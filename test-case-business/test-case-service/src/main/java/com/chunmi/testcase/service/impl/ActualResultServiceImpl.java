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
 * File Created @ [2018年4月9日, 上午10:27:23 (CST)]
 */
package com.chunmi.testcase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chunmi.testcase.mapper.ActualResultMapper;
import com.chunmi.testcase.model.po.ActualResult;
import com.chunmi.testcase.service.ActualResultService;

@Service
public class ActualResultServiceImpl implements ActualResultService{

	@Autowired
	private ActualResultMapper actualResultMapper;
	
	@Override
	public List<ActualResult> selectAllActualResultList() {
		return actualResultMapper.selectAllActualResultList();
	}

}
