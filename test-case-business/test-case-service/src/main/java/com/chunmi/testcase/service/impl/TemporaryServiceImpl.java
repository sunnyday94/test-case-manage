/**
 * This class was created by sunny. It's distributed as
 * part of the test-case-service Mod.
 *
 * ��Ȩ����(C) �Ϻ����׵��ӿƼ����޹�˾ 2014-2023
 * Copyright 2014-2023 CHUNMI TECHNOLOGY CO..
 *
 * This software is the confidential and proprietary information of
 * CHUNMI Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with CHUNMI.
 *
 * File Created @ [2018��5��22��, ����11:32:48 (CST)]
 */
package com.chunmi.testcase.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.chunmi.testcase.model.po.CaseDetail;
import com.chunmi.testcase.model.po.ProjectModule;
import com.chunmi.testcase.service.CaseDetailService;
import com.chunmi.testcase.service.ProjectModuleService;
import com.chunmi.testcase.service.TemporaryService;

@Service
public class TemporaryServiceImpl implements TemporaryService {
	
	@Autowired
	private ProjectModuleService moduleService;
	
	@Autowired
	private CaseDetailService caseDetailService;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void readContent(Workbook wb,Long projectId,Long versionId) {
		Sheet sheet = wb.getSheetAt(0);// ��һ��������
		int firstRowIndex = sheet.getFirstRowNum(); //��ȡ��һ��(��0��ʼ)
		int lastRowIndex = sheet.getLastRowNum();   //��ȡ���һ��(��0��ʼ)
		
		List<CaseDetail> caseDetailList = new ArrayList<CaseDetail>();
		//�ӵڶ��п�ʼ(��һ��Ϊ����)
		for(int rIndex =firstRowIndex+1;rIndex<=lastRowIndex;rIndex++) {
			Row row = sheet.getRow(rIndex);
			if(row !=null) {
				CaseDetail caseDetail = new CaseDetail();
				caseDetail.setProjectId(projectId);
				caseDetail.setVersionId(versionId);
				int firstCellIndex = row.getFirstCellNum(); //��ȡ��һ����Ԫ��(��0��ʼ)
				int lastCellIndex = row.getLastCellNum(); //��ȡ���һ����Ԫ��(��1��ʼ)
				for(int cIndex = firstCellIndex;cIndex<lastCellIndex;cIndex++) {
					Cell cell = row.getCell(cIndex);
					if(cell !=null) {
						if(cIndex == 1)
							caseDetail.setCaseName(cell.getStringCellValue().trim());
						if(cIndex ==2)
							caseDetail.setCaseTitle(cell.getStringCellValue().trim());
						if(cIndex == 3) {
							String priority = cell.getStringCellValue().trim();
							switch (priority) {
							case "��":
								priority = "0";
								break;
							case "��":
								priority = "1";
								break;
							case "��":
								priority = "2";
								break;
							default:
								break;
							}
							caseDetail.setPriority(priority);
						}
						if(cIndex == 4) {
							String testMode = cell.getStringCellValue().trim();
							switch (testMode) {
							case "�ֶ�":
								testMode = "0";
								break;
							case "�Զ�":
								testMode = "1";
								break;
							default:
								break;
							}
							caseDetail.setTestMode(testMode);
						}
						if(cIndex == 5) {
							ProjectModule pm = new ProjectModule();
							pm.setProjectId(projectId);
							pm.setModuleName(cell.getStringCellValue());
							ProjectModule selectPm = moduleService.selectModuleByProjectIdAndModuleName(pm);
							if(selectPm==null) {
								moduleService.insertNewModule(pm);
								caseDetail.setModuleId(pm.getId());
							}else {
								caseDetail.setModuleId(selectPm.getId());
							}
						}
						if(cIndex == 6)
							caseDetail.setTestConditions(cell.getStringCellValue().trim());
						if(cIndex == 7)
							caseDetail.setDetailSteps(cell.getStringCellValue().trim());
						if(cIndex == 8)
							caseDetail.setExpectedResult(cell.getStringCellValue().trim());
						if(cIndex == 9) {
							String actualResult  = cell.getStringCellValue().trim().toUpperCase();
							Long actualResultId = null;
							switch (actualResult) {
							case "PASS":
								actualResultId = 1L;
								break;
							case "Failed":
								actualResultId = 2L;
								break;
							case "N/A":
								actualResultId = 3L;
								break;
							default:
								actualResultId = 4L;
								break;
							}
							caseDetail.setActualResultId(actualResultId);
						}
						if(cIndex == 10)
							caseDetail.setBugId(cell.getStringCellValue());
						if(cIndex == 11)
							caseDetail.setRemarks(cell.getStringCellValue());
					}
				}
				caseDetailList.add(caseDetail);
			}
		}
		if(caseDetailList!=null && caseDetailList.size()>0)
			caseDetailService.batchInsertCaseDetail(caseDetailList);
	}
}
