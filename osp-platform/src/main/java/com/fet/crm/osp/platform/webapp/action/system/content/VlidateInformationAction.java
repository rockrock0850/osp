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

package com.fet.crm.osp.platform.webapp.action.system.content;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.mware.client.restful.proxy.OSPKernelRESTClientProxy;
import com.fet.crm.osp.platform.mware.client.vo.AuthLevelInfoReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.AuthLevelInfoVO;
import com.fet.crm.osp.platform.mware.client.vo.BillDetailVO;
import com.fet.crm.osp.platform.mware.client.vo.CacheSubscriberInfoVO;
import com.fet.crm.osp.platform.mware.client.vo.CheckCustInfoReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.CustBillingInfoReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.CustBillingInfoVO;
import com.fet.crm.osp.platform.mware.client.vo.CustInfoForOSPReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.IdViewInfoVO;
import com.fet.crm.osp.platform.mware.client.vo.InvoiceInfoVO;
import com.fet.crm.osp.platform.mware.client.vo.SpecialOfferVO;
import com.fet.crm.osp.platform.webapp.action.AbstractOSPAction;
import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;
import com.fet.crm.osp.platform.webapp.handler.HttpSessionHandler;
import com.fet.crm.osp.platform.webapp.result.ForwardUtil;
import com.fet.crm.osp.platform.webapp.vo.session.SessionVO;

/**
 * [CONT0023_核資] 前端控制器 ( 參考文件: 客製頁簽說明.docx )<br>
 *
 * @author Adam Yeh, AllenChen
 */
@Controller
@RequestMapping("/flow/content")
public class VlidateInformationAction extends AbstractOSPAction {

	@Autowired
	private OSPKernelRESTClientProxy proxy;

    @RequestMapping(value = "/cont-0023", method = RequestMethod.POST)
    public String getContent0023() {
        return ForwardUtil.CONT0023.getView();
    }

    @RequestMapping(value = "/get-bus-cust-info", method = RequestMethod.POST)
    public String getBusCustInfo(@RequestParam String msisdn, @RequestParam String rocId) {
    	SessionVO sessionVO = HttpSessionHandler.getSessionInfo();
      	String ntAccount = sessionVO.getUserInfoVO().getNtAccount();
    	String userId = HttpSessionHandler.getSessionInfo().getUserInfoVO().getUserId();
    	
    	CacheSubscriberInfoVO cacheSubscriberInfoVO = proxy.getCacheSubscriberInfoByMsisdn(msisdn, ntAccount);

    	String subscriberId = "";
    	String accountId = "";
    	String paymentCategory = "";

    	List<BillDetailVO> billDetailVOList = null;

    	if (cacheSubscriberInfoVO != null) {
    		subscriberId = cacheSubscriberInfoVO.getSubscriberId();
    		accountId = cacheSubscriberInfoVO.getAccountId();
    		paymentCategory = cacheSubscriberInfoVO.getPaymentCategory();
    	}

    	// 取得sim
    	String sim = proxy.getSimIdByMsisdn(paymentCategory, msisdn, ntAccount);

    	// 取得基本資訊
    	CustInfoForOSPReturnVO custInfoForOSPReturnVO = proxy.getCustInfoForOSP(subscriberId, ntAccount);
    	
    	if (custInfoForOSPReturnVO != null) {
    		custInfoForOSPReturnVO.setSim(sim);
    	}

    	CustBillingInfoVO custBillingInfoVO = new CustBillingInfoVO();
    	custBillingInfoVO.setAccountId(accountId);
    	custBillingInfoVO.setSubscriberId(subscriberId);

    	// 取得bill資訊
    	CustBillingInfoReturnVO custBillingInfoReturnVO = proxy.getCustBillingInfo(custBillingInfoVO, ntAccount);

	    if (custBillingInfoReturnVO != null) {
        	// 取得bill detail資訊
	    	List<InvoiceInfoVO> invoiceInfoVOList = custBillingInfoReturnVO.getInvoiceInfoList();

        	if (invoiceInfoVOList != null) {
        		// 整理 帳務詳細資訊table 所需資訊
            	billDetailVOList = prepareBillDetailInfo(invoiceInfoVOList);
        	}
	    }

    	AuthLevelInfoVO paramVO = new AuthLevelInfoVO();
    	paramVO.setEmpNo(userId);
    	paramVO.setRocId(rocId);
    	
    	AuthLevelInfoReturnVO authLevelInfoReturnVO = proxy.getAuthLevelInfo(paramVO, ntAccount);
    	
    	// 裝填核資頁所需資訊的vo
    	CheckCustInfoReturnVO checkCustInfoReturnVO = new CheckCustInfoReturnVO();

    	checkCustInfoReturnVO.setCustInfoForOSPReturnVO(custInfoForOSPReturnVO);
    	checkCustInfoReturnVO.setCustBillingInfoReturnVO(custBillingInfoReturnVO);
    	checkCustInfoReturnVO.setAuthLevelInfoReturnVO(authLevelInfoReturnVO);
    	checkCustInfoReturnVO.setBillDetailVOList(billDetailVOList);

		String responseData = JsonUtil.toJson(checkCustInfoReturnVO);
		HttpRequestHandler.put("responseData", responseData);

		return ForwardUtil.JSON.getView();
    }

	@RequestMapping(value = "/get-special-offer-info", method = RequestMethod.POST)
    public String getSpecialOfferInfo(@RequestParam String rocId) {
		SessionVO sessionVO = HttpSessionHandler.getSessionInfo();
      	String ntAccount = sessionVO.getUserInfoVO().getNtAccount();
		
		String responseData = JsonUtil.toJson(Collections.emptyList());

		if (StringUtils.isNotBlank(rocId)) {
			List<SpecialOfferVO> specialOfferVOList = proxy.getSpecialOfferByRocId(rocId, ntAccount);

			responseData = JsonUtil.toJson(specialOfferVOList);
		}

		HttpRequestHandler.put("responseData", responseData);

		return ForwardUtil.JSON.getView();
    }

	@RequestMapping(value = "/get-id-view-info", method = RequestMethod.POST)
    public String getIdViewInfo(@RequestParam String rocId) {
		SessionVO sessionVO = HttpSessionHandler.getSessionInfo();
      	String ntAccount = sessionVO.getUserInfoVO().getNtAccount();
		
		String responseData = JsonUtil.toJson(Collections.emptyList());

		if (StringUtils.isNotBlank(rocId)) {
			List<IdViewInfoVO> idViewInfoVOList = proxy.getIdViewInfoByRocId(rocId, ntAccount);

			responseData = JsonUtil.toJson(idViewInfoVOList);
		}

		HttpRequestHandler.put("responseData", responseData);

		return ForwardUtil.JSON.getView();
    }
	
    private List<BillDetailVO> prepareBillDetailInfo(List<InvoiceInfoVO> invoiceList) {
    	// 用以裝填分類後的兩類帳單  電信(T) 小額(M)
    	List<BillDetailVO> tBillList = new ArrayList<>();
    	List<BillDetailVO> mBillList = new ArrayList<>();

    	// 記得判斷 NullpointException
    	if (CollectionUtils.isNotEmpty(invoiceList)) {
    		// step 1 : 先分成兩類:  電信(T) 小額(M)
        	for (int i = 0; i < invoiceList.size(); i++) {
        		BillDetailVO billDetailVO = new BillDetailVO();

        		// 取得每筆vo
        		InvoiceInfoVO invoiceInfoVO = invoiceList.get(i);

        		// 填入 tempBillDetailVO
        		BeanUtils.copyProperties(invoiceInfoVO, billDetailVO);

        		// 給定順序
        		billDetailVO.setSortNumber(i);

        		// 判斷是電信 (T) 還是小額(M) 分別放入 tBillList  mBillList
        		if ("T".equals(invoiceInfoVO.getSubBE())) {
        			tBillList.add(billDetailVO);
        		} else {
        			mBillList.add(billDetailVO);
        		}
        	}

        	// step 2 比對日期相同， 將小額併入電信
        	String tTotalAmonut = null;
        	String mTotalAmount = null;
        	String tBalance = null;
        	String mBalance = null;

        	int iTTotalAmonut = 0;
        	int iMTotalAmount = 0;
        	int iTBalance = 0;
        	int iMBalance = 0;

        	// 將加總後(電信+小額) 併入 電信中
        	for (BillDetailVO tvo : tBillList) {

    			// 取出 電信 新增費用、帳單餘額
    			tTotalAmonut = tvo.getTotalAmount();
    			tBalance = tvo.getInvoiceBalance();

    			// 轉int
    			iTTotalAmonut = Integer.valueOf(tTotalAmonut);
    			iTBalance = Integer.valueOf(tBalance);

        		for (BillDetailVO mvo : mBillList) {

        			// 若日期相同  將小額併入電信
        			if (tvo.getStatementDate() == mvo.getStatementDate()) {

        				// 取出 小額 新增費用、帳單餘額
        				mTotalAmount = mvo.getTotalAmount();
        				mBalance = mvo.getInvoiceBalance();

        				iMTotalAmount = Integer.valueOf(mTotalAmount);
        				iMBalance = Integer.valueOf(mBalance);

        				iTTotalAmonut += iMTotalAmount;
        				iTBalance += iMBalance;

        				break;
        			}
        		}

        		// 總新增費用、當期未繳餘額
        		tvo.setSumAmount(String.valueOf(iTTotalAmonut));
        		tvo.setSumBalance(String.valueOf(iTBalance));
        	}

        	// step 3 再把兩組vo合併
        	tBillList.addAll(mBillList);

        	// step4 依sortNumber 對tBillList排序
            Collections.sort(tBillList,
            new Comparator<BillDetailVO>() {
                public int compare(BillDetailVO o1, BillDetailVO o2) {
                    return o1.getSortNumber() - o2.getSortNumber();
                }
            });

        	return tBillList;
    	}

    	return Collections.emptyList();
	}

}
