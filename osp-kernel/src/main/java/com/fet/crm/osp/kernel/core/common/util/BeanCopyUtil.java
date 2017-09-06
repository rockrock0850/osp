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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * [資料封裝物件 物件複製] 工具類別
 * 
 * @author LawrenceLai, VJChou
 */
public class BeanCopyUtil {

    /**
     * 提取NULL資料，略過複製行為。
     * 
     * @param object
     * @return String[]
     */
    @SuppressWarnings("rawtypes")
    public static String[] null2Ignore(Object object) {
        List<String> properties = new ArrayList<String>();

        try {
            Class clazz = object.getClass();
            Field[] declaredFields = clazz.getDeclaredFields();

            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);

                if (declaredField.get(object) == null) {
                    String name = declaredField.getName();
                    properties.add(name);
                }
            }

            return properties.toArray(new String[properties.size()]);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return new String[0];
    }

}
