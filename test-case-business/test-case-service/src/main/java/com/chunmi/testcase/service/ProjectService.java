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
 * File Created @ [2018年4月3日, 下午1:26:12 (CST)]
 */
package com.chunmi.testcase.service;

import com.chunmi.testcase.model.po.Project;
import com.chunmi.testcase.utils.PageBean;

public interface ProjectService {

	PageBean<Project> selectProjectListByCondition(Project project, Integer pageCurrent, Integer pageSize,
			Integer pageCount);

	Project selectProjectByName(String projectName);

	Integer addProject(Project project);

	Integer delProjectById(Long id);

}
