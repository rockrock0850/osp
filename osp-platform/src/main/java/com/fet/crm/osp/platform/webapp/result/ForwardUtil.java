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

package com.fet.crm.osp.platform.webapp.result;

/**
 * 
 * @author Adam Yeh, AndrewLee
 */
public enum ForwardUtil {

	/* 按鈕清單功能頁面 */
    OSPLV2001("tiles.function.ospLv2001"),

    OSPLV2002("tiles.function.ospLv2002"),

    OSPLV2004("tiles.function.ospLv2004"),

    OSPLV2005("tiles.function.ospLv2005"),

    OSPLV2006("tiles.function.ospLv2006"),

    OSPLV2007("tiles.function.ospLv2007"),

    OSPLV2008("tiles.function.ospLv2008"),

    OSPLV2009("tiles.function.ospLv2009"),

    OSPLV2010("tiles.function.ospLv2010"),

    OSPLV2011("tiles.function.ospLv2011"),

    OSPLV2012("tiles.function.ospLv2012"),

    OSPLV4001("tiles.function.ospLv4001"),
    
    OSPLV4002("tiles.function.ospLv4002"),
    
    OSPLV4003("tiles.function.ospLv4003"),
    
    OSPLV4004("tiles.function.ospLv4004"),
    
    OSPLV4005("tiles.function.ospLv4005"),
    
    OSPLV4006("tiles.function.ospLv4006"),

    OSPLV4007("tiles.function.ospLv4007"),
    
    OSPLV4008("tiles.function.ospLv4008"),
    
    OSPLV4009("tiles.function.ospLv4009"),
    
    OSPLV4010("tiles.function.ospLv4010"),
    
    OSPLV4011("tiles.function.ospLv4011"),
    
    OSPLV4012("tiles.function.ospLv4012"),
    
    OSPLV4013("tiles.function.ospLv4013"),
    
    OSPLV4014("tiles.function.ospLv4014"),
    
    OSPLV4015("tiles.function.ospLv4015"),
    
    OSPLV4016("tiles.function.ospLv4016"),
    
    OSPLV4017("tiles.function.ospLv4017"),
    
    OSPLV4018("tiles.function.ospLv4018"),
    
    OSPLV4019("tiles.function.ospLv4019"),
    
    OSPLV4020("tiles.function.ospLv4020"),

    OSPLV4021("tiles.function.ospLv4021"),

    OSPLV4022("tiles.function.ospLv4022"),
    
    OSPLV4023("tiles.function.ospLv4023"),
    
    OSPLV4024("tiles.function.ospLv4024"),
    
    OSPLV4025("tiles.function.ospLv4025"),
    
    OSPLV4026("tiles.function.ospLv4026"),
    
    OSPLV4027("tiles.function.ospLv4027"),
    
    OSPLV4028("tiles.function.ospLv4028"),
    
    OSPLV4029("tiles.function.ospLv4029"),
    
    OSPLV4030("tiles.function.ospLv4030"),

    OSPLV4031("tiles.function.ospLv4031"),
    
    OSPLV4032("tiles.function.ospLv4032"),
    
    OSPLV4033("tiles.function.ospLv4033"),
    
    OSPLV4034("tiles.function.ospLv4034"),
    
    OSPLV4035("tiles.function.ospLv4035"),

    OSPLV4036("tiles.function.ospLv4036"),
    
    OSPLV4037("tiles.function.ospLv4037"),

    OSPLV4038("tiles.function.ospLv4038"),
    
    OSPLV4039("tiles.function.ospLv4039"),

    OSPLV4040("tiles.function.ospLv4040"), 
    
    OSPLV4041("tiles.function.ospLv4041"), 
    
    OSPLV4042("tiles.function.ospLv4042"), 
    
    OSPLV4043("tiles.function.ospLv4043"), 
    
    OSPLV4044("tiles.function.ospLv4044"), 
    
    OSPLV4045("tiles.function.ospLv4045"), 
    
    OSPLV4046("tiles.function.ospLv4046"), 
    
    OSPLV4047("tiles.function.ospLv4047"), 
    
    OSPLV4048("tiles.function.ospLv4048"), 
    
    OSPLV4049("tiles.function.ospLv4049"), 
    
    OSPLV4050("tiles.function.ospLv4050"), 
    
    OSPLV4051("tiles.function.ospLv4051"), 
    
    OSPLV4052("tiles.function.ospLv4052"), 
    
    OSPLV4053("tiles.function.ospLv4053"), 
    
    OSPLV4054("tiles.function.ospLv4054"), 
    
    /* 回傳JSON訊息頁面 */
    JSON("tiles.data.json"),
	
    /* 下拉選單資料頁面 */
	OPTION_SELECTION("tiles.option.selection"),  
	
	/* 功能頁簽內容 */
	CONT0013("tiles.flow.cont0013"),  

	CONT0014("tiles.flow.cont0014"),  
	
	CONT0019("tiles.flow.cont0019"), 

	CONT0022("tiles.flow.cont0022"),  

	CONT0023("tiles.flow.cont0023"),  

	CONT0024("tiles.flow.cont0024"),

	CONT0026("tiles.flow.cont0026"),
	
	CONT0025("tiles.flow.cont0025"),
	
	CONT0027("tiles.flow.cont0027"), 
	
    CONT0068("tiles.flow.cont0068"),
    
    CONT0069("tiles.flow.cont0069"),
    
    CONT0070("tiles.flow.cont0070"),
    
    /* 案件影像彈跳視窗  */
    SPVGRIDVIEW("tiles.window.SPV"),
	
	APPPARTVIEW("tiles.view.apppart");

    private String name;

    private ForwardUtil(String name) {
        this.name = name;
    }

    public String getView() {

        return name;
    }

}
