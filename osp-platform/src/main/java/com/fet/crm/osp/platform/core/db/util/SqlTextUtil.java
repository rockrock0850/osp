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
 
package com.fet.crm.osp.platform.core.db.util;
 
import java.util.List;

/**
 * 產生SQL語法
 * 
 * @author VJChou
 */
public class SqlTextUtil {

    /**
     * 取得「模糊查詢」用語法. <br>
     * 組合出 (LIKE[置前] OR LIKE[置中] OR LIKE[置尾])的資料語法
     * 
     * @param columnNm
     * @param columnValue
     * @return String
     */
    public static String getFuzzyQueryExpress(String columnNm, String columnValue) {
        String template = "($P{columnNm} LIKE '%$P{columnValue}' OR $P{columnNm} LIKE '%$P{columnValue}%' OR $P{columnNm} LIKE '$P{columnValue}%')";
        
        template = template.replace("$P{columnNm}", columnNm);
        template = template.replace("$P{columnValue}", columnValue);
        
        return template;
    }
    
    /**
     * 取得「模糊查詢 & 大小寫無區分」用語法. <br>
     * 組合出 (LIKE[置前] OR LIKE[置中] OR LIKE[置尾])的資料語法
     * 
     * @param columnNm
     * @param columnValue
     * @return String
     */
    public static String getFuzzyQueryExpressWithUpperAndLower(String columnNm, String columnValue) {
        String template = 
                    "(" 
                  + "(Upper($P{columnNm}) LIKE Upper('%$P{columnValue}') "
                  + "OR Upper($P{columnNm}) LIKE Upper('%$P{columnValue}%') "
                  + "OR Upper($P{columnNm}) LIKE Upper('$P{columnValue}%')) "
                  + "OR"
                  + "(Lower($P{columnNm}) LIKE Lower('%$P{columnValue}') "
                  + "OR Lower($P{columnNm}) LIKE Lower('%$P{columnValue}%') "
                  + "OR Lower($P{columnNm}) LIKE Lower('$P{columnValue}%')) "
                  + ")";
        
        template = template.replace("$P{columnNm}", columnNm);
        template = template.replace("$P{columnValue}", columnValue);
        
        return template;
    }

    /**
     * 產生[查詢條件 IN]語法
     * 
     * @param columns
     * @return String
     */
    public static String getConditionInStr(List<String> columns) {
        StringBuffer sqltext = new StringBuffer();

        for (int i = 0; i < columns.size(); i++) {
            if (i > 0) {
                sqltext.append(", ");
            }
            sqltext.append("'");
            sqltext.append(columns.get(i)); // 字串
            sqltext.append("'");
        }

        return sqltext.toString();
    }

    /**
     * 產生[查詢]語法(select script)
     * 
     * @param table
     * @param columns
     * @return String
     */
    public static String getSelectScript(String table, String columns[]) {
        StringBuffer sqltext = new StringBuffer();

        sqltext.append("SELECT ");

        for (int i = 0; i < columns.length; i++) {
            if (i > 0) {
                sqltext.append(", ");
            }
            sqltext.append(columns[i]);
        }

        sqltext.append(" FROM ").append(table);

        return sqltext.toString();
    }

    /**
     * 產生[新增]語法(insert script)
     * 
     * @param table
     * @param columns
     * @return String
     */
    public static String getInsertScript(String table, String columns[]) {
        StringBuffer sqltext = new StringBuffer();

        sqltext.append("INSERT INTO ").append(table).append(" (");

        for (int i = 0; i < columns.length; i++) {
            if (i > 0) {
                sqltext.append(", ");
            }
            sqltext.append(columns[i]);
        }

        sqltext.append(") VALUES (");
        for (int i = 0; i < columns.length; i++) {
            if (i > 0) {
                sqltext.append(", ");
            }
            sqltext.append("?");
        }

        sqltext.append(")");

        return sqltext.toString();
    }

}