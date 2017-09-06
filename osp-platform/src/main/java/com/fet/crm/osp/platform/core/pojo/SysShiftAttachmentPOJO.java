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

package com.fet.crm.osp.platform.core.pojo;

import java.util.Date;

/**
 *
 * @author AndrewLee
 */
public class SysShiftAttachmentPOJO {

    private String fileName;
    private byte[] content;
    private Date createDate;
    private String createUser;
    private String dtYear;
    private String dtMonth;
    private String fileExtensions;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getDtYear() {
        return dtYear;
    }

    public void setDtYear(String dtYear) {
        this.dtYear = dtYear;
    }

    public String getDtMonth() {
        return dtMonth;
    }

    public void setDtMonth(String dtMonth) {
        this.dtMonth = dtMonth;
    }

    public String getFileExtensions() {
        return fileExtensions;
    }

    public void setFileExtensions(String fileExtensions) {
        this.fileExtensions = fileExtensions;
    }

}
