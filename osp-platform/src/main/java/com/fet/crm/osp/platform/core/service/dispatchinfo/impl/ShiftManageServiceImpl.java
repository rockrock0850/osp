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

package com.fet.crm.osp.platform.core.service.dispatchinfo.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.platform.core.common.util.DateUtil;
import com.fet.crm.osp.platform.core.common.util.ExcelParseUtil;
import com.fet.crm.osp.platform.core.common.util.ResourceFileUtil;
import com.fet.crm.osp.platform.core.db.condition.Condition;
import com.fet.crm.osp.platform.core.db.condition.api.Restrictions;
import com.fet.crm.osp.platform.core.db.jdbc.JdbcDAO;
import com.fet.crm.osp.platform.core.db.warehouse.ShiftTypeSettingWareHouse;
import com.fet.crm.osp.platform.core.db.warehouse.ShiftTypeSkillsMapWareHouse;
import com.fet.crm.osp.platform.core.db.warehouse.SysShiftAttachmentWareHouse;
import com.fet.crm.osp.platform.core.excpetion.validate.ExcelException;
import com.fet.crm.osp.platform.core.pojo.ShiftSkillMapPOJO;
import com.fet.crm.osp.platform.core.pojo.ShiftTypePOJO;
import com.fet.crm.osp.platform.core.pojo.SysShiftAttachmentPOJO;
import com.fet.crm.osp.platform.core.service.dispatchinfo.IShiftManageService;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.ShiftMaintainVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.ShiftTypeSettingVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.ShiftTypeSkillMapVO;
import com.fet.crm.osp.platform.core.vo.dispatchinfo.condition.ShiftTypeInfoCVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.component.FileVO;

/**
 * [班表管理] 服務界面
 *
 * @author AndrewLee, AllenChen
 */
@Service
public class ShiftManageServiceImpl implements IShiftManageService {

    @Autowired
    private JdbcDAO jdbcDAO;

    @Autowired
    private ShiftTypeSettingWareHouse shiftTypeSettingWareHouse;

    @Autowired
    private ShiftTypeSkillsMapWareHouse shiftTypeSkillMapWareHouse;

    @Autowired
    private SysShiftAttachmentWareHouse sysShiftAttachmentWareHouse;
    
    @Override
    public List<ShiftTypeSettingVO> queryShiftTypeInfo() {
    	String sqlText = ResourceFileUtil.SQL_SHIFT.getResource("QUERY_SHIFT_TYPE_INFO_BY_CONDITION");

        Condition condition = new Condition();
        
        sqlText = condition.getCompleteSQL(sqlText);

        Map<String, Object> params = condition.getParams();

        List<ShiftTypeSettingVO> rtnList = jdbcDAO.queryForBean(sqlText, params, ShiftTypeSettingVO.class);
        
        if (CollectionUtils.isNotEmpty(rtnList)) {
        	return rtnList;
        }

        return Collections.emptyList();
    }

    @Override
    public List<ShiftTypeSettingVO> queryShiftTypeInfo(ShiftTypeInfoCVO cvo) {
        if (cvo != null) {
            String shiftTypeName = cvo.getShiftTypeId();
            String shiftTypeId = cvo.getShiftTypeName();

            String sqlText = ResourceFileUtil.SQL_SHIFT.getResource("QUERY_SHIFT_TYPE_INFO_BY_CONDITION");

            Condition condition = new Condition();

            if (StringUtils.isNotBlank(shiftTypeName)) {
                condition.and(Restrictions.like("SHIFT_TYPE_NAME", shiftTypeName));
            }

            if (StringUtils.isNotBlank(shiftTypeId)) {
                condition.and(Restrictions.like("SHIFT_TYPE_ID", shiftTypeId));
            }

            sqlText = condition.getCompleteSQL(sqlText);

            Map<String, Object> params = condition.getParams();

            List<ShiftTypeSettingVO> rtnList = jdbcDAO.queryForBean(sqlText, params, ShiftTypeSettingVO.class);

            return rtnList;
        }

        return Collections.emptyList();
    }

    @Override
    public boolean createShiftType(ShiftTypeSettingVO shifttypeSettingVO) {
        if (shifttypeSettingVO != null) {

            String shiftTypeId = shifttypeSettingVO.getShiftTypeId();
            String shiftTypeName = shifttypeSettingVO.getShiftTypeName();
            String startTime = shifttypeSettingVO.getStartTime();
            String endTime = shifttypeSettingVO.getEndTime();
            String breakStartTime = shifttypeSettingVO.getBreakStartTime();
            String breakEndTime = shifttypeSettingVO.getBreakEndTime();
            String description = shifttypeSettingVO.getDescription();
            String createUser = shifttypeSettingVO.getCreateUser();
            String updateUser = createUser;

            ShiftTypePOJO pojo = new ShiftTypePOJO();
            pojo.setShiftTypeId(shiftTypeId);
            pojo.setShiftTypeName(shiftTypeName);
            pojo.setStartTime(startTime);
            pojo.setEndTime(endTime);
            pojo.setBreakStartTime(breakStartTime);
            pojo.setBreakEndTime(breakEndTime);
            pojo.setDescription(description);
            pojo.setCreateUser(createUser);
            pojo.setUpdateUser(updateUser);
            pojo.setCreateDate(new Date());
            pojo.setUpdateDate(new Date());

            return shiftTypeSettingWareHouse.save(pojo);
        }

        return true;
    }

    @Override
    public boolean modifyShiftType(ShiftTypeSettingVO shifttypeSettingVO) {
        if (shifttypeSettingVO != null) {
            String shiftTypeId = shifttypeSettingVO.getShiftTypeId();
            String shiftTypeName = shifttypeSettingVO.getShiftTypeName();
            String startTime = shifttypeSettingVO.getStartTime();
            String endTime = shifttypeSettingVO.getEndTime();
            String breakStartTime = shifttypeSettingVO.getBreakStartTime();
            String breakEndTime = shifttypeSettingVO.getBreakEndTime();
            String description = shifttypeSettingVO.getDescription();
            String updateUser = shifttypeSettingVO.getUpdateUser();

            ShiftTypePOJO pojo = new ShiftTypePOJO();
            pojo.setShiftTypeId(shiftTypeId);
            pojo.setShiftTypeName(shiftTypeName);
            pojo.setStartTime(startTime);
            pojo.setEndTime(endTime);
            pojo.setBreakStartTime(breakStartTime);
            pojo.setBreakEndTime(breakEndTime);
            pojo.setDescription(description);
            pojo.setUpdateUser(updateUser);
            pojo.setUpdateDate(new Date());

            return shiftTypeSettingWareHouse.update(pojo);
        }

        return true;
    }

    @Override
    public boolean modifySkillMapping(List<ShiftTypeSkillMapVO> sysShifttypeSkillMapVOLs) {
        if (CollectionUtils.isNotEmpty(sysShifttypeSkillMapVOLs)) {
            // 先將所有同班別的技能全部刪除掉
            String sqlText = ResourceFileUtil.SQL_SHIFT.getResource("DELETE_BY_SHIFT_TYPE");

            Map<String, Object> params = new HashMap<>();

            // 異動的必然是相同班別.所以只取第一個物件的shiftTypeId
            params.put("SHIFT_TYPE_ID", sysShifttypeSkillMapVOLs.get(0).getShiftTypeId());

            jdbcDAO.update(sqlText, params);

            // 建立新的班別技能
            List<ShiftSkillMapPOJO> pojoLs = new ArrayList<>();

            for (ShiftTypeSkillMapVO vo : sysShifttypeSkillMapVOLs) {
                String skillId = vo.getSkillId();
                String shiftTypeId = vo.getShiftTypeId();
                String createUser = vo.getCreateUser();
                String updateUser = createUser;

                ShiftSkillMapPOJO pojo = new ShiftSkillMapPOJO();

                pojo.setSkillId(skillId);
                pojo.setShiftTypeId(shiftTypeId);
                pojo.setCreateUser(createUser);
                pojo.setUpdateUser(updateUser);
                pojo.setCreateDate(new Date());
                pojo.setUpdateDate(new Date());

                pojoLs.add(pojo);
            }
            shiftTypeSkillMapWareHouse.batchSave(pojoLs);
        }

        return true;
    }

    @Override
    public List<ShiftTypeSkillMapVO> querySkillMappingInfoByShiftTypeId(String shiftTypeId) {
        List<ShiftTypeSkillMapVO> shiftSkillDataLs = new ArrayList<>();
        if (StringUtils.isNotBlank(shiftTypeId)) {
            Map<String, Object> params = new HashMap<>();

            params.put("SHIFT_TYPE_ID", shiftTypeId);

            // 查詢該班別所需要的skill
            String sqlText = ResourceFileUtil.SQL_SHIFT.getResource("QUERY_SKILLS_BY_SHIFT_TYPE_ID");
            shiftSkillDataLs = jdbcDAO.queryForBean(sqlText, params, ShiftTypeSkillMapVO.class);
        }

        return shiftSkillDataLs;
    }

    /**
     * 查詢所有班別的skill,但會排除掉當前班別已經擁有的skill
     * 
     * @param shiftTypeId
     * @return List<ShiftTypeSkillMapVO>
     */
    @Override
    public List<ShiftTypeSkillMapVO> querySkillMappingInfoNotInShiftTypeId(String shiftTypeId) {
        List<ShiftTypeSkillMapVO> otherShiftSkillDataLs = new ArrayList<>();
        if (StringUtils.isNotBlank(shiftTypeId)) {
            String sqlText = ResourceFileUtil.SQL_SHIFT.getResource("QUERY_SKILLS_NO_IN_SKILL_ID");

            Map<String, Object> params = new HashMap<>();

            params.put("SHIFT_TYPE_ID", shiftTypeId);

            otherShiftSkillDataLs = jdbcDAO.queryForBean(sqlText, params, ShiftTypeSkillMapVO.class);
        }

        return otherShiftSkillDataLs;
    }

    @Override
    public boolean createShiftAttachment(ShiftMaintainVO shiftMaintainVO) {
        if (shiftMaintainVO != null) {
        	FileVO fileVO = shiftMaintainVO.getShiftAttachment();
			String fileName = fileVO.getName();
			String createUser = shiftMaintainVO.getCreateUser();
			byte[] fileBinary = fileVO.getFileBinary();
			String fileExtension = fileVO.getExtension();
			String dtYear = shiftMaintainVO.getDtYear();
			String dtMonth = shiftMaintainVO.getDtMonth();

			SysShiftAttachmentPOJO pojo = new SysShiftAttachmentPOJO();

			pojo.setFileName(fileName);
			pojo.setCreateUser(createUser);
			pojo.setCreateDate(new Date());
			pojo.setDtYear(dtYear);
			pojo.setDtMonth(dtMonth);
			pojo.setContent(fileBinary);
			pojo.setFileExtensions(fileExtension);

			// 先刪除所有該年&月底下的附件資料
			sysShiftAttachmentWareHouse.deleteByDtYearAndDtMonth(dtYear, dtMonth);

			// 再新增
			sysShiftAttachmentWareHouse.save(pojo);
        }

        return true;
    }

    @Override
    public FileVO queryShiftAttachment(String year, String month) {
        if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
            FileVO rtnVO = new FileVO();
            SysShiftAttachmentPOJO pojo = sysShiftAttachmentWareHouse.findByDtYearAndDtMonth(year, month);

            String fileName = pojo.getFileName();
            byte[] content = pojo.getContent();
            Date createDate = pojo.getCreateDate();
            String createUser = pojo.getCreateUser();
            String extension = pojo.getFileExtensions();

            // 如果連FileName都沒的話代表查無資料.直接略過
            if (StringUtils.isNotBlank(fileName)) {
                rtnVO.setName(fileName);
                rtnVO.setCreateDateTxt(DateUtil.fromDate(createDate, DateUtil.DATE_TIME_PATTERN));
                rtnVO.setCreateUser(createUser);
                rtnVO.setFileBinary(content);
                rtnVO.setExtension(extension);
                
                return rtnVO;
            }
        }

		throw new ExcelException("找不到該檔案!");
    }

	@Override
	public boolean createShiftContent(ShiftMaintainVO shiftMaintainVO) {
		if (shiftMaintainVO != null) {
			String createUser = shiftMaintainVO.getCreateUser();
			String dtYear = shiftMaintainVO.getDtYear();
			String dtMonth = shiftMaintainVO.getDtMonth();
			File excel = shiftMaintainVO.getShift();
			
			List<Map<String, Object>> shiftList = ExcelParseUtil.fromExcel(excel,0);
			shiftList.remove(0);

			// ========================== 班表匯入檢核 begin	==========================

			//FIXME 注意上述excel檔 是否可能讀到空白列資料
			Map<String, Object> returnMap = checkShift(shiftList, dtYear, dtMonth);

			boolean isPass = (boolean) returnMap.get("validateStatus");

			if (!isPass) {
				String errorMessage = (String) returnMap.get("errorMessage");

				throw new ExcelException("excel檢核錯誤! " + errorMessage);
			}

			// ========================== 班表匯入檢核 end  	==========================

			if (CollectionUtils.isNotEmpty(shiftList)) {
				List<Map<String, Object>> dataList = new ArrayList<>();
				
				for (Map<String, Object> rowMap : shiftList) {
					String empNo = spiltPointZero(MapUtils.getString(rowMap, "員編"));
					String empName = MapUtils.getString(rowMap, "姓名");
					String ntAccount = MapUtils.getString(rowMap, "NT帳號");
					
					for (String key : rowMap.keySet()) {
						if (!"員編".equals(key) && !"姓名".equals(key) && !"NT帳號".equals(key)) {
							String dtDay = spiltPointZero(key);
							String shiftTypeId = MapUtils.getString(rowMap, key);
							StringBuffer workDateSB = new StringBuffer();
							
							workDateSB.append(dtYear);
							workDateSB.append("/");
							workDateSB.append(dtMonth);
							workDateSB.append("/");
							workDateSB.append(dtDay);
							
							Map<String, Object> param = new HashMap<>();
							param.put("WORK_DATE", DateUtil.toDate(workDateSB.toString(), DateUtil.DATE_SLASH_PATTERN));
							param.put("DT_YEAR", dtYear);
							param.put("DT_MONTH", dtMonth);
							param.put("DT_DAY", dtDay);
							param.put("SHIFT_TYPE_ID", shiftTypeId);
							param.put("EMPNO", empNo);
							param.put("EMPNAME", empName);
							param.put("NT_ACCOUNT", ntAccount);
							param.put("CREATE_USER", createUser);
							param.put("CREATE_DATE", new Date());
							
							dataList.add(param);
						}
					}
				}

				//判斷年月是否為空.以避免SQL把整年或所有相同月份的資料全部砍掉的問題
				if(StringUtils.isNotBlank(dtYear) && StringUtils.isNotBlank(dtMonth)) {
				    String sqlText = ResourceFileUtil.SQL_SHIFT.getResource("DELETE_SHIFT_CONTENT_BY_DT_YEAR_AND_DT_MONTH");
				    
				    Map<String,Object> params = new HashMap<>();
				    params.put("DT_YEAR", dtYear);
				    params.put("DT_MONTH", dtMonth);
				    
				    //執行刪除.避免重新上傳相同年 & 月的班表資料時出現問題
				    jdbcDAO.update(sqlText, params);
				}
				
				String sqlText = ResourceFileUtil.SQL_SHIFT.getResource("INSERT_INTO_SYS_SHIFT_CONTENT");
				jdbcDAO.batchUpdate(sqlText, dataList);
			}
		}
		
		return true;
	}
	
	private String spiltPointZero(String content) {
		if (StringUtils.isNotBlank(content)) {
			int value = Double.valueOf(content).intValue();
			
			return String.valueOf(value);
		}
		
		return "";
	}
	
	private Map<String, Object> checkShift(List<Map<String, Object>> shiftList, String dtYear, String dtMonth) {
		Map<String, Object> returnMap = new HashMap<String, Object>();

		boolean validateStatus = false;

		String errorMessage = "";

		// step0 取得當月有幾天
		int year = Integer.valueOf(dtYear);
		int month = Integer.valueOf(dtMonth);
		int maxDaysByDate = getDaysByYearMonth(year, month);

		// step1 檢核日期是否完整
		validateStatus =  validateDate(shiftList, maxDaysByDate);

		if (!validateStatus) {
			errorMessage = "日期檢核有誤";
		}

		if (validateStatus) {
			// step2 檢核資料是否有空白
			Map<String, Object> validateData = validateData(shiftList, maxDaysByDate);

			validateStatus = (boolean) validateData.get("validateDataStatus");
			
			if (!validateStatus) {
				errorMessage = "資料檢核有誤(員編或班別為空)";
			}

			List<String> dataList =  new ArrayList();

			if(validateStatus) {
				dataList = (List<String>) validateData.get("dataList");

				// step3 檢核班別是否存在於 db
				validateStatus = validateExistDB(dataList);
				
				if (!validateStatus) {
					errorMessage = "匯入value不存在於DB";
				}
			}
		}

		returnMap.put("validateStatus", validateStatus);
		returnMap.put("errorMessage", errorMessage);

		return returnMap;
	}

	// 檢核日期是否完整
	private boolean validateDate(List<Map<String, Object>> shiftList, int maxDaysByDate) {
		boolean isPass = true;

		Map<String, Object> shiftMap = shiftList.get(0);

		List<String> keyList = new ArrayList<>();

		// 取得除了 員編、姓名、NT帳號之外的所有key
		for (String key : shiftMap.keySet()) {
			if (!"員編".equals(key) && !"姓名".equals(key) && !"NT帳號".equals(key)) {

				keyList.add(spiltPointZero(key));
			}
		}

		for(int i = 1; i <= maxDaysByDate; i++) {
			String tempKey = String.valueOf(i);
			if (!keyList.contains(tempKey)) {
				isPass = false;

				break;
			}
		}

		return isPass;
	}
	
	// 檢核資料是否空白
	private Map<String, Object> validateData(List<Map<String, Object>> shiftList, int maxDaysByDate) {
		Map<String, Object> returnMap = new HashMap<String, Object>();

		Set<String> dataSet = new HashSet<String>();

		boolean validateDataStatus = true;

		for (Map<String, Object> rowMap : shiftList) {

			// 判斷員編是否為空
			String empNo = spiltPointZero(MapUtils.getString(rowMap, "員編"));

			if (empNo == null || empNo == "") {

				validateDataStatus = false;

				break;
			}

			if(validateDataStatus) {
				for (String key : rowMap.keySet()) {
					if (!"員編".equals(key) && !"姓名".equals(key) && !"NT帳號".equals(key)) {

						// 超過的日期，不存入dataSet中
						if (Integer.valueOf(spiltPointZero(key)) <= maxDaysByDate) {
							String shiftTypeId = MapUtils.getString(rowMap, key);

							if (shiftTypeId == null || shiftTypeId =="") {
								validateDataStatus = false;

								break;
							}

							dataSet.add(shiftTypeId);
						}
					}
				}
			}

		}
		
		List<String> dataList = new ArrayList<>();

		// 將Set 轉為 List
		dataList.addAll(dataSet);

		returnMap.put("validateDataStatus", validateDataStatus);
		returnMap.put("dataList", dataList);

		return returnMap;
	}
	
	// 比對是否存在於DB
	private boolean validateExistDB(List<String> dataList) {
		boolean validateStatus = true;

		// 取得DB設定的shiftType
		List<ShiftTypeSettingVO> shiftTypeSettingVOList = queryShiftTypeInfo();

		List<String> shiftTypeIdList = new ArrayList<>();

		for (ShiftTypeSettingVO vo : shiftTypeSettingVOList) {

			shiftTypeIdList.add(vo.getShiftTypeId());
		}

		for(int i = 0; i < dataList.size(); i++) {

			if(!shiftTypeIdList.contains(dataList.get(i))) {

				validateStatus = false;

				break;
			}
		}

		return validateStatus;
	}
	
	// 取得相對應月份的天数
    public static int getDaysByYearMonth(int year, int month) {  
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, year);  
        a.set(Calendar.MONTH, month - 1);  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);

        return maxDate;  
    }

}
