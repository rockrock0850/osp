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

package com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.content.compare;

import java.io.File;

import com.fet.crm.osp.common.vo.AbstractOspBaseVO;

/**
 *
 * @author AndrewLee
 */
public class BuzVPOSCompareVO extends AbstractOspBaseVO {

    private File vPosfile;
    private File aimsFile;
    private String userId;

    public File getvPosfile() {
        return vPosfile;
    }

    public void setvPosfile(File vPosfile) {
        this.vPosfile = vPosfile;
    }

    public File getAimsFile() {
        return aimsFile;
    }

    public void setAimsFile(File aimsFile) {
        this.aimsFile = aimsFile;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
