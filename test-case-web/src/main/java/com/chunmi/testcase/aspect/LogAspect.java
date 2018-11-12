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
 * File Created @ [2018年5月23日, 下午1:09:41 (CST)]
 */
package com.chunmi.testcase.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.chunmi.testcase.service.OperationLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.chunmi.testcase.annotation.Loggable;
import com.chunmi.testcase.model.po.OperationLog;
import com.chunmi.testcase.model.po.Users;
import com.chunmi.testcase.utils.Constant;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAspect {
	
	@Resource
	private OperationLogService operationLogServiceImpl;

	/**
	 * 
	 * @description: <p class="detail">定义切点</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年5月23日-下午2:07:37
	 * @param 
	 * @return void
	 */
	@Pointcut(value="@annotation(com.chunmi.testcase.annotation.Loggable)")
	public void controllerAspect() {
		
	} 
	
	/**
	 * 
	 * @description: <p class="detail">前切</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年5月23日-下午2:11:35
	 * @param @param joinPoint
	 * @return void
	 */
	@Before(value="controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String method = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"; //方法名
		String params = joinPoint.getArgs()==null || joinPoint.getArgs().length==0 ? null : Arrays.toString(joinPoint.getArgs()); //参数
		String description = getControllerMethodDescription(joinPoint);  //描述信息
		Users user = (Users) request.getSession().getAttribute(Constant.LOGIN_MANAGER);
		try {
			OperationLog operationLog = new OperationLog();
			operationLog.setUserId(user.getId());
			operationLog.setParams(params);
			operationLog.setMethod(method);
			operationLog.setMessage(description);
			operationLogServiceImpl.insertOperationLog(operationLog);
			StringBuilder builder = new StringBuilder("请求方法:"+method+"描述信息:"+description);
			if(params!=null)
				builder.append("请求参数:"+params);
			log.debug(builder.toString());
		} catch (Exception e) {
			log.error("前置通知异常，异常信息:{}",e);
		}
	}
	

	/**
	 * 
	 * @description: <p class="detail">异常通知 用于拦截Controller层记录异常日志</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年5月23日-下午2:56:48
	 * @param @param joinPoint
	 * @param @param e
	 * @return void
	 */
	@AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		try {
			String error = "异常代码:" + e.getClass().getName() + "异常信息:" + e.getMessage() + "异常方法:"
					+ joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()";
			log.error(error, e);
		} catch (Exception ex) {
			log.error("==异常通知异常,异常信息:{}",ex);
		}
	}
	
	
	/**
	 * 
	 * @description: <p class="detail">获取注解中对方法的描述信息 用于Controller层注解</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年5月23日-下午2:21:43
	 * @param @param joinPoint
	 * @param @return
	 * @return String
	 */
	private String getControllerMethodDescription(JoinPoint joinPoint) {
		String message = "";
		Method proxyMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
		Method sourceMethod;
		try {
			sourceMethod = joinPoint.getTarget().getClass().getMethod(proxyMethod.getName(),
					proxyMethod.getParameterTypes());
			message = AnnotationUtils.findAnnotation(sourceMethod,Loggable.class).logDescription();
		} catch (NoSuchMethodException e) {
			log.error("error: ", e);
		}
		return message;
	}
	
}
