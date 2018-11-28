package com.chunmi.testcase.controller;

import com.chunmi.testcase.model.po.OperationLog;
import com.chunmi.testcase.model.po.Users;
import com.chunmi.testcase.service.OperationLogService;
import com.chunmi.testcase.utils.Constant;
import com.chunmi.testcase.utils.PageBean;
import com.chunmi.testcase.utils.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@Slf4j
public class OperationLogController {

	
	@Resource
	private OperationLogService operationLogServiceImpl;
	

	/**
	  * @Description: 日志列表
	  * @Author: sunny
	  * @Date: 22:50 2018/11/12
	  */
	@GetMapping(value="operationLogList_{pageCurrent}_{pageSize}_{pageCount}")
	public String operationLogList(HttpServletRequest request,Model model,@PathVariable("pageCurrent") Integer pageCurrent,
			@PathVariable("pageSize") Integer pageSize,@PathVariable("pageCount") Integer pageCount,Users user) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put(Constant.LOGIN_MANAGER, request.getSession().getAttribute(Constant.LOGIN_MANAGER));
		try {
			PageBean<OperationLog> pb = operationLogServiceImpl.selectOperationLogListByCondition(user,pageCurrent,pageSize,pageCount); //��ѯ��־�б�
			map.put("pb",pb);
			//返回新url
			String newUrl = "operationLogList_{pageCurrent}_{pageSize}_{pageCount}?userName="+user.getUserName();

			String pageHTML = PageUtil.getPageContent(newUrl,pageCurrent,pageSize, pb.getPageCount());
			map.put("pageHTML", pageHTML);
		} catch (Exception e) {
			log.error("查询日志列表出错:{}",e);
		}
		model.addAllAttributes(map);
		return "log/operationLogList";
	}
	

	/**
	  * @Description: 删除日志
	  * @Author: sunny
	  * @Date: 22:49 2018/11/12
	  */
	@PostMapping(value="delOperationLog")
	@ResponseBody
	public Integer delOperationLog(OperationLog operationLog) {
		return operationLogServiceImpl.delOperationLog(operationLog);
	}
	
	
	@GetMapping(value="selectOperationLogDetailById/{id}")
	@ResponseBody
	public OperationLog selectOperationLogDetailById(@PathVariable("id") Long id) {
		OperationLog operationLog = null;
		try {
			operationLog = operationLogServiceImpl.selectOperationLogDetailById(id);
		} catch (Exception e) {
			log.error("查询日志详情出错:{}",e.getMessage());
		}
		return operationLog;
	}
}
