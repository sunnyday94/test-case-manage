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
 * File Created @ [2018年4月3日, 下午1:27:03 (CST)]
 */
package com.chunmi.testcase.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chunmi.testcase.mapper.ProjectMapper;
import com.chunmi.testcase.model.po.Project;
import com.chunmi.testcase.service.ProjectService;
import com.chunmi.testcase.utils.PageBean;
import com.chunmi.testcase.utils.PageRequest;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectMapper projectMapper;

	@Override
	public PageBean<Project> selectProjectListByCondition(Project project, Integer pageCurrent, Integer pageSize,
			Integer pageCount) {
		PageBean<Project> pb = new PageBean<Project>();
		//判断
		if(pageCurrent==0) pageCurrent =1;  
		if(pageSize==0)  pageSize = 12;  //每页显示12条数据
		Integer rows = projectMapper.selectProjectCountsByCondition(project); //总条目数
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
		pb.setObjectBean(project);      //设置查询条件
		List<Project> projectList = projectMapper.selectProjectListByCondition(project,pageRequest);
		pb.setList(projectList);
		return pb;
	}

	@Override
	public Project selectProjectByName(String projectName) {
		return projectMapper.selectProjectByName(projectName);
	}

	@Override
	public Integer addProject(Project project) {
		return projectMapper.insertSelective(project);
	}

	@Override
	public Integer delProjectById(Long id) {
		return projectMapper.delProjectById(id);
	}

}
