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
 * File Created @ [2018��4��3��, ����1:27:03 (CST)]
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
		//�ж�
		if(pageCurrent==0) pageCurrent =1;  
		if(pageSize==0)  pageSize = 12;  //ÿҳ��ʾ12������
		Integer rows = projectMapper.selectProjectCountsByCondition(project); //����Ŀ��
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
		pb.setObjectBean(project);      //���ò�ѯ����
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
