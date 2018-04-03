/**
 * This class was created by sunny. It's distributed as
 * part of the test-case-web Mod.
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
 * File Created @ [2018年4月3日, 下午3:53:39 (CST)]
 */
package com.chunmi.testcase.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chunmi.testcase.model.po.ProjectVersion;
import com.chunmi.testcase.service.ProjectVersionService;
import com.chunmi.testcase.utils.Constant;
import com.chunmi.testcase.utils.MessageExceptionEnum;
import com.chunmi.testcase.utils.PageBean;
import com.chunmi.testcase.utils.PageUtil;
import com.chunmi.testcase.utils.Response;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ProjectVersionController {
	
	@Autowired
	private ProjectVersionService versionService;

	/**
	 * 
	 * @description: <p class="detail">查询项目版本号</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年4月3日-下午3:57:42
	 * @param @param request
	 * @param @param model
	 * @param @param pageCurrent
	 * @param @param pageSize
	 * @param @param pageCount
	 * @param @param projectVersion
	 * @param @return
	 * @return String
	 */
	@GetMapping(value="/projectVersionList_{pageCurrent}_{pageSize}_{pageCount}")
	public String projectVersionList(HttpServletRequest request,Model model,@PathVariable("pageCurrent") Integer pageCurrent,
			@PathVariable("pageSize") Integer pageSize,@PathVariable("pageCount") Integer pageCount,ProjectVersion projectVersion) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put(Constant.LOGIN_MANAGER, request.getSession().getAttribute(Constant.LOGIN_MANAGER));
		try {
			PageBean<ProjectVersion> pb = versionService.selectProjectVersionByCondition(projectVersion,pageCurrent,pageSize,pageCount);
			map.put("pb",pb);
			//生成新的查询url
			String newUrl = "projectList_{pageCurrent}_{pageSize}_{pageCount}?projectId="+projectVersion.getProjectId()+"&versionNum="+projectVersion.getVersionNum();
			//返回分页内容
			String pageHTML = PageUtil.getPageContent(newUrl,pb.getPageCurrent(), pb.getPageSize(), pb.getPageCount());
			map.put("pageHTML", pageHTML);	
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		model.addAllAttributes(map);
		return "version/version";
	}
	
	/**
	 * 
	 * @description: <p class="detail">添加项目版本号</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年4月3日-下午4:43:46
	 * @param @param projectVersion
	 * @param @return
	 * @return Response
	 */
	@PostMapping(value="/addProjectVersion")
	@ResponseBody
	public Response addProjectVersion(ProjectVersion projectVersion) {
		try {
			if(versionService.seletProjectVersionByProjectIdAndVersionNum(projectVersion)!=null)
				return Response.getError(MessageExceptionEnum.VERSION_EXISTED);
			versionService.addProjectVersion(projectVersion);
			return Response.getSuccess();
		} catch (Exception e) {
			return Response.getError(MessageExceptionEnum.ERROR_HANDLE);
		}
	}
}
