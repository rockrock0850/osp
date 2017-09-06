/**
 * Copyright (c) 2017 Far Eastone Telecommunications Co., Ltd.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of 
 * Far Eastone Telecommunications Co., Ltd. ("Confidential Information"). 
 * 
 * You shall not disclose such Confidential Information and shall use it 
 * only in accordance with the terms of license agreement you entered
 * into with Far Eastone Telecommunications Co., Ltd.
 */

package com.fet.crm.osp.platform.webapp.action.setting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fet.crm.osp.common.util.DateUtil;
import com.fet.crm.osp.platform.core.common.util.CSVUtil;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.common.util.PropertiesUtil;
import com.fet.crm.osp.platform.core.facade.dispatchinfo.OrderTypeManageFacade;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.FlowSourceMapVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.OrderTypeInfoVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.OrderTypeMaintainVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderStatusCVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.PersonalOrderOperateRecordCVO;
import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;
import com.fet.crm.osp.platform.webapp.handler.HttpSessionHandler;
import com.fet.crm.osp.platform.webapp.result.ForwardUtil;

/**
 * [案件類別維護作業] 導頁控制器
 * 
 * @author LawrenceLai, Adam Yeh, AndrewLee
 */
@Controller
@RequestMapping("/setting/order")
public class OrderTypeMaintainAction {

    @Autowired
    OrderTypeManageFacade orderTypeManageFacade;

    /**
     * 「案件類別維護」服務頁面
     * 
     * @return String
     */
    @RequestMapping(value = "/service-order-type", method = RequestMethod.GET)
    public String serviceOrderType() {
        return ForwardUtil.OSPLV2011.getView();
    }

    /**
     * 「查詢案件類別資訊」
     * 
     * @return String
     */
    @RequestMapping(value = "/get-order-type-info", method = RequestMethod.POST)
    public String getOrderTypeInfo() {
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        String orderTypeName = MapUtils.getString(requestData, "name");
        List<OrderTypeInfoVO> voList = orderTypeManageFacade.getOrderTypeInfoByTypeName(orderTypeName);

        String responseData = JsonUtil.toJson(voList);
        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.JSON.getView();
    }

    /**
     * 修改案件類別設定
     * 
     * @return String
     */
    @RequestMapping(value = "/modify-order-type", method = RequestMethod.POST)
    public String modifyOrderType() {
        String requestData = HttpRequestHandler.getJsonData();
        String userId = HttpSessionHandler.getSessionInfo().getUserId();

        OrderTypeMaintainVO vo = JsonUtil.fromJson(requestData.toString(), OrderTypeMaintainVO.class);
        vo.setCreateUser(userId);
        vo.setUpdateUser(userId);
        
        //若這兩個欄位的資料為空的話。即代表前端Checkbox未勾選。直接給予N
        if(StringUtils.isBlank(vo.getIsRegularTime())) {
            vo.setIsRegularTime("N");
        }
        
        if(StringUtils.isBlank(vo.getChkCreateDate())) {
            vo.setChkCreateDate("N");
        }

        boolean dataMap = orderTypeManageFacade.modifyOrderTypeInfo(vo);

        String responseData = JsonUtil.toJson(dataMap);
        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.JSON.getView();
    }

    /**
     * 查詢進件來源
     * 
     * @return String
     */
    @RequestMapping(value = "/query-source-sys", method = { RequestMethod.POST })
    public String querySourceSys() {
        Map<String, Object> requestData = HttpRequestHandler.getJsonToMap();

        String orderTypeId = MapUtils.getString(requestData, "orderTypeId");
        List<FlowSourceMapVO> voList = orderTypeManageFacade.getFlowSourceByorderTypeId(orderTypeId);

        String responseData = JsonUtil.toJson(voList);
        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.JSON.getView();
    }

    /**
     * 顯示 查詢案件狀態 頁面
     * 
     * @return String
     */
    @RequestMapping("/service-order-status")
    public String serviceOrderStatus() {

        return ForwardUtil.OSPLV2004.getView();
    }

    /**
     * 取得案件狀態 查詢結果
     * 
     * @return String
     */
    @RequestMapping("/get-order-status-info")
    public String getOrderStatusInfo() {
        String requestData = HttpRequestHandler.getJsonData();
        OrderStatusCVO cvo = JsonUtil.fromJson(requestData, OrderStatusCVO.class);

        List<Map<String, Object>> dataList = orderTypeManageFacade.getOrderStatusInfo(cvo);

        String responseData = JsonUtil.toJson(dataList);
        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.JSON.getView();
    }

    /**
     * 將查詢結果匯入CSV檔案中,並且回傳地址.提供檔案名稱以及地址以供下載
     * 
     * @param response
     * @return String
     */
    @RequestMapping("/export-csv-file")
    public @ResponseBody Map<String,Object> exportCSVFile() {
        String requestData = HttpRequestHandler.getJsonData();
        List<Map<String, Object>> dataList = JsonUtil.fromJsonToList(requestData, Map.class);

        //如果需要對CSV檔案建立title.則利用List<String>的方式,傳入CSVUtil
        List<String> titleList = new ArrayList<>();
        titleList.add("案件狀態");
        titleList.add("OSP進件時間狀態");
        titleList.add("OSP單號");
        titleList.add("案件類別");
        titleList.add("來源單號");
        titleList.add("筆數");
        titleList.add("母子單");
        titleList.add("進件系統");
        titleList.add("產品類別");
        titleList.add("門號/代表號/線路編號");
        titleList.add("用戶名稱");
        titleList.add("證號/統編");
        titleList.add("交易型態");
        titleList.add("經銷代碼");
        titleList.add("業務員編");
        titleList.add("處理人員");
        titleList.add("處理原因");
        titleList.add("備註");
        titleList.add("客戶指定生效日");
        titleList.add("授權原因");
        titleList.add("促銷代號");
        titleList.add("結案日期");

        FileOutputStream fileOutputStream = null;

        Map<String,Object> rtnMap = new HashMap<>();
        
        try {
            String filePath = PropertiesUtil.getProperty("application.temp.file.csv");
            String fileName = "案件狀態查詢_"+ DateUtil.formatDt(new Date(), DateUtil.DATE_TIME_PATTERN_FOR_FILE_NAME) + ".csv";

            File file = new File(filePath + "/" + fileName);
            //若無該資料夾.則新增
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // 必須指定其父資料夾再用.mkdirs()的方法.不然會把fileName.csv當作資料夾一起建立
                file.createNewFile(); // 執行建立資料夾
            }

            fileOutputStream = new FileOutputStream(file);

            CSVUtil.parseCSV(dataList, titleList, fileOutputStream);
            
            rtnMap.put("fileName", fileName);
            rtnMap.put("filePath", filePath + "/" + fileName);

        } catch (Exception e) {
            // FIXME 暫時用這個來代替
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                // FIXME 暫時用這個來代替
                e.printStackTrace();
            }
        }
        
        return rtnMap;
    }

    /**
     * 透過檔案路徑,下載CSV檔 注意.此action為 必須與exportCSVFile搭配使用 透過AJAX Call exportCSVFile
     * 後.會在Ajax的Success處執行此action,才可透過AJax執行下載檔案
     * 
     * @param filePath
     * @param fileName
     * @return String
     */
    @RequestMapping("/excute-csv-file-download")
    public @ResponseBody void excuteCSVFileDownLoad(@RequestParam String filePath, @RequestParam String fileName,
            HttpServletResponse response) {
        File file = new File(filePath);
        
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");//解決檔名亂碼問題
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);//指定下載檔案的名稱為fileName的宣告變數
            
            FileInputStream fileInputStream = new FileInputStream(file);
            OutputStream outputStream = response.getOutputStream();

            FileCopyUtils.copy(fileInputStream, outputStream);
            
        } catch (IOException e) {
            // FIXME 暫時用這個來代替
            e.printStackTrace();
        }
    }
    
    /**
     * 顯示 個人產能狀態查詢 頁面
     * 
     * @return String
     */
    @RequestMapping("/service-personal-order-operate-records")
    public String servicePersonalOrderOperateRecords() {
        
        return ForwardUtil.OSPLV2005.getView();
    }
    
    /**
     * 查詢個人產能
     * 
     * @return String
     */
    @RequestMapping("/get-personal-order-operate-records-info")
    public String getPersonalOrderOperateRecordsInfo() {
        String requestData = HttpRequestHandler.getJsonData();
        PersonalOrderOperateRecordCVO cvo = JsonUtil.fromJson(requestData, PersonalOrderOperateRecordCVO.class);

        List<Map<String, Object>> dataList = orderTypeManageFacade.getPersonalOrderOperateRecordInfo(cvo);

        String responseData = JsonUtil.toJson(dataList);
        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.JSON.getView();
    }
    
    /**
     * 匯出個人產能為CSV檔案,並且回傳地址.提供檔案名稱以及地址以供下載
     * 
     * @return Map<String,Object>
     */
    @RequestMapping("/export-operate-record-csv")
    public @ResponseBody Map<String,Object> exportPersonalOperateRecordsCSV() {
        String requestData = HttpRequestHandler.getJsonData();
        List<Map<String, Object>> dataList = JsonUtil.fromJsonToList(requestData, Map.class);
        
        //如果需要對CSV檔案建立title.則利用List<String>的方式寫入,傳入CSVUtil
        List<String> titleList = new ArrayList<>();
        titleList.add("處理人員");
        titleList.add("日期");
        titleList.add("實際完成產能");
        titleList.add("個人待處理完成產能");
        titleList.add("前兩者加總產能");
        titleList.add("當日已派件預計處理產能");
        
        FileOutputStream fileOutputStream = null;

        Map<String,Object> rtnMap = new HashMap<>();
        
        try {
            String filePath = PropertiesUtil.getProperty("application.temp.file.csv");
            String fileName = "個人產能狀態查詢_"+ DateUtil.formatDt(new Date(), DateUtil.DATE_TIME_PATTERN_FOR_FILE_NAME) + ".csv";

            File file = new File(filePath + "/" + fileName);
            //若無該資料夾.則新增
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // 必須指定其父資料夾再用.mkdirs()的方法.不然會把fileName.csv當作資料夾一起建立
                file.createNewFile(); // 執行建立資料夾
            }

            fileOutputStream = new FileOutputStream(file);

            CSVUtil.parseCSV(dataList, titleList, fileOutputStream);
            
            rtnMap.put("fileName", fileName);
            rtnMap.put("filePath", filePath + "/" + fileName);

        } catch (Exception e) {
            // FIXME 暫時用這個來代替
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                // FIXME 暫時用這個來代替
                e.printStackTrace();
            }
        }
        
        return rtnMap;
    }

}
