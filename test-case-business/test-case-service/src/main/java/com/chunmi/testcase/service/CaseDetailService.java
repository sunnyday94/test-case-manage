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
 * File Created @ [2018年4月8日, 上午10:34:07 (CST)]
 */
package com.chunmi.testcase.service;

import javax.servlet.http.HttpServletResponse;

import com.chunmi.testcase.model.po.CaseDetail;
import com.chunmi.testcase.model.vo.CaseDetailVo;
import com.chunmi.testcase.utils.PageBean;

public interface CaseDetailService {

	PageBean<CaseDetail> selectTestCaseList(CaseDetail testCase, Integer pageCurrent, Integer pageSize,
			Integer pageCount);

	CaseDetail selectTestCaseByConditions(String caseName);

	Integer addTestCase(CaseDetail testCase);

	CaseDetailVo selectTestCaseDetailById(Long id);

	Integer delTestCaseDetailById(Long id);

	Integer updateTestCase(CaseDetail caseDetail);

	PageBean<CaseDetailVo> selectExportTestCaseByConditions(CaseDetail caseDetail);

	void exportTestCase(PageBean<CaseDetailVo> pb, HttpServletResponse response);

}
