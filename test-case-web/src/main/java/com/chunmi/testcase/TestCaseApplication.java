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
 * File Created @ [2018年3月30日, 下午2:38:39 (CST)]
 */
package com.chunmi.testcase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication(scanBasePackages= {"com.chunmi.testcase"})
@MapperScan(basePackages= {"com.chunmi.testcase.mapper"})
@EnableTransactionManagement
@EnableCaching
@Slf4j
public class TestCaseApplication  extends SpringBootServletInitializer {
	
		@Override
		protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
			return configureApplication(builder);
		}	
	
		public static void main(String[] args) throws Exception {
			log.debug("application start .....");
			configureApplication(new SpringApplicationBuilder()).run(args);
		}

		private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
			return builder.sources(TestCaseApplication.class);
		}
}
