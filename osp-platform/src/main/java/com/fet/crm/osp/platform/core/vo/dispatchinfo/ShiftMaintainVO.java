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

package com.fet.crm.osp.platform.core.vo.dispatchinfo;

import java.io.File;
import java.io.FileInputStream;

import com.fet.crm.osp.common.vo.AbstractOspBaseVO;
import com.fet.crm.osp.platform.core.common.util.PropertiesUtil;
import com.fet.crm.osp.platform.core.vo.systeminfo.component.FileVO;

/**
 * [班表檔案] 資料封裝物件
 * 
 * @author LawrenceLai, Adam Yeh
 */
public class ShiftMaintainVO extends AbstractOspBaseVO {

	private String dtYear;
	private String dtMonth;
	private FileVO shiftAttachment;

	public String getDtYear() {
		return dtYear;
	}

	public void setDtYear(String dtYear) {
		this.dtYear = dtYear;
	}

	public String getDtMonth() {
		return dtMonth;
	}

	public void setDtMonth(String dtMonth) {
		this.dtMonth = dtMonth;
	}

	public FileVO getShiftAttachment() {
		return shiftAttachment;
	}

	public void setShiftAttachment(FileVO shiftAttachment) {
		this.shiftAttachment = shiftAttachment;
	}
	
	public File getShift() {
		File file = shiftAttachment.getFile();
		
		try {
			FileInputStream reader = new FileInputStream(file);   
			int check = reader.read();
			
			reader.close();
			if (check == -1) {
				String filePath = PropertiesUtil.getProperty("temp.store.path");
				
			    return new File(filePath + "/" + "emptyFile.xlsx");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return file;
	}

}
