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
 * File Created @ [2018年4月4日, 上午10:53:01 (CST)]
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
		//判断
		if(pageCurrent==0) pageCurrent =1;  
		if(pageSize==0)  pageSize = 12;  //每页显示12条数据
		Integer rows = moduleMapper.selectModuleCountsByCondition(projectModule);
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
		pb.setObjectBean(projectModule); //设置查询条件
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
