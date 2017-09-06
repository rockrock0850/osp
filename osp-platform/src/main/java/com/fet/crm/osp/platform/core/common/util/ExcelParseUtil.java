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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Excel解析工具
 * 
 * @author LawrenceLai, AndrewLee, Adam Yeh
 */
public class ExcelParseUtil {
	
	/**
	 * 輸入行號.從該行開始解析Excel.第一個被讀取的所有資料將成為Column Name,來作為Map的Key
	 * 
	 * @param rowBeginNum 若是Excel的第一行資料.則為0.以此類推
	 * @param excel Excel檔案
	 * @return List<Map<String, Object>>
	 */
	public static List<Map<String, Object>> fromExcel(File excel, int rowBeginNum) {
	    if (excel != null) {
	        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
	        
	        try {
	            Workbook workbook = WorkbookFactory.create(excel);
	            
	            //從Sheet開始解析
	            for (int sheetNum = 0; sheetNum < 1; sheetNum++) {
	                Sheet sheet = workbook.getSheetAt(sheetNum);
	                List<String> columnList = new ArrayList<String>();
	                
	                // Row Loop 行數Loop
	                for (int rowNum = rowBeginNum; rowNum <= sheet.getLastRowNum(); rowNum++) {
	                    Row row = sheet.getRow(rowNum);
	                    Map<String, Object> rowMap = new HashMap<String, Object>();
	                    
	                    if (row != null) {
	                        // Cell Loop 格子 
	                        for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
	                            Cell cell = row.getCell(cellNum);
	                            
	                            if (rowNum == rowBeginNum) {
	                                // 第一列為欄位名稱,以欄位名稱作為key
	                                columnList.add(String.valueOf(cell));
	                            } else {
	                                rowMap.put(columnList.get(cellNum), cell);
	                            }
	                        }
	                    }
	                    
	                    if(rowNum != 0) {
	                        dataList.add(rowMap);
	                    }
	                }
	            } 
	            
	            return dataList;
	        } catch (InvalidFormatException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return null;
	}

	public static List<Map<String, Object>> fromExcel(File excel) {
	    return fromExcel(excel, 0);
	}
	
}
