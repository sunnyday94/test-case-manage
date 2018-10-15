/**
 * This class was created by sunny. It's distributed as
 * part of the test-case-web Mod.
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
 * File Created @ [2018��5��23��, ����4:27:02 (CST)]
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

import com.chunmi.testcase.annotation.Loggable;
import com.chunmi.testcase.model.po.OperationLog;
import com.chunmi.testcase.model.po.Users;
import com.chunmi.testcase.service.OperationLogService;
import com.chunmi.testcase.utils.Constant;
import com.chunmi.testcase.utils.PageBean;
import com.chunmi.testcase.utils.PageUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class OperationLogController {

	
	@Autowired
	private OperationLogService operationLogService;
	
	/**
	 * 
	 * @description: <p class="detail">��ѯ��־�б�</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018��5��23��-����5:06:19
	 * @param @param request
	 * @param @param model
	 * @param @param pageCurrent
	 * @param @param pageSize
	 * @param @param pageCount
	 * @param @param user
	 * @param @return
	 * @return String
	 */
	@GetMapping(value="operationLogList_{pageCurrent}_{pageSize}_{pageCount}")
	public String operationLogList(HttpServletRequest request,Model model,@PathVariable("pageCurrent") Integer pageCurrent,
			@PathVariable("pageSize") Integer pageSize,@PathVariable("pageCount") Integer pageCount,Users user) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put(Constant.LOGIN_MANAGER, request.getSession().getAttribute(Constant.LOGIN_MANAGER));
		try {
			PageBean<OperationLog> pb = operationLogService.selectOperationLogListByCondition(user,pageCurrent,pageSize,pageCount); //��ѯ��־�б�
			map.put("pb",pb);
			//�����µĲ�ѯurl
			String newUrl = "operationLogList_{pageCurrent}_{pageSize}_{pageCount}?userName="+user.getUserName();
			//���ط�ҳ����
			String pageHTML = PageUtil.getPageContent(newUrl,pb.getPageCurrent(), pb.getPageSize(), pb.getPageCount());
			map.put("pageHTML", pageHTML);
		} catch (Exception e) {
			log.error("��ѯ��־�б�ʧ��:{}",e);
		}
		model.addAllAttributes(map);
		return "log/operationLogList";
	}
	
	/**
	 * 
	 * @description: <p class="detail">ɾ����־</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018��5��23��-����5:08:12
	 * @param @param operationLog
	 * @param @return
	 * @return Integer
	 */
	@PostMapping(value="delOperationLog")
	@ResponseBody
	public Integer delOperationLog(OperationLog operationLog) {
		return operationLogService.delOperationLog(operationLog);
	}
	
	
	@GetMapping(value="selectOperationLogDetailById/{id}")
	@ResponseBody
	public OperationLog selectOperationLogDetailById(@PathVariable("id") Long id) {
		OperationLog operationLog = null;
		try {
			operationLog = operationLogService.selectOperationLogDetailById(id);
		} catch (Exception e) {
			log.error("��ѯ��־�������:{}",e.getMessage());
		}
		return operationLog;
	}
}
