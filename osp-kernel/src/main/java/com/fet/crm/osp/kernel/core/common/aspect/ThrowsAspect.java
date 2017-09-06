//package com.fet.crm.osp.kernel.core.common.aspect;
//
//import org.apache.log4j.spi.ErrorCode;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.dao.DataAccessException;
//import org.springframework.stereotype.Component;
//
//import com.fet.crm.osp.common.exception.util.ExceptionStackExtractor;
//import com.fet.crm.osp.kernel.core.common.LogContext;
//import com.fet.crm.osp.kernel.core.exception.OSPAspectException;
//
///**
// * 例外 攔截器
// * 
// * @author PaulChen
// */
//@Aspect
//@Component ("throwsAspect")
//public class ThrowsAspect extends AbstractAspectParser {
//	
//	/**
//	 * 將例外轉為「SOA Portal」自訂義例外
//	 * 
//	 * @param joinPoint
//	 * @param e
//	 */
//	@AfterThrowing(pointcut = "execution(* com.fet.crm.osp.kernel.core.module..*.*(..))", throwing = "e")
//	public void throwsException(JoinPoint jp, Throwable e) throws OSPAspectException {
//		// 若已為「OSPAspectException」實例，則不做任何處理直接再往外拋出
//		if(e instanceof OSPAspectException) {
//			throw (OSPAspectException) e;
//		}
//		
//		// execution(* com.fet.osp.kernel..*.core..*.*(..)) -- 以各模組 core package 為對象
//		// execution(* com.fet.osp.kernel..*.facade..*.*(..)) -- 以各模組 core.facade package 為對象
//		String inputMessage = processInputMessage(jp);
//		String excStackTrace = ExceptionStackExtractor.extractExceptionStack(e, new StringBuffer(), 1);
//		
//		writeExcToLogContext(inputMessage, excStackTrace);
//		
//		OSPAspectException oSPAspectException = new OSPAspectException(ErrorCode.SYSTEM_ERROR, e, excStackTrace);
//		throw oSPAspectException;
//	}
//	
//	/**
//	 * 將例外轉為「SOA Portal」自訂義例外
//	 * 
//	 * @param joinPoint
//	 * @param e
//	 */
//	@AfterThrowing(pointcut = "execution(* com.fet.crm.osp.kernel.core.module..*.facade..*.*(..))", throwing = "e")
//	public void throwsException(JoinPoint jp, DataAccessException e) throws OSPAspectException {
//		String inputMessage = processInputMessage(jp);
//		String excStackTrace = ExceptionStackExtractor.extractExceptionStack(e, new StringBuffer(), 1);
//		
//		writeExcToLogContext(inputMessage, excStackTrace);
//		
//		OSPAspectException oSPAspectException = new OSPAspectException(ErrorCode.DB_ERROR, e, excStackTrace);
//		throw oSPAspectException;
//	}
//	
//	/**
//	 * 將有 Exception stack trace 寫入至 LogContext 元件
//	 * 
//	 * @param inputMessage
//	 * @param excStackTrace
//	 */
//	private void writeExcToLogContext(String inputMessage, String excStackTrace) {
//		// 是否還需再寫入一次 輸入參數等相關訊息???
//		LogContext logCtx = LogContext.getContext();
//		logCtx.append(excStackTrace);
////		logCtx.append("<span style=\"color: #C63300\">Exception:\t" + excStackTrace + "</span>");
//	}
//
//}
