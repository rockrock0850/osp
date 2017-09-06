package com.fet.crm.osp.kernel.core.common.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.fet.crm.osp.kernel.core.common.aspect.annotation.Param;
import com.fet.crm.osp.kernel.core.common.util.DateUtil;
import com.fet.crm.osp.kernel.core.common.util.JsonUtil;

/**
 * 攔截器 資訊解析 共用物件
 * 
 * @author PaulChen 
 */
public abstract class AbstractAspectParser {

	/**
	 * 記錄執行完畢的METHOD資訊
	 * 
	 * @param pjp
	 * @return String
	 */
	protected String processOutputMessage(JoinPoint pjp) {
		String time = DateUtil.formatDt(new Date(), DateUtil.DATE_TIME_PATTERN);
		
		StringBuffer message = new StringBuffer();
		message.append("<span style=\"color: blue\">" + time + "</span>");
		message.append("&nbsp;&nbsp;&nbsp;OUTPUT【<span style='color: red'>");
		message.append(pjp.getTarget().getClass());
		message.append("</span>】METHOD【<span style=\"color: brown\">" + pjp.getSignature().getName());
		message.append("</span>】");
		
		return message.toString();
	}
	
	/**
	 * 將回傳值轉為JSON物件
	 * 
	 * @param rtnValue
	 * @return
	 */
	protected String processReturnValues(Object rtnValue) {
		StringBuffer message = new StringBuffer();
		message.append("<span style=\"color: green\">&nbsp;&nbsp;&nbsp;RETURN&nbsp;&nbsp;&nbsp;VALUE&nbsp;&nbsp;");
		message.append(JsonUtil.toJson(rtnValue));
		message.append("</span>");
		
		return message.toString();
	}
	
	/**
	 * 將傳入參數轉換為JSON字串
	 * 
	 * @param args
	 * @return String
	 */
	protected String processInputMessage(JoinPoint pjp) {
		String time = DateUtil.formatDt(new Date(), DateUtil.DATE_TIME_PATTERN);
		
		StringBuffer message = new StringBuffer();
		message.append("<span style=\"color: blue\">" + time + "</span>");
		message.append("&nbsp;&nbsp;&nbsp;INPUT【<span style='color: red'>");
		message.append(pjp.getTarget().getClass());
		message.append("</span>】");
		
		MethodSignature ms = (MethodSignature) pjp.getSignature();
		// 取得傳入參數
		Object[] inputParams = pjp.getArgs();
		
		// 取得參數上的 annotation
		Method m = ms.getMethod();
		Annotation[][] paramAnno = m.getParameterAnnotations();
		
		message.append("&nbsp;&nbsp;&nbsp;METHOD【<span style=\"color:brown\">");
		message.append(m.getName());
		message.append("</span>】");
		
		for(int i = 0 ; i < inputParams.length ; i++) {
			Object input = inputParams[i];
			Annotation[] annos = paramAnno[i];
			boolean useDefault = true;
			// 若程式無加入「@Param」則使用預設的訊息
			if(annos.length != 0) {
				Annotation a = annos[0];
				if(Param.class.equals(a.annotationType())) {
					Param p = (Param) a;
					String paramDesc = p.value();
					
					message.append("&nbsp;&nbsp;&nbsp;");
					message.append(paramDesc + " = ");
					useDefault = false;
				}
			} 
			
			if(useDefault) {
				message.append("&nbsp;&nbsp;&nbsp;參數 " + (i + 1) + " = ");
			}
			
			message.append(JsonUtil.toJson(input)); // 以JSON格式，將物件資訊記錄
			message.append(",&nbsp;&nbsp;&nbsp;");
		}
		
		return message.toString();
	}
	
}
