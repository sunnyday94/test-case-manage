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
 * File Created @ [2018��4��8��, ����10:34:25 (CST)]
 */
package com.chunmi.testcase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chunmi.testcase.mapper.CaseDetailMapper;
import com.chunmi.testcase.model.po.CaseDetail;
import com.chunmi.testcase.model.vo.CaseDetailVo;
import com.chunmi.testcase.service.CaseDetailService;
import com.chunmi.testcase.utils.PageBean;
import com.chunmi.testcase.utils.PageRequest;

@Service
public class CaseDetailServiceImpl implements CaseDetailService {
	
	@Autowired
	private CaseDetailMapper caseDetailMapper;
	
	@Override
	public PageBean<CaseDetail> selectTestCaseList(CaseDetail testCase, Integer pageCurrent, Integer pageSize,
			Integer pageCount) {
		PageBean<CaseDetail> pb = new PageBean<CaseDetail>();
		//�ж�
		if(pageCurrent==0) pageCurrent =1;  
		if(pageSize==0)  pageSize = 12;  //ÿҳ��ʾ12������
		Integer rows = caseDetailMapper.selectTestCaseCountsByCondition(testCase);
		pb.setRows(rows);
		//�����ҳ
		pageCount = rows%pageSize == 0 ? (rows/pageSize) : (rows/pageSize) + 1;
		//������ҳ��
		pageCount = pageCount ==0 ? 1: pageCount;  
		//�����ǰҳ>=���ҳ,�����õ�ǰҳΪ���ҳ
		if(pageCurrent>=pageCount) {
			pb.setPageCurrent(pageCount);
		}
		PageRequest pageRequest = new PageRequest(pageCurrent, pageSize);
		pb.setPageSize(pageSize);       //ÿҳ��ʾ��Ŀ
		pb.setPageCount(pageCount);     //��ҳ��
		pb.setObjectBean(testCase);      //���ò�ѯ����
		List<CaseDetail> testCaseList = caseDetailMapper.selectTestCaseListByCondition(pageRequest,testCase);
		pb.setList(testCaseList);
		return pb;
	}

	@Override
	public CaseDetail selectTestCaseByConditions(String caseName) {
		return caseDetailMapper.selectTestCaseByConditions(caseName);
	}

	@Override
	public Integer addTestCase(CaseDetail testCase) {
		return caseDetailMapper.insertSelective(testCase);
	}

	@Override
	public CaseDetailVo selectTestCaseDetailById(Long id) {
		return caseDetailMapper.selectTestCaseDetailById(id);
	}

	@Override
	public Integer delTestCaseDetailById(Long id) {
		return caseDetailMapper.delTestCaseDetailById(id);
	}

	@Override
	public Integer updateTestCase(CaseDetail caseDetail) {
		return caseDetailMapper.updateByPrimaryKeySelective(caseDetail);
	}

}
