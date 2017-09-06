/**
 * Copyright (c) 2015 Far Eastone Telecommunications Co., Ltd.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of 
 * Far Eastone Telecommunications Co., Ltd. ("Confidential Information"). 
 * 
 * You shall not disclose such Confidential Information and shall use it 
 * only in accordance with the terms of license agreement you entered
 * into with Far Eastone Telecommunications Co., Ltd.
 */

package com.fet.crm.osp.common.exception.util;

/**
 * 例外 Stack Trace 訊息抽取工具 類別
 * 
 * @author PaulChen
 */
public class ExceptionStackExtractor {

    private static final int LIMIT_STACK_TRACE_COUNT = 9;

    /**
     * 將 Exception stack trace element 抽取出
     * 
     * @param e
     * @return String
     */
    public static String extractExceptionStack(Throwable e) {
        return extractExceptionStack(e, new StringBuffer());
    }

    /**
     * 將 Exception stack trace element 資訊寫入StringBuffer中
     * 
     * @param e
     * @param sb
     * @return String
     */
    public static String extractExceptionStack(Throwable e, StringBuffer sb) {
        return extractExceptionStack(e, sb, LIMIT_STACK_TRACE_COUNT);
    }

    /**
     * 將 Exception stack trace element 資訊寫入StringBuffer中
     * 
     * @param e
     * @param sb
     * @param limitCount
     * @return String
     */
    public static String extractExceptionStack(Throwable e, StringBuffer sb, int limitCount) {
        String excMsg = e.getMessage();

        sb.append(e.getClass().getName());
        sb.append("&nbsp;&nbsp;&nbsp;");

        if (excMsg != null) {
            excMsg = excMsg.replace("\r", "").replace("\n", "");
            sb.append(excMsg);
        }

        sb.append("<br/>");

        StackTraceElement[] element = e.getStackTrace();
        int index = 0;
        for (StackTraceElement st : element) {
            if (index > limitCount) {
                break;
            }

            sb.append("&nbsp;&nbsp;&nbsp;");
            sb.append(st);
            sb.append("<br/>");

            index++;
        }

        if (e.getCause() != null) { // 遞迴
            sb.append("Caused by:");

            return extractExceptionStack(e.getCause(), sb);
        }

        // 將字串「"」雙引號前加入跳脫字元「\」
        index = 0;

        while ((index = sb.indexOf("\"", index)) != -1) {
            sb.insert(index, "\\");
            index += 2;
        }

        return sb.toString();
    }

}
