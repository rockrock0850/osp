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

package com.fet.crm.osp.platform.core.service.systeminfo.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.platform.core.common.util.ExcelParseUtil;
import com.fet.crm.osp.platform.core.common.util.IdentifierIdUtil;
import com.fet.crm.osp.platform.core.common.util.ResourceFileUtil;
import com.fet.crm.osp.platform.core.db.jdbc.JdbcDAO;
import com.fet.crm.osp.platform.core.db.warehouse.BuzVPosCompareResultWareHouse;
import com.fet.crm.osp.platform.core.service.systeminfo.IBuzVPosCompareService;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.content.compare.BuzVPOSCompareResultVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.content.compare.BuzVPOSCompareVO;

/**
 * VPOS AIMS 資料對比 實作類別
 *
 * @author AndrewLee
 */
@Service
public class BuzVPosCompareServiceImpl implements IBuzVPosCompareService {
    
    @Autowired
    private BuzVPosCompareResultWareHouse buzVPosCompareResultWareHouse;
    
    @Autowired
    private JdbcDAO jdbcDao;
    
    @Override
    public boolean doCompareExcel(BuzVPOSCompareVO buzVPOSCompareVO) {
        //開始解析並且輸出成map
        File VPOSfile = buzVPOSCompareVO.getvPosfile();
        File AIMSFile = buzVPOSCompareVO.getAimsFile();
        List<Map<String, Object>> vposExcelList = fromExcelAtRowNumForVPOSOnly(VPOSfile, 1);
        List<Map<String, Object>> aimsExcelList = ExcelParseUtil.fromExcel(AIMSFile, 3);

        List<Map<String,Object>> dataList = new ArrayList<>();

        //開始檢核
        if (CollectionUtils.isNotEmpty(vposExcelList) && CollectionUtils.isNotEmpty(aimsExcelList)) {
            for (Map<String, Object> vPosMap : vposExcelList) {

                if (!vPosMap.isEmpty()) {
                    String vPosAuthCode = MapUtils.getString(vPosMap, "授權碼");
                    String vPosMoney = MapUtils.getString(vPosMap, "交易金額").split("\\.")[0];
                    String vPosNumberPeriods = MapUtils.getString(vPosMap, "分期數");
                    String vPosRemark = MapUtils.getString(vPosMap, "備註","無");

                    //一行(ROW)資料為一個dataMap.dataMap的數量以VPOS的筆數為準
                    Map<String,Object> dataMp = new HashMap<>();

                    //若有授權碼.把VPOS的資料塞進VO
                    if (StringUtils.isNotBlank(vPosAuthCode)) {
                        
                        dataMp.put("COMPARE_ID", IdentifierIdUtil.getUuid());
                        dataMp.put("V_POS_AUTH_CODE", vPosAuthCode.split("\\.")[0]);
                        dataMp.put("V_POS_REMARK", vPosRemark);
                        dataMp.put("V_POS_MONEY", new BigDecimal(vPosMoney));
                        dataMp.put("CREATE_USER", buzVPOSCompareVO.getUserId());
                        dataMp.put("UPDATE_USER", buzVPOSCompareVO.getUserId());
                        dataMp.put("CREATE_DATE", new Date());
                        dataMp.put("UPDATE_DATE", new Date());

                        //初始化JDBC INSERT所需要的內容.下面幾個有符合的資料就塞.沒資料就INSERT時直接塞NULL.
                        //但絕對不能不塞.否則會拋Exception
                        dataMp.put("AIMS_AUTH_CODE", null);
                        dataMp.put("AIMS_ACTUAL_PAYMENT", null);
                        dataMp.put("AIMS_MSISDN", null);
                        dataMp.put("AIMS_ORDER_ID", null);
                        dataMp.put("AIMS_SHIPPING_ID", null);
                        
                        //vPosNumberPeriods 有值才會給他轉型成BigDecimal.否則直接給NULL
                        if(StringUtils.isNotBlank(vPosNumberPeriods)) {
                            dataMp.put("V_POS_NUMBER_PERIODS", new BigDecimal(vPosNumberPeriods));
                        } else {
                            dataMp.put("V_POS_NUMBER_PERIODS", null);
                        }

                        // 透過得到的授權碼對所有AIMS的資料進行對比
                        for (int j = 0; j < aimsExcelList.size(); j++) {
                            Map<String, Object> aimsMap = aimsExcelList.get(j);

                            String aimsAuthCode = MapUtils.getString(aimsMap, "授權碼");

                            // 如果這個Map為空.或沒有授權碼的話.直接continue
                            if (aimsMap.isEmpty() || StringUtils.isBlank(aimsAuthCode)) {
                                // 如果剛好這個沒有授權碼的交易是最後一筆的話.一樣註明為查無授權碼的訊息
                                if (j == aimsExcelList.size() - 1) {
                                    dataMp.put("COMPARE_RESULT","003");
                                    
                                    dataList.add(dataMp);
                                }
                                continue;
                            }

                            // 開始對比 ,如果有找到與該筆VPOS授權碼相符的AIMS單號時.則塞值
                            if (vPosAuthCode.equals(aimsAuthCode)) {
                                String aimsMoney = MapUtils.getString(aimsMap, "實際付款金額");
                                String aimsMsisdn = MapUtils.getString(aimsMap, "門號");
                                String aimsOrderId = MapUtils.getString(aimsMap, "編號");
                                String aimsShippingId = MapUtils.getString(aimsMap, "出貨單號");
                                
                                dataMp.put("AIMS_AUTH_CODE", aimsAuthCode.split("\\.")[0]);
                                dataMp.put("AIMS_ACTUAL_PAYMENT", new BigDecimal(aimsMoney));
                                dataMp.put("AIMS_MSISDN", aimsMsisdn);
                                dataMp.put("AIMS_ORDER_ID", aimsOrderId);
                                dataMp.put("AIMS_SHIPPING_ID", aimsShippingId);

                                // 有找到授權碼後.在檢核金額是否正確,根據金額來判斷對比結果
                                if (vPosMoney.equals(aimsMoney)) {
                                    dataMp.put("COMPARE_RESULT","001");
                                } else {
                                    dataMp.put("COMPARE_RESULT","002");
                                }
                                dataList.add(dataMp);

                                break;
                            }

                            // 若已經是最後一筆資料,即沒有找到符合條件的授權碼
                            if (j == aimsExcelList.size() - 1) {
                                dataMp.put("COMPARE_RESULT","003");
                                
                                dataList.add(dataMp);
                            }
                        }
                    }
                }
            }
            //執行前先deleteTable裡面的對比結果資料
            buzVPosCompareResultWareHouse.deleteAll();
            
            //開始批次insert
            String sqlText = ResourceFileUtil.SQL_SYSTEM.getResource("content","INSERT_INTO_BUZ_VPOS_COMPARE_RESULT");
            jdbcDao.batchUpdate(sqlText, dataList);
        }

        return true;
    }

    @Override
    public List<BuzVPOSCompareResultVO> queryCompareResult() {
        String sqlText = ResourceFileUtil.SQL_SYSTEM.getResource("content","QUERY_BUZ_VPOS_COMPARE_RESULT");
        
        List<BuzVPOSCompareResultVO> dataList = jdbcDao.queryForList(sqlText, new HashMap<String,Object>(), BuzVPOSCompareResultVO.class);
        
        //將金額轉換成千位數有逗號來區隔
        for(BuzVPOSCompareResultVO vo : dataList) {
            String vposMoney = vo.getVPosMoney();
            String aimsActualPayment = vo.getAimsActualPayment();
            
            String vposMoneyFloat = String.format("%,.0f", Double.parseDouble(vposMoney));
            String aimsActualPaymentFloat = String.format("%,.0f", Double.parseDouble(aimsActualPayment));
            
            vo.setVPosMoney(vposMoneyFloat);
            vo.setAimsActualPayment(aimsActualPaymentFloat);
        }
        
        return dataList;
    }
    
    //----------------------------------------------------------------
    
    /**
     * 輸入行號.從該行開始解析Excel.第一個被讀取的所有資料將成為Column Name,來作為Map的Key 
     * WARNING!注意!.此方法為VPOS Excel專屬.若要使用共用的請到ExcelParseUtil裡面的method
     * 
     * @param rowBeginNum 若是Excel的第一行資料.則為0.以此類推
     * @param excel Excel檔案
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> fromExcelAtRowNumForVPOSOnly(File excel, int rowBeginNum) {
        if (excel != null) {
            List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

            try {
                Workbook workbook = WorkbookFactory.create(excel);

                // 從Sheet開始解析
                for (int sheetNum = 0; sheetNum < 1; sheetNum++) {
                    Sheet sheet = workbook.getSheetAt(sheetNum);
                    List<String> columnListOdd = new ArrayList<String>();
                    List<String> columnListEven = new ArrayList<String>();

                    // Row Loop 行數Loop
                    Map<String, Object> rowMapOdd = new HashMap<>();
                    Map<String, Object> rowMapEven = new HashMap<>();

                    for (int rowNum = rowBeginNum; rowNum <= sheet.getLastRowNum(); rowNum++) {
                        Row row = sheet.getRow(rowNum);

                        if (row != null) {
                            // Cell Loop 格子
                            for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
                                Cell cell = row.getCell(cellNum);

                                if (rowNum == rowBeginNum) {
                                    // 起始行為欄位名稱,以欄位名稱作為key
                                    columnListOdd.add(String.valueOf(cell));

                                } else if (rowNum == rowBeginNum + 1) {
                                    // 起始行的下面一行也是欄位名稱,以欄位名稱作為key
                                    columnListEven.add(String.valueOf(cell));
                                } else {
                                    // 判斷是否為基數行,若是.取columnListOdd作為該欄位的key,cell為value.組合成這一格的資料
                                    if (rowNum % 2 != 0) {
                                        rowMapOdd.put(columnListOdd.get(cellNum), cell);
                                    } else {
                                        // 同上.不過是偶數行
                                        rowMapEven.put(columnListEven.get(cellNum), cell);

                                        // 如果已經執行到偶數行了.就檢查是否是最後一個欄位的資料
                                        if (cellNum == row.getLastCellNum() - 1) {
                                            // 如果是.在檢查兩者是否都不為空
                                            if (!rowMapOdd.isEmpty() && !rowMapEven.isEmpty()) {
                                                // 若通過檢核.把兩個塞好值的Map合併.放入List,並且在初始化
                                                rowMapOdd.putAll(rowMapEven);
                                                dataList.add(rowMapOdd);

                                                // 注意.由於資料是兩行兩行為一組.若你在上面那個迴圈的開頭裡產生一個new Map的話.
                                                // 會導致基數的Map中的資料被刷掉
                                                // 所以刷新Map的行為一律做在把Map資料放進List之後
                                                rowMapOdd = new HashMap<>();
                                                rowMapEven = new HashMap<>();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                return dataList;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

}
