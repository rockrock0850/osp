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
package com.fet.crm.osp.kernel.core.pojo;

/**
 * @author RichardHuang
 */
public class NCPSystemConfigPOJO {
    
    String configName;
    String configValue;
    
    /**
     * @return the configName
     */
    public String getConfigName() {
        return configName;
    }
    
    /**
     * @param configName the configName to set
     */
    public void setConfigName(String configName) {
        this.configName = configName;
    }
    
    /**
     * @return the configValue
     */
    public String getConfigValue() {
        return configValue;
    }
    
    /**
     * @param configValue the configValue to set
     */
    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    @Override
    public String toString() {
        return "NCPSystemConfigPOJO [configName=" + configName + ", configValue=" + configValue + "]";
    }
    
}
