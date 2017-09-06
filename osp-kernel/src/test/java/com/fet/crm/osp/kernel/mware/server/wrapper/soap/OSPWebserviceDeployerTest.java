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

package com.fet.crm.osp.kernel.mware.server.wrapper.soap;

import javax.xml.ws.Endpoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 透過本機端測試發佈Webservice，並取得WSDL檔案. <br>
 * 主因在於目前整個專案的物件生成與生命週期都是透過Spring進行管理，無法透過New取得. <br>
 * 
 * @author VJChou
 */
public class OSPWebserviceDeployerTest {

    private static ApplicationContext aptx; // Spring bean管理物件
    private static String configXml = "classpath*:META-INF/applicationContext-test.xml";
    
    private static Endpoint endpoint;
    
    private static String url = "localhost";
    private static String port = "8082";

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        try {
            // step1. 將整個Spring容器啟動，並掃瞄相關設定檔
            aptx = new ClassPathXmlApplicationContext(new String[] { configXml });
            
            // step2. 透過Spring容器，取得指定對象的Bean實例物件 ()
            TicketPoolMainSOAPWS ticketPoolMainWS = (TicketPoolMainSOAPWS) aptx.getBean("TicketPoolMainSOAPWS");
            OSPPoolMainSOAPWS ospPoolMainWS = (OSPPoolMainSOAPWS) aptx.getBean("OSPPoolMainSOAPWS");
            OSPToolMainSOAPWS ospToolMainWS = (OSPToolMainSOAPWS) aptx.getBean("OSPToolMainSOAPWS");

            String publishL_1 = "http://" + url + ":" + port + "/TicketPoolMainService";
            String publishL_2 = "http://" + url + ":" + port + "/OSPPoolMainService";
            String publishL_3 = "http://" + url + ":" + port + "/OSPToolMainService";
            
            endpoint = Endpoint.publish(publishL_1, ticketPoolMainWS);
            endpoint = Endpoint.publish(publishL_2, ospPoolMainWS);
            endpoint = Endpoint.publish(publishL_3, ospToolMainWS);

            if (endpoint.isPublished()) {
                System.out.println("已成功發佈 Webservice，請透過以下連結存取 WSDL");
                System.out.println();
                System.out.println(publishL_1 + "?wsdl");
                System.out.println(publishL_2 + "?wsdl"); 
                System.out.println(publishL_3 + "?wsdl"); 
            } else {
                System.out.println("發佈 Webservice失敗!!");
            }
        } finally {
            /*
            if (endpoint != null) {
                endpoint.stop();
            }
            if (aptx != null) {
                ;
            }
            */
        }
    }

}
