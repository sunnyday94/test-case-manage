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
 * File Created @ [2018��4��4��, ����10:53:01 (CST)]
 */
package com.chunmi.testcase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chunmi.testcase.mapper.ProjectModuleMapper;
import com.chunmi.testcase.model.po.ProjectModule;
import com.chunmi.testcase.service.ProjectModuleService;
import com.chunmi.testcase.utils.PageBean;
import com.chunmi.testcase.utils.PageRequest;

@Service
public class ProjectModuleServiceImpl implements ProjectModuleService {
	
	@Autowired
	private ProjectModuleMapper moduleMapper;

	@Override
	public PageBean<ProjectModule> selectModuleListByCondition(ProjectModule projectModule, Integer pageCurrent,
			Integer pageSize, Integer pageCount) {
		PageBean<ProjectModule> pb = new PageBean<ProjectModule>();
		//�ж�
		if(pageCurrent==0) pageCurrent =1;  
		if(pageSize==0)  pageSize = 12;  //ÿҳ��ʾ12������
		Integer rows = moduleMapper.selectModuleCountsByCondition(projectModule);
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
		pb.setObjectBean(projectModule); //���ò�ѯ����
		List<ProjectModule> projectModuleList = moduleMapper.selectModuleListByCondition(pageRequest,projectModule);
		pb.setList(projectModuleList);
		return pb;
	}

	@Override
	public ProjectModule selectModuleByProjectIdAndModuleName(ProjectModule projectModule) {
		return moduleMapper.selectModuleByProjectIdAndModuleName(projectModule);
	}

	@Override
	public Integer addProjectModule(ProjectModule projectModule) {
		return moduleMapper.insertSelective(projectModule);
	}

	@Override
	public Integer delModule(ProjectModule projectModule) {
		return moduleMapper.delModule(projectModule);
	}

}
