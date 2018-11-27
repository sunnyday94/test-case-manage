/**
 * This class was created by sunny. It's distributed as
 * part of the annualconvention-manager Mod.
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
 * File Created @ [2017年12月12日, 下午6:10:52 (CST)]
 */
package com.chunmi.testcase.configuration;

import com.chunmi.testcase.filter.XSSFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
public class FilterConfig {
    

	/**
	 * 
	 * @description: <p class="detail">编码过滤器(统一处理编码</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年3月30日-下午5:29:57
	 * @param @return
	 * @return FilterRegistrationBean
	 */
	@Bean
	public FilterRegistrationBean<CharacterEncodingFilter> encodingFilter() {
		FilterRegistrationBean<CharacterEncodingFilter> registrationBean = new FilterRegistrationBean<CharacterEncodingFilter>();
		registrationBean.setFilter(new CharacterEncodingFilter());
		registrationBean.addInitParameter("encoding","UTF-8");
		registrationBean.addInitParameter("forceEncoding","true");
		registrationBean.addServletNames("encodingFilter");
		registrationBean.addUrlPatterns("/*");
		registrationBean.setEnabled(true);
		return registrationBean;
	}
	
	/**
	 * 
	 * @description: <p class="detail">登录过滤器</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年3月30日-下午10:44:11
	 * @param @return
	 * @return FilterRegistrationBean<ManagerCheckFilter>
	 */
//	@Bean
//	public FilterRegistrationBean<ManagerCheckFilter> managerCheckFilter(){
//		FilterRegistrationBean<ManagerCheckFilter> registrationBean = new FilterRegistrationBean<ManagerCheckFilter>();
//		registrationBean.setFilter(new ManagerCheckFilter());
//		registrationBean.addServletNames("managerCheckFilter");
//		registrationBean.addUrlPatterns("/*");
//		registrationBean.setEnabled(true);
//		return registrationBean;
//	}
	
	
	/**
	 * 
	 * @description: <p class="detail">XSSFilter</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年3月30日-下午10:44:26
	 * @param @return
	 * @return FilterRegistrationBean<XSSFilter>
	 */
	@Bean
	public FilterRegistrationBean<XSSFilter> xssFilter(){
		FilterRegistrationBean<XSSFilter> registrationBean = new FilterRegistrationBean<XSSFilter>();
		registrationBean.setFilter(new XSSFilter());
		registrationBean.addServletNames("xssFilter");
		registrationBean.addUrlPatterns("/*");
		registrationBean.setEnabled(true);
		return registrationBean;
	}
}
