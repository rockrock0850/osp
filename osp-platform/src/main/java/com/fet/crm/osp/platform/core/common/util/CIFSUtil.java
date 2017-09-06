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

package com.fet.crm.osp.platform.core.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

/**
 * [遠端磁碟機] Common Internet File System 網路文件共享系統 相關工具
 *
 * @author AndrewLee
 */
public class CIFSUtil {
    
    /**
     *  將檔案上傳至遠端磁碟機
     * 
     * @param file 檔案   
     * @param remoteUrl 遠端磁碟機 URL
     * @param account 遠端磁碟機 帳號
     * @param password 遠端磁碟機 密碼
     */
    public static void createSmbFile(File file, String remoteUrl,String account,String password) {
        NtlmPasswordAuthentication auth = null;
        SmbFileOutputStream outputSmbFileStream = null;
        FileInputStream fileInputStream = null;
        
        try {
        //防呆
        if(StringUtils.isNotBlank(account) && StringUtils.isNotBlank(password)) {
            StringBuffer sbf = new StringBuffer();
            sbf.append(account).append(":").append(password);
            
            auth = new NtlmPasswordAuthentication(sbf.toString());
        }
        
            String fileName = file.getName();
            
            SmbFile smbFile = new SmbFile(remoteUrl + "/" + fileName, auth);
            
            outputSmbFileStream = new SmbFileOutputStream(smbFile);
            fileInputStream = new FileInputStream(file);
            
            byte[] buf = new byte[1024];
            int length = 0;
            
            while ((length = fileInputStream.read(buf)) > 0) {
                outputSmbFileStream.write(buf, 0, length);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            
            try {
                if (outputSmbFileStream != null) {
                    outputSmbFileStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    /**
     *  將檔案上傳至遠端磁碟機,登錄磁碟機時時附帶網域
     * 
     * @param file 檔案   
     * @param remoteUrl 遠端磁碟機 URL
     * @param account 遠端磁碟機 帳號
     * @param password 遠端磁碟機 密碼
     * @param domain 網域
     */
    public static void createSmbFileWithDomain(File file, String remoteUrl,String account,String password,String domain) {
        NtlmPasswordAuthentication auth = null;
        SmbFileOutputStream outputSmbFileStream = null;
        FileInputStream fileInputStream = null;
        
        try {
        //防呆,若無domain則直接把帳號跟密碼串在一起
        if(StringUtils.isNotBlank(account) && StringUtils.isNotBlank(password) && StringUtils.isNotBlank(domain)) {
            auth = new NtlmPasswordAuthentication(domain,account,password);
        
        } else {
            StringBuffer sbf = new StringBuffer();
            sbf.append(account).append(":").append(password);
            
            auth = new NtlmPasswordAuthentication(sbf.toString());
        }
        
            String fileName = file.getName();
            
            SmbFile smbFile = new SmbFile(remoteUrl + "/" + fileName, auth);
            
            outputSmbFileStream = new SmbFileOutputStream(smbFile);
            fileInputStream = new FileInputStream(file);
            
            byte[] buf = new byte[1024];
            int length = 0;
            
            while ((length = fileInputStream.read(buf)) > 0) {
                outputSmbFileStream.write(buf, 0, length);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            
            try {
                if (outputSmbFileStream != null) {
                    outputSmbFileStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
