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

package com.fet.crm.osp.platform.webapp.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.fet.crm.osp.common.exception.OSPException;
import com.fet.crm.osp.common.exception.code.IExceptionCode;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.excpetion.code.CoreExceptionCode;
import com.fet.crm.osp.platform.core.excpetion.validate.DataValidateException;
import com.fet.crm.osp.platform.webapp.logger.WebappLoggerFactory;

/**
 * WepApp 例外處理 控制器
 * 
 * @author Lawrence.Lai
 */
@ControllerAdvice
public class ExceptionController {
	
	private Logger logger = WebappLoggerFactory.getLogger(ExceptionController.class);
	
	/**
	 * 處理非預期錯誤
	 * 
	 * @param t
	 *            非預期錯誤內容
	 * @return ModelAndView
	 */
	@ExceptionHandler(Throwable.class)
	public ModelAndView process(HttpServletResponse res, Throwable t) {
		String reqURL = HttpRequestHandler.getRequestURL();
		logger.error(reqURL, t);
		
		return internalProcess(t, res);
	}

	/**
	 * 處理預期錯誤
	 * 
	 * @param t
	 *            預期錯誤內容
	 * @return ModelAndView
	 */
	@ExceptionHandler(OSPException.class)
	public ModelAndView process(HttpServletResponse res, OSPException ex) {
		String reqURL = HttpRequestHandler.getRequestURL();
		logger.error(reqURL, ex);
		
		return internalProcess(ex, res);
	}
	
	/**
	 * 
	 * 
	 * @param t
	 * @return ModelAndView
	 */
	private ModelAndView internalProcess(Throwable t, HttpServletResponse res) {
		String errCode, errMessage, excStackTrace = null;
		
		excStackTrace = ExceptionUtils.getStackTrace(t);
		
		if (t instanceof DataValidateException) {
			DataValidateException valExc = (DataValidateException) t;
			
			errCode = valExc.getErrorCode();
			errMessage = valExc.getErrorMessage();
		} else if (t instanceof OSPException) {
			OSPException ospExc = (OSPException) t;
			
			errCode = ospExc.getErrorCode();
			errMessage = ospExc.getErrorMessage();
		} else {
			IExceptionCode defCode = getDefaultCode();
			
			errCode = defCode.getErrorCode();
			errMessage = defCode.getErrorMessage();
		}
		
		ModelAndView mAndV = new ModelAndView();
		
		boolean isAjax = isAjax();
		if(isAjax) { // ajax 
			mAndV.setViewName("tiles.data.json");
			
			Map<String, Object> jsonMp = new HashMap<>();
			jsonMp.put("errCode", errCode);
			jsonMp.put("errMessage", errMessage);
			jsonMp.put("excStackTrace", excStackTrace);
			
			String jsonStr = JsonUtil.toJson(jsonMp);
			mAndV.addObject("responseData", jsonStr);
			
			// set response code 500
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} else { // not ajax
			mAndV.setViewName("tiles.error.index");
			mAndV.addObject("errCode", errCode);
			mAndV.addObject("errMessage", errMessage);
			mAndV.addObject("excStackTrace", excStackTrace);
		}
		
		return mAndV;
	}

	/**
	 * 取得預設錯誤訊息代碼、資訊
	 * 
	 * @return IExceptionCode
	 */
	private IExceptionCode getDefaultCode() {
		return CoreExceptionCode.SYS_001;
	}
	
	/**
	 * 判斷是否為 ajax 請求
	 * 
	 * @return boolean
	 */
	private boolean isAjax() {
		return HttpRequestHandler.isAjaxReq();
	}
	
}

