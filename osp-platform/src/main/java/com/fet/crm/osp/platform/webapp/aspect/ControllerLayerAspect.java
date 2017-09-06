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

package com.fet.crm.osp.platform.webapp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * 
 * @author LawrenceLai
 */
@Aspect
public class ControllerLayerAspect {

	@Around("execution(* com.fet.crm.osp.platform.webapp.action..*.*(..))")
	public Object executeActionLayer(ProceedingJoinPoint pjp) throws Throwable {
	    Object retVal = pjp.proceed();
	    
	    return retVal;
	}

}
