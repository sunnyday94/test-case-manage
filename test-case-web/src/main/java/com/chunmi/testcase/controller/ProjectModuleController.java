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
 * File Created @ [2018年4月4日, 上午10:45:39 (CST)]
 */
package com.chunmi.testcase.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chunmi.testcase.model.po.ProjectModule;
import com.chunmi.testcase.service.ProjectModuleService;
import com.chunmi.testcase.utils.Constant;
import com.chunmi.testcase.utils.MessageExceptionEnum;
import com.chunmi.testcase.utils.PageBean;
import com.chunmi.testcase.utils.PageUtil;
import com.chunmi.testcase.utils.Response;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ProjectModuleController {
	
	@Autowired
	private ProjectModuleService moduleService;

	/**
	 * 
	 * @description: <p class="detail"></p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年4月4日-上午10:52:16
	 * @param @param request
	 * @param @param model
	 * @param @param pageCurrent
	 * @param @param pageSize
	 * @param @param pageCount
	 * @param @param projectModule
	 * @param @return
	 * @return String
	 */
	@GetMapping(value="/projectModuleList_{pageCurrent}_{pageSize}_{pageCount}")
	public String projectModuleList(HttpServletRequest request,Model model,@PathVariable("pageCurrent") Integer pageCurrent,
			@PathVariable("pageSize") Integer pageSize,@PathVariable("pageCount")Integer pageCount,ProjectModule projectModule) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(Constant.LOGIN_MANAGER,request.getSession().getAttribute(Constant.LOGIN_MANAGER));
		try {
			PageBean<ProjectModule> pb = moduleService.selectModuleListByCondition(projectModule,pageCurrent,pageSize,pageCount);
		    map.put("pb",pb);
			//生成新的查询url
			String newUrl = "projectModuleList_{pageCurrent}_{pageSize}_{pageCount}?projectId="+projectModule.getProjectId()+"&moduleName="+projectModule.getModuleName();
			//返回分页内容
			String pageHTML = PageUtil.getPageContent(newUrl,pb.getPageCurrent(), pb.getPageSize(), pb.getPageCount());
			map.put("pageHTML", pageHTML);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		model.addAllAttributes(map);
		return "module/moduleList";
	}
	
	/**
	 * 
	 * @description: <p class="detail">添加项目模块</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年4月4日-上午11:58:16
	 * @param @param projectModule
	 * @param @return
	 * @return Response
	 */
	@PostMapping(value="/addProjectModule")
	@ResponseBody
	public Response addProjectModule(ProjectModule projectModule) {
		try {
			if(moduleService.selectModuleByProjectIdAndModuleName(projectModule)!=null)
				return Response.getError(MessageExceptionEnum.MODULE_EXISTED);
			moduleService.addProjectModule(projectModule);
			return Response.getSuccess();
		} catch (Exception e) {
			log.info(e.getMessage());
			return Response.getError(MessageExceptionEnum.ERROR_HANDLE);
		}
	}
	
	
	/**
	 * 
	 * @description: <p class="detail">删除项目模块</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年4月4日-下午12:14:34
	 * @param @param projectModule
	 * @param @return
	 * @return Integer
	 */
	@PostMapping(value="/delModule")
	@ResponseBody
	public Integer delModule(ProjectModule projectModule) {
		return moduleService.delModule(projectModule);
	}
}
