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
 * File Created @ [2018��4��3��, ����4:02:44 (CST)]
 */
package com.chunmi.testcase.service;

import com.chunmi.testcase.model.po.ProjectVersion;
import com.chunmi.testcase.utils.PageBean;

public interface ProjectVersionService {

	PageBean<ProjectVersion> selectProjectVersionByCondition(ProjectVersion projectVersion, Integer pageCurrent,
			Integer pageSize, Integer pageCount);

	ProjectVersion seletProjectVersionByProjectIdAndVersionNum(ProjectVersion projectVersion);

	Integer addProjectVersion(ProjectVersion projectVersion);

}
