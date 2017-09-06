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
package com.fet.crm.osp.kernel.core.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * 字串加密工具
 * 
 * @author RichardHuang
 */
public class CipherUtil {
    
//    private static final String ENCODING = "MS950";
//    private static final String Algorithm = "AES";
    private static final char[] DIGIT_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F' };
    
    public static String encrypt(String paramString1, String paramString2) {
        try {
            if ((null == paramString2) || (paramString2.length() == 0)) {
                return null;
            }
            
            paramString2 = "000000000000000" + paramString2;
            paramString2 = paramString2.substring(paramString2.length() - 16, paramString2.length());
            
            byte[] arrayOfByte1 = paramString2.getBytes();
            
            SecretKeySpec localSecretKeySpec = new SecretKeySpec(arrayOfByte1, "AES");
            
            Cipher localCipher = Cipher.getInstance("AES");
            
            localCipher.init(1, localSecretKeySpec);
            
            byte[] arrayOfByte2 = paramString1.getBytes("MS950");
            byte[] arrayOfByte3 = localCipher.doFinal(arrayOfByte2);
            return bytesToHex(arrayOfByte3);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }
    
    public static String decrypt(String paramString1, String paramString2) {
        try {
            byte[] arrayOfByte1 = paramString2.getBytes();
            
            SecretKeySpec localSecretKeySpec = new SecretKeySpec(arrayOfByte1, "AES");
            
            Cipher localCipher = Cipher.getInstance("AES");
            
            localCipher.init(2, localSecretKeySpec);
            
            byte[] arrayOfByte2 = hexToBytes(paramString1);
            byte[] arrayOfByte3 = localCipher.doFinal(arrayOfByte2);
            return new String(arrayOfByte3, "MS950");
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }
    
    public static String bytesToHex(byte[] paramArrayOfByte) {
        StringBuffer localStringBuffer = new StringBuffer(paramArrayOfByte.length * 2);
        
        for (int i = 0; i < paramArrayOfByte.length; ++i) {
            int j = paramArrayOfByte[i];
            
            localStringBuffer.append(DIGIT_CHARS[((j & 0xF0) >> 4)]);
            localStringBuffer.append(DIGIT_CHARS[(j & 0xF)]);
        }
        
        return localStringBuffer.toString();
    }
    
    public static byte[] hexToBytes(String paramString) {
        int i = paramString.length();
        int j = 0;
        byte[] arrayOfByte = new byte[i / 2];
        int k = 0;
        
        while (j < i) {
            int l = Integer.parseInt(paramString.substring(j, j + 2), 16);
            
            arrayOfByte[k] = (byte) (l & 0xFF);
            j += 2;
            ++k;
        }
        
        return arrayOfByte;
    }
    
    public static void main(String[] paramArrayOfString) throws Exception {
        System.out.println(decrypt("CB0587558EC587360218C997BA6C6DE0", "1231231230123456"));
        System.out.println(encrypt("20071005181059", "1231231230123456"));
    }
    
}
