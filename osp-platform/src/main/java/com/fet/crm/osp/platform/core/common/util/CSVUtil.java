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

package com.fet.crm.osp.platform.core.common.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections4.CollectionUtils;

/**
 * CSV 轉換工具
 *
 * @author AndrewLee
 */
public class CSVUtil {

    public static void parseCSV(List<Map<String, Object>> dataList, FileOutputStream fileOutputStream) {
        parseCSV(dataList, null, fileOutputStream);
    }

    /**
     * 透過傳入的物件以及FileOutStream.將物件裡面的內容寫入FileOutStream中
     * 
     * @param dataList 欄位裡面的資料
     * @param titleList title資料
     * @param fileOutputStream
     */
    public static void parseCSV(List<Map<String, Object>> dataList, List<String> titleList,
            FileOutputStream fileOutputStream) {

        try {
            StringBuffer sb = new StringBuffer();

            // 有些軟體必須有 BOM 的協助來判斷 unicode 文件的編碼，如果沒有 BOM 有些軟體不會自己去猜測。
            // 在檔案最前頭加入指示 UTF-8 編碼的 BOM(也就是字元 '\uFEFF' 以 UTF-8 編碼來 encode)。
            // https://www.javaworld.com.tw/jute/post/view?bid=29&id=267500
            byte[] BOM_UTF8 = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };
            fileOutputStream.write(BOM_UTF8);
            
            // 開始輸入欄位裡的資料
            for (Map<String, Object> map : dataList) {
                for (String key : map.keySet()) {
                    String value = MapUtils.getString(map, key, " ");

                    //檢查該值是否為純數字.若是.則加上單引號避免EXCEL讀取時自己轉型
                    if (checkNumber(value)) {
                        value = "'" + value;
                    }
                    sb.append(value).append(",");
                }
                sb.append("\n");
            }

            //如果有需要在CSV裡顯示title的話.則進行下列輸入
            if(CollectionUtils.isNotEmpty(titleList)) {
                StringBuffer titleSB = new StringBuffer();
                
                for(String title : titleList) {
                    titleSB.append(title).append(",");
                }
                
                titleSB.append("\n");
                fileOutputStream.write((titleSB.toString()).getBytes("UTF-8"));
            }

            fileOutputStream.write((sb.toString()).getBytes("UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 透過字串轉型成整數時是否會發生Exception來判斷該字串是否為數字
     * 
     * @param value
     * @return boolean
     */
    private static boolean checkNumber(String value) {
        try {
            Integer.parseInt(value);

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
