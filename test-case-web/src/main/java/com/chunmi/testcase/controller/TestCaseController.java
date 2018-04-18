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
 * File Created @ [2018年4月8日, 上午10:23:35 (CST)]
 */
package com.chunmi.testcase.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chunmi.testcase.model.po.ActualResult;
import com.chunmi.testcase.model.po.CaseDetail;
import com.chunmi.testcase.model.vo.CaseDetailVo;
import com.chunmi.testcase.service.ActualResultService;
import com.chunmi.testcase.service.CaseDetailService;
import com.chunmi.testcase.service.ProjectModuleService;
import com.chunmi.testcase.utils.Constant;
import com.chunmi.testcase.utils.MessageExceptionEnum;
import com.chunmi.testcase.utils.PageBean;
import com.chunmi.testcase.utils.PageUtil;
import com.chunmi.testcase.utils.Response;
import com.fasterxml.jackson.databind.Module;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TestCaseController {
	
	@Autowired
	private CaseDetailService caseDetailService;
	
	@Autowired
	private ProjectModuleService moduleService;
	
	@Autowired
	private ActualResultService actualResultService;
	

	/**
	 * 
	 * @description: <p class="detail">测试用例列表</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年4月8日-下午12:12:45
	 * @param @param request
	 * @param @param model
	 * @param @param pageCurrent
	 * @param @param pageSize
	 * @param @param pageCount
	 * @param @param testCase
	 * @param @return
	 * @return String
	 */
	@GetMapping(value="/testCaseList_{pageCurrent}_{pageSize}_{pageCount}")
	public String testCaseList(HttpServletRequest request,Model model,@PathVariable("pageCurrent") Integer pageCurrent,
			@PathVariable("pageSize") Integer pageSize,@PathVariable("pageCount") Integer pageCount,CaseDetail testCase) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put(Constant.LOGIN_MANAGER, request.getSession().getAttribute(Constant.LOGIN_MANAGER));		
		try {
			PageBean<CaseDetail> pb = caseDetailService.selectTestCaseList(testCase,pageCurrent,pageSize,pageCount);
			List<Module> moduleList = moduleService.selectAllModuleList();
			List<ActualResult> actualResultList = actualResultService.selectAllActualResultList();
			map.put("pb",pb);
			map.put("moduleList",moduleList);
			map.put("actualResultList",actualResultList);
			//生成新的查询url
			String newUrl = "testCaseList_{pageCurrent}_{pageSize}_{pageCount}?projectId="+testCase.getProjectId()+
					"&versionId="+testCase.getVersionId()+"&caseName="+testCase.getCaseName()+"&caseTitle="+testCase.getCaseTitle()
					+"&priority="+testCase.getPriority()+"&moduleId="+testCase.getModuleId();
			//返回分页内容
			String pageHTML = PageUtil.getPageContent(newUrl,pb.getPageCurrent(), pb.getPageSize(), pb.getPageCount());
			map.put("pageHTML", pageHTML);				
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}
		model.addAllAttributes(map);
		return "testcase/testCaseList";
	}
	
	
	/**
	 * 
	 * @description: <p class="detail">添加测试用例</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年4月9日-上午11:12:04
	 * @param @param request
	 * @param @param testCase
	 * @param @return
	 * @return Response
	 */
	@PostMapping(value="/addTestCase")
	@ResponseBody
	public Response addTestCase(HttpServletRequest request,CaseDetail testCase) {
		try {
			if(caseDetailService.selectTestCaseByConditions(testCase.getCaseName())!=null)
				return Response.getError(MessageExceptionEnum.TESTCASE_EXISTED);
			caseDetailService.addTestCase(testCase);
			return Response.getSuccess();
		} catch (Exception e) {
			log.info(e.getMessage());
			return Response.getError(MessageExceptionEnum.ERROR_HANDLE);
		}
	}
	
	/**
	 * 
	 * @description: <p class="detail">根据id查询用例详情</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年4月9日-下午1:17:11
	 * @param @param id
	 * @param @return
	 * @return CaseDetail
	 */
	@GetMapping(value="selectTestCaseDetailById/{id}")
	@ResponseBody
	public CaseDetailVo selectTestCaseDetailById(@PathVariable("id") Long id) {
		try {
			CaseDetailVo caseDetail = caseDetailService.selectTestCaseDetailById(id);
			return caseDetail;
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 
	 * @description: <p class="detail">删除用例</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年4月9日-下午3:02:18
	 * @param @param caseDetail
	 * @param @return
	 * @return Response
	 */
	@PostMapping(value="/delTestCaseDetailById")
	@ResponseBody
	public Response delTestCaseDetailById(CaseDetail caseDetail) {
		try {
			caseDetailService.delTestCaseDetailById(caseDetail.getId());
			return Response.getSuccess();
		} catch (Exception e) {
			log.info(e.getMessage());
			return Response.getError(MessageExceptionEnum.ERROR_HANDLE);
		}
	}
	
	
	/**
	 * 
	 * @description: <p class="detail">更新测试用例</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年4月9日-下午4:26:39
	 * @param @param caseDetail
	 * @param @return
	 * @return Response
	 */
	@PostMapping(value="/updateTestCase")
	@ResponseBody
	public Response updateTestCase(CaseDetail caseDetail) {
		try {
			caseDetailService.updateTestCase(caseDetail);
			return Response.getSuccess();
		} catch (Exception e) {
			log.info(e.getMessage());
			return Response.getError(MessageExceptionEnum.ERROR_HANDLE);
		}
	}
	
	
	/**
	 * 
	 * @description: <p class="detail">根据查询条件导出测试用例</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年4月13日-下午2:52:30
	 * @param @param caseDetail
	 * @param @param response
	 * @return void
	 */
	@PostMapping(value="/exportTestCase")
	public void exportTestCase(CaseDetail caseDetail,HttpServletResponse response) {
		PageBean<CaseDetailVo> pb = null;
		try {
			String fileName = "测试用例_"+System.currentTimeMillis()+".xls";  //设置下载时excel客户端名称
			pb = caseDetailService.selectExportTestCaseByConditions(caseDetail);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			caseDetailService.exportTestCase(pb,response);
		} catch (Exception e) {
			log.error("查询测试用例失败:{}",e.getMessage());
		}
	}
	
	/**
	 * 
	 * @description: <p class="detail">导出测试用例</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年4月13日-下午3:45:37
	 * @param @param id
	 * @param @param response
	 * @return void
	 */
	@GetMapping(value="/exportTestCaseById/{id}")
	public void exportTestCaseById(@PathVariable("id") Long id,HttpServletResponse response) {
		try {
			String fileName = "测试用例_"+System.currentTimeMillis()+".xls";  //设置下载时excel客户端名称
			List<CaseDetailVo> caseDetailList = new ArrayList<CaseDetailVo>();
			PageBean<CaseDetailVo> pb = new PageBean<CaseDetailVo>();
			CaseDetailVo caseDetail = caseDetailService.selectTestCaseDetailById(id);
			caseDetailList.add(caseDetail);
			pb.setList(caseDetailList);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			caseDetailService.exportTestCase(pb, response);
		} catch (Exception e) {
			log.error("查询测试用例失败:{}",e.getMessage());
		}
	}
}
