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

package com.fet.crm.osp.platform.core.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.fet.crm.osp.common.exception.OSPException;
import com.fet.crm.osp.platform.core.excpetion.PlatformExceptionFactory;
import com.fet.crm.osp.platform.core.excpetion.validate.DataValidateException;
import com.fet.crm.osp.platform.core.logger.CoreLoggerFactory;

/**
 * 核心組件例外縫合器
 * 
 * @author PaulChen
 */
@Aspect
@Component
public class CoreThrowsAspect {

	private static Logger logger = CoreLoggerFactory.getLogger(CoreThrowsAspect.class);
	
	@Autowired
	private PlatformExceptionFactory factory;
	
	/**
	 * 轉換成「CoreException」
	 * 
	 * 補充： 為非預期之例外
	 * 
	 * @param jp
	 *            縫合對象資訊 封裝物件
	 * @param t
	 *            異常資訊 封裝物件
	 */
	@Around("execution(* com.fet.crm.osp.platform.core.facade..*.*(..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		String clazzNm = pjp.getTarget().getClass().getName();
		
		MethodSignature methodSign = (MethodSignature) pjp.getSignature();
		String methodNm = methodSign.getMethod().getName();
		
		try {
			logger.info(" INPUT " + clazzNm + "." + methodNm);
			
			Object retVal = pjp.proceed();
			
			logger.info(" OUTPUT " + clazzNm + "." + methodNm);
			
			return retVal;
		} catch (DataValidateException ex) {
			logMessge(pjp, ex);
			
			throw ex;
		} catch (DataAccessException ex) {
			OSPException ospEx = factory.getException(ex);
			
			logMessge(pjp, ospEx);
			
			throw ospEx;
		}  catch (Throwable t) {
			OSPException ex = factory.getException(t);
			
			logMessge(pjp, ex);
			
			throw ex;
		}
	}

	/**
	 * 將錯誤對象、方法名稱打印於日誌檔
	 * 
	 * @param jp
	 *            縫合對象資訊 封裝物件
	 * @param t
	 *            異常資訊 封裝物件
	 */
	private void logMessge(JoinPoint jp, Throwable e) {
		// 可對於 JoinPoint 內容做打印訊息
		MethodSignature methodSignature = (MethodSignature) jp.getSignature();
		String clazzNm  = jp.getTarget().getClass().getName();
		String methodNm = methodSignature.getName();
				
		// if (logger.isErrorEnabled()) {
			logger.error("error", e);
			
			logger.error(" class name = " + clazzNm + " ; methodNm = " + methodNm);
		// }
	}

}
