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

package com.fet.crm.osp.kernel.core.exception;

import org.springframework.stereotype.Component;

import com.fet.crm.osp.common.exception.ExceptionFactory;
import com.fet.crm.osp.common.exception.db.DaoDataException;

/**
 * 核心組件例外轉換類別
 * 
 * @author PaulChen, RichardHuang
 */
@Component
public class KernelExceptionFactory extends ExceptionFactory {

	/**
	 * 將非預期之例外轉換成 CoreException
	 * 
	 * @param ex
	 *            發生Exception 類別
	 * @return CoreException
	 */
	public CoreException getException(Throwable ex) {
		String message = ex.getMessage();
		Throwable cause = ex;

		CoreException cex = new CoreException(message, cause);
		return cex;
	}
	
	/**
     * 將 IllegalArgumentException 轉換成 CoreException
     * 
     * @param ex
     *            發生Exception 類別
     * @return CoreException
     */
    public CoreException getException(IllegalArgumentException ex) {
        String message = ex.getMessage();
        Throwable cause = ex;

        CoreException cex = new CoreException(message, cause);
        return cex;
    }
    
    /**
     * 將 DaoDataException 轉換為可明確得知資料庫異常訊息 CoreException
     * 
     * @param ex
     *            發生Exception 類別
     * @return CoreException
     */
    public CoreException getException(DaoDataException ex) {
        String message = ex.getMessage();
        Throwable cause = ex;

        CoreException cex = new CoreException(message, cause);
        return cex;
    }

}
