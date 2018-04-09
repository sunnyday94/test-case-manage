/**
 * This class was created by sunny. It's distributed as
 * part of the test-case-service Mod.
 *
 * 版权所有(C) 上海纯米电子科技有限公司 2014-2023
 * Copyright 2014-2023 CHUNMI TECHNOLOGY CO..
 *
 * This software is the confidential and proprietary information of
 * CHUNMI Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with CHUNMI.
 *
 * File Created @ [2018年4月8日, 上午10:34:25 (CST)]
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
		//判断
		if(pageCurrent==0) pageCurrent =1;  
		if(pageSize==0)  pageSize = 12;  //每页显示12条数据
		Integer rows = caseDetailMapper.selectTestCaseCountsByCondition(testCase);
		pb.setRows(rows);
		//计算分页
		pageCount = rows%pageSize == 0 ? (rows/pageSize) : (rows/pageSize) + 1;
		//设置总页数
		pageCount = pageCount ==0 ? 1: pageCount;  
		//如果当前页>=最大页,则设置当前页为最大页
		if(pageCurrent>=pageCount) {
			pb.setPageCurrent(pageCount);
		}
		PageRequest pageRequest = new PageRequest(pageCurrent, pageSize);
		pb.setPageSize(pageSize);       //每页显示条目
		pb.setPageCount(pageCount);     //总页数
		pb.setObjectBean(testCase);      //设置查询条件
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
