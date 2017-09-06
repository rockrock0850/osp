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
package com.fet.crm.osp.kernel.mware.client.coes.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fet.car.client.CARServiceFactory;
import com.fet.car.client.CARServiceInvoker;
import com.fet.car.exception.CARException;
import com.fet.car.fact.APIRequest;
import com.fet.car.fact.DiscountOffer;
import com.fet.car.fact.FactObject;
import com.fet.car.fact.FactObjectArray;
import com.fet.car.fact.FactObjectMap;
import com.fet.car.fact.Mobile;
import com.fet.car.fact.PostpaidWireless;
import com.fet.car.fact.Promotion;
import com.fet.car.fact.RelatedContract;
import com.fet.car.model.ServiceCommand;
import com.fet.car.model.ServiceResult;
import com.fet.crm.osp.common.util.DateUtil;
import com.fet.crm.osp.common.vo.kernel.result.promotiondetail.DiscountOfferVO;
import com.fet.crm.osp.common.vo.kernel.result.promotiondetail.PromotionDetailRtnVO;
import com.fet.crm.osp.common.vo.kernel.result.promotiondetail.VasOfferVO;
import com.fet.crm.osp.kernel.core.common.Constant;
import com.fet.crm.osp.kernel.mware.client.coes.ICARServiceClient;
import com.fet.crm.osp.kernel.mware.exception.CARAPIException;
import com.fet.crm.osp.kernel.mware.exception.MwareExceptionFactory;

/**
 * @author RichardHuang
 */
@Service
public class CARServiceClientImpl implements ICARServiceClient {
	
	private ServiceCommand initServiceCommand() {
		try {
			ServiceCommand serviceCommand = 
					CARServiceFactory.getInstance().createServiceCommand(Constant.GET_PROMOTION_DETAILS);
			
			return serviceCommand;
			
		} catch (CARException e) {
			throw new MwareExceptionFactory().getException(e);
		}
	}

	private void setAPIRequestParam(ServiceCommand serviceCommand) {
		APIRequest apiRequest = new APIRequest();
		apiRequest.setSystemType(APIRequest.SYSTEMTYPE_WM);
		
		try {
			serviceCommand.setParam("APIRequest", apiRequest);
			
		} catch (CARException e) {
			throw new MwareExceptionFactory().getException(e);
		}
	}
	
	private void setPromotionParam(ServiceCommand serviceCommand, String promotionId) {
		Promotion promotion = new Promotion();
		promotion.setPromotionCode(promotionId);
		
		List<Promotion> promotionlist = new ArrayList<Promotion>();
		promotionlist.add(promotion);
		
		Promotion[] promotions = promotionlist.toArray(new Promotion[promotionlist.size()]);
		
		try {
			serviceCommand.setParam("Promotion", promotions);
			
		} catch (CARException e) {
			throw new MwareExceptionFactory().getException(e);
		}
	}
	
	private void setRelatedContractParam(ServiceCommand serviceCommand, String contractType) {
		RelatedContract relatedContract = new RelatedContract();
		relatedContract.setContractType(contractType);
		
		try {
			serviceCommand.setParam("RelatedContract", relatedContract);
			
		} catch (CARException e) {
			throw new MwareExceptionFactory().getException(e);
		}
	}
	
	private Promotion[] getPromotionArray(CARServiceInvoker invoker, ServiceCommand serviceCommand) {
		ServiceResult serviceResult = null;
		
		try {
			serviceResult = invoker.callService(serviceCommand);
			
		} catch (CARException e) {
			throw new MwareExceptionFactory().getException(e);
		}
		
		Assert.state(serviceResult != null, "serviceResult is null");
		
		String status = serviceResult.getStatus();
		String errtMsg = serviceResult.getErrtMsg();
		
		if (!StringUtils.equals(status, ServiceResult.succuss)) {
			throw new CARAPIException("[" + status + "] " + errtMsg);
		}
		
		FactObject factObject = serviceResult.getFactObject();
		Assert.state(factObject != null, "factObject is null");
		
		FactObjectMap factObjectMap = (FactObjectMap) factObject;
		Assert.state(factObjectMap != null, "factObjectMap is null");
		
		FactObjectArray factObjectArray = (FactObjectArray) factObjectMap.get("PromotionDetails");
		Assert.state(factObjectArray != null, "factObjectArray is null");
		
		FactObject[] factObjects = factObjectArray.getArray();
		Assert.state(ArrayUtils.isNotEmpty(factObjects), "Array of factObject is empty");
		
		Promotion[] promotions = (Promotion[]) factObjects;
		Assert.state(ArrayUtils.isNotEmpty(promotions), "Array of promotion is empty");
		
		return promotions;
	}
	
	@Override
	public PromotionDetailRtnVO getPromotionDetail(String promotionId) {
		ServiceCommand serviceCommand = initServiceCommand();
		CARServiceInvoker invoker = null;
		
		try {
			invoker = CARServiceFactory.getInstance().createServiceInvoker();
			
		} catch (CARException e) {
			throw new MwareExceptionFactory().getException(e);
		}
		
		setAPIRequestParam(serviceCommand);
		setPromotionParam(serviceCommand, promotionId);
		setRelatedContractParam(serviceCommand, "2"); // 主加附
		
		Promotion[] promotions = null;
		
		try {
			promotions = getPromotionArray(invoker, serviceCommand);
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
			setRelatedContractParam(serviceCommand, "3"); // 副約
			promotions = getPromotionArray(invoker, serviceCommand);
		}
		
		Promotion promotion = promotions[0];
		PromotionDetailRtnVO promotionDetailRtnVO = new PromotionDetailRtnVO();

		// 促代
		promotionDetailRtnVO.setAttach(promotion.getAttach());
		promotionDetailRtnVO.setPromotionCode(promotion.getPromotionCode());
		promotionDetailRtnVO.setName(promotion.getName());
		promotionDetailRtnVO.setPromotionCategoryName(promotion.getPromotionCategoryName());
		promotionDetailRtnVO.setPromotionType(promotion.getPromotionType());
		promotionDetailRtnVO.setStartDate(DateUtil.formatDt(promotion.getStartDate()));
		promotionDetailRtnVO.setEndDate(DateUtil.formatDt(promotion.getEndDate()));
		promotionDetailRtnVO.setMsisdnType(promotion.getMsisdnType());
		
		// 折扣項目
		Mobile[] discountOfferArray = promotion.getDiscountOffer();
		
		if (ArrayUtils.isNotEmpty(discountOfferArray)) {
			List<DiscountOfferVO> discountOfferList = new ArrayList<>();
			
			for (Mobile discountOffer : discountOfferArray) {
				DiscountOffer disOffer = (DiscountOffer) discountOffer;
				
				DiscountOfferVO discountOfferVO = new DiscountOfferVO();
				discountOfferVO.setId(disOffer.getId());
				discountOfferVO.setName(disOffer.getName());
				discountOfferVO.setHappyGo(disOffer.getHappyGo());
				
				discountOfferList.add(discountOfferVO);
			}
			
			promotionDetailRtnVO.setDiscountOfferList(discountOfferList);
		}
		
		// 加值項目
		Mobile[] vasOfferArray = promotion.getVasOffer();
		
		if (ArrayUtils.isNotEmpty(vasOfferArray)) {
			List<VasOfferVO> vasOfferList = new ArrayList<>();
			
			for (Mobile vasOffer : vasOfferArray) {
				PostpaidWireless postpaidWireless = (PostpaidWireless) vasOffer;
				
				VasOfferVO vasOfferVO = new VasOfferVO();
				vasOfferVO.setId(postpaidWireless.getId());
				vasOfferVO.setName(postpaidWireless.getName());
				
				vasOfferList.add(vasOfferVO);
			}
			
			promotionDetailRtnVO.setVasOfferList(vasOfferList);
		}
		
		return promotionDetailRtnVO;
	}

}
