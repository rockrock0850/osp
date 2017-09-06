package com.fet.crm.osp.platform.core.facade.orderinfo;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fet.crm.osp.platform.SpringTest;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.vo.systeminfo.OperateManageVO;

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

/**
 * 
 * @author Adam
 */
public class OperateManageFacadeTest extends SpringTest {

	@Autowired
	private OperateManageFacade facade;

	@Test
	public void testCreateOperateContent() {
		// 注意: 測試資料不一定正確, 因為測試資料裡面需要日期時間, 且日期時間是要每天變動的, 所以測資裡面的日期在每次測試的時候都要手動更新一次, 否則測試結果會不正確
		String data = "{\"id_ROUT001\":\"ROUT001\",\"sort_ROUT001\":\"1\",\"unit_ROUT001\":\"件\",\"name_ROUT001\":\"Log Fail-Postpaid/Prepaid\",\"reserv_ROUT001\":\"23\",\"create_date_ROUT001\":\"2017-05-04 03:24:01.0\",\"id_ROUT002\":\"ROUT002\",\"sort_ROUT002\":\"2\",\"unit_ROUT002\":\"件\",\"name_ROUT002\":\"Log-啟用\",\"reserv_ROUT002\":\"44\",\"create_date_ROUT002\":\"2017-05-04 03:24:01.0\",\"id_ROUT003\":\"ROUT003\",\"sort_ROUT003\":\"3\",\"unit_ROUT003\":\"件\",\"name_ROUT003\":\"公用信箱-Activation Team\",\"reserv_ROUT003\":\"999\",\"create_date_ROUT003\":\"2017-05-04 03:24:01.0\",\"id_ROUT004\":\"ROUT004\",\"sort_ROUT004\":\"4\",\"unit_ROUT004\":\"件\",\"name_ROUT004\":\"公用信箱-Prepaid Team\",\"reserv_ROUT004\":\"\",\"create_date_ROUT004\":\"2017-05-04 03:24:01.0\",\"id_ROUT005\":\"ROUT005\",\"sort_ROUT005\":\"5\",\"unit_ROUT005\":\"件\",\"name_ROUT005\":\"公用信箱-異動\",\"reserv_ROUT005\":\"\",\"create_date_ROUT005\":\"2017-05-04 03:24:01.0\",\"id_ROUT006\":\"ROUT006\",\"sort_ROUT006\":\"6\",\"unit_ROUT006\":\"件\",\"name_ROUT006\":\"EBU攜碼\",\"reserv_ROUT006\":\"\",\"create_date_ROUT006\":\"2017-05-04 03:24:01.0\",\"id_ROUT007\":\"ROUT007\",\"sort_ROUT007\":\"7\",\"unit_ROUT007\":\"分\",\"name_ROUT007\":\"E-VPOS核帳\",\"reserv_ROUT007\":\"10\",\"create_date_ROUT007\":\"2017-05-04 03:24:01.0\",\"id_ROUT008\":\"ROUT008\",\"sort_ROUT008\":\"8\",\"unit_ROUT008\":\"件\",\"name_ROUT008\":\"預付大量開卡\",\"reserv_ROUT008\":\"\",\"create_date_ROUT008\":\"2017-05-04 03:24:01.0\",\"id_ROUT009\":\"ROUT009\",\"sort_ROUT009\":\"9\",\"unit_ROUT009\":\"件\",\"name_ROUT009\":\"移入協商-傳真\",\"reserv_ROUT009\":\"9999999\",\"create_date_ROUT009\":\"2017-05-04 03:24:01.0\",\"id_ROUT010\":\"ROUT010\",\"sort_ROUT010\":\"10\",\"unit_ROUT010\":\"件\",\"name_ROUT010\":\"移入協商-查詢\",\"reserv_ROUT010\":\"\",\"create_date_ROUT010\":\"2017-05-04 03:24:01.0\",\"id_ROUT011\":\"ROUT011\",\"sort_ROUT011\":\"11\",\"unit_ROUT011\":\"件\",\"name_ROUT011\":\"移出批核\",\"reserv_ROUT011\":\"\",\"create_date_ROUT011\":\"2017-05-04 03:24:01.0\",\"id_ROUT012\":\"ROUT012\",\"sort_ROUT012\":\"12\",\"unit_ROUT012\":\"件\",\"name_ROUT012\":\"公用信箱-移出協商回覆\",\"reserv_ROUT012\":\"\",\"create_date_ROUT012\":\"2017-05-04 03:24:01.0\",\"id_ROUT013\":\"ROUT013\",\"sort_ROUT013\":\"13\",\"unit_ROUT013\":\"件\",\"name_ROUT013\":\"公用信箱-移出問題回覆\",\"reserv_ROUT013\":\"\",\"create_date_ROUT013\":\"2017-05-04 03:24:01.0\"}";
		Map<String , Object> dataMap = JsonUtil.fromJson(data, Map.class);
		OperateManageVO vo = new OperateManageVO();
		vo.setContentId("CONT0013");
		vo.setUserId("65196");
		vo.setContentData(dataMap);
    	
        boolean result = facade.createOperateContent(vo);

        System.out.println("==================================================");
        System.out.println(result);
        System.out.println("==================================================");
	}

}


