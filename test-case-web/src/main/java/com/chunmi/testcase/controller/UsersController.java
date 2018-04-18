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
 * File Created @ [2018年3月30日, 下午5:17:56 (CST)]
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
import com.chunmi.testcase.model.po.Users;
import com.chunmi.testcase.model.vo.UsersVo;
import com.chunmi.testcase.service.UsersService;
import com.chunmi.testcase.utils.Constant;
import com.chunmi.testcase.utils.MD5Util;
import com.chunmi.testcase.utils.PageBean;
import com.chunmi.testcase.utils.PageUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UsersController {


	@Autowired
	private UsersService usersService;


	/**
	 * 
	 * @description: <p class="detail">跳转登录页</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年4月2日-下午1:04:09
	 * @param @param model
	 * @param @return
	 * @return String
	 */
	@GetMapping(value= {"","/goToLogin"})
	public String goToLoginIndex(Model model) {
		return "login";
	}
	


	/**
	 * 
	 * @description: <p class="detail">跳转注册页</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年4月2日-下午1:04:22
	 * @param @return
	 * @return String
	 */
	@GetMapping(value="/goToRegister")
	public String register() {
		return "register";
	}
	
	


	/**
	 * 
	 * @description: <p class="detail">检测注册信息</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年4月2日-下午1:04:41
	 * @param @param usersVo
	 * @param @param model
	 * @param @return
	 * @return String
	 */
	@PostMapping(value="/checkRegister")
	public String checkRegister(UsersVo usersVo,Model model) {
		if(!usersVo.getPassword().equals(usersVo.getConfirmPassword())) {
			model.addAttribute("error", "The password for the two input is inconsistent");
			return "register";
		}
		Users user = usersService.selectVoteUsersByName(usersVo.getUserName());
		if(user!=null) {
			model.addAttribute("error","The user already exists");
			return "register";
		}
		try {
			Users insertUser = new Users();
			insertUser.setUserName(usersVo.getUserName());
			insertUser.setPassword(usersVo.getPassword());
			usersService.addUser(insertUser);
			return "redirect:/goToLogin";
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return null;
	}
	
	
	/**
	 * 
	 * @description: <p class="detail">登录验证</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年4月2日-下午1:17:53
	 * @param @param user
	 * @param @param model
	 * @param @return
	 * @return String
	 */
	@PostMapping(value="/checkLogin")
	public String checkLogin(Users user,Model model,HttpServletRequest request) {
		String userName = user.getUserName();
		String password = user.getPassword();
		Users u = usersService.selectVoteUsersByName(userName);
		if(u==null) {
			model.addAttribute("error", "User does not exist, please register first");  //用户不存在
		}else {
			if(MD5Util.MD5Encryption(password).toLowerCase().equals(u.getPassword())){
				if(u.getIsDisabled().equals("1")) {
					model.addAttribute("error","User disabled");   //用户被禁用
				}else {
					request.getSession().setAttribute(Constant.LOGIN_MANAGER, u);
					return "redirect:dashboard";                  //通过登录验证，跳转首页页面
				}
			}else {
				model.addAttribute("error","User name and password do not match");     //密码错误
			}
		}
		return "login";
	}
	

	/**
	 * 
	 * @description: <p class="detail">首页页面</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年4月2日-下午2:07:03
	 * @param @param model
	 * @param @param request
	 * @param @return
	 * @return String
	 */
	@GetMapping(value="/dashboard")
	public String dashboard(Model model,HttpServletRequest request) {
		model.addAttribute(Constant.LOGIN_MANAGER,request.getSession().getAttribute(Constant.LOGIN_MANAGER));
		return "dashboard";
	}
	

	/**
	 * 
	 * @description: <p class="detail">用户退出</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年4月2日-下午2:14:30
	 * @param @param request
	 * @param @return
	 * @return String
	 */
	@GetMapping(value="/signOut")
	public String signOut(HttpServletRequest request) {
		request.getSession().removeAttribute(Constant.LOGIN_MANAGER);
		return "login";
	}	
	
	
	/**
	 * 
	 * @description: <p class="detail">用户列表</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年4月2日-下午2:33:39
	 * @param @param request
	 * @param @param model
	 * @param @param pageCurrent
	 * @param @param pageSize
	 * @param @param pageCount
	 * @param @param user
	 * @param @return
	 * @return String
	 */
	@GetMapping(value="/userList_{pageCurrent}_{pageSize}_{pageCount}")
	public String goToUserList(HttpServletRequest request,Model model,@PathVariable("pageCurrent")Integer pageCurrent,
			@PathVariable("pageSize")Integer pageSize,@PathVariable("pageCount")Integer pageCount,
			Users user) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put(Constant.LOGIN_MANAGER, request.getSession().getAttribute(Constant.LOGIN_MANAGER));
		try {
			PageBean<Users> pb = usersService.selectUserListByCondition(user,pageCurrent,pageSize,pageCount); //查询用户列表
			map.put("pb",pb);
			//生成新的查询url
			String newUrl = "userList_{pageCurrent}_{pageSize}_{pageCount}?userName="+user.getUserName()+"&isDisabled="+user.getIsDisabled();
			//返回分页内容
			String pageHTML = PageUtil.getPageContent(newUrl,pb.getPageCurrent(), pb.getPageSize(), pb.getPageCount());
			map.put("pageHTML", pageHTML);
		} catch (Exception e) {
			log.error("查询用户列表失败",e);
		}
		model.addAllAttributes(map);
		return "users/usersManage";
	}
	
	
	/**
	 * 
	 * @description: <p class="detail">更新用户状态</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年4月2日-下午4:25:50
	 * @param @param user
	 * @param @return
	 * @return Integer
	 */
	@PostMapping(value="/updateUserStatus")
	@ResponseBody
	public Integer updateUserStatus(Users user) {
		return usersService.updateUserStatus(user);
	}
}
