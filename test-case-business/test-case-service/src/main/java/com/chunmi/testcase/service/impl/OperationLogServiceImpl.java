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
 * File Created @ [2018��5��23��, ����2:09:02 (CST)]
 */
package com.chunmi.testcase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chunmi.testcase.mapper.OperationLogMapper;
import com.chunmi.testcase.model.po.OperationLog;
import com.chunmi.testcase.model.po.Users;
import com.chunmi.testcase.service.OperationLogService;
import com.chunmi.testcase.utils.PageBean;
import com.chunmi.testcase.utils.PageRequest;

@Service
public class OperationLogServiceImpl implements OperationLogService {

	@Autowired
	private OperationLogMapper operationLogMapper;
	
	@Override
	public Integer insertOperationLog(OperationLog operationLog) {
		return operationLogMapper.insertSelective(operationLog);
	}

	@Override
	public PageBean<OperationLog> selectOperationLogListByCondition(Users user, Integer pageCurrent, Integer pageSize,
			Integer pageCount) {
		PageBean<OperationLog> pb = new PageBean<OperationLog>();
		//�ж�
		if(pageSize == 0) pageSize = 12;         //ÿҳ12������
		if(pageCurrent == 0) pageCurrent = 1;    //��ǰҳ
		Integer rows = operationLogMapper.selectOperationLogsCountsByCondition(user).intValue();
		pb.setRows(rows);   //��������Ŀ��
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
		pb.setObjectBean(user);        // ���ò�ѯ����
		List<OperationLog> operationLogs = operationLogMapper.selectOperationLogListByCondition(user,pageRequest);
		pb.setList(operationLogs);
		return pb;
	}

	@Override
	public Integer delOperationLog(OperationLog operationLog) {
		return operationLogMapper.delOperationLog(operationLog);
	}

	@Override
	public OperationLog selectOperationLogDetailById(Long id) {
		return operationLogMapper.selectOperationLogDetailById(id);
	}

}
