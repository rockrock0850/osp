package com.fet.crm.osp.kernel.core.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.fet.crm.osp.kernel.core.common.LogContext;

/**
 * 業務邏輯層-出/入 參數 攔截器
 * 
 * <br />
 * "只" 處理 - 各功能的 package，如：com.fet.crm.osp.kernel.core.common.aspect..*
 * 
 * @author PaulChen
 */
@Aspect
@Component ("businessLayerAspect")
public class BusinessLayerAspect extends AbstractAspectParser {
	
	/**
	 * 記錄 Facade Layer 輸入/出參數
	 * 
	 * @param pjp
	 * @return Object
	 * @throws Throwable
	 */
	@Around("execution(* com.fet.crm.osp.kernel.core.module..*.facade..*.*(..))")
	public Object executeFacadeLayer(ProceedingJoinPoint pjp) throws Throwable {
		String inputMessage = processInputMessage(pjp);
		writeToLogContext(inputMessage);
		
		Object retVal = pjp.proceed();
		
		String outputMessage = processOutputMessage(pjp);
		writeToLogContext(outputMessage);
		// writeToLogContext(processReturnValues(retVal));
		
		return retVal;
	}
	
	/**
	 * 記錄 Service Layer 輸入/出參數
	 * 
	 * @param pjp
	 * @return Object
	 * @throws Throwable
	 */
	@Around("execution(* com.fet.crm.osp.kernel.core.module..*.service.impl..*.*(..))")
	public Object executeServiceLayer(ProceedingJoinPoint pjp) throws Throwable {
		String inputMessage = processInputMessage(pjp);
		writeToLogContext(inputMessage);
		
		Object retVal = pjp.proceed();
		
		String outputMessage = processOutputMessage(pjp);
		writeToLogContext(outputMessage);
		// writeToLogContext(processReturnValues(retVal));
		
		return retVal;
	}
	
	// =========================== tools method
	
	/**
	 * 將訊息寫入日誌 CONTEXT 物件中
	 * 
	 * @param logMessage
	 */
	private void writeToLogContext(String logMessage) {
		LogContext logCtx = LogContext.getContext();
		logCtx.append(logMessage);
	}

}