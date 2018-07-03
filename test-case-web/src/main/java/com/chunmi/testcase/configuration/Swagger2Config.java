/**
 * This class was created by sunny. It's distributed as
 * part of the test-case-web Mod.
 *
 * 版权所有(C) 唯存(上海)网络科技有限公司 2014-2023
 * Copyright 2014-2023 Vphotos TECHNOLOGY CO..
 *
 * This software is the confidential and proprietary information of
 * Vphotos Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Vphotos.
 *
 * File Created @ [2018年7月3日, 下午2:21:00 (CST)]
 */
package com.chunmi.testcase.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@Configuration
@EnableSwagger2
public class Swagger2Config {
	public Swagger2Config() {
		log.debug("=================加载Swagger2Config================");
	}
	
	@Value("${swagger2.package}")
	private String basePackage;
	
	@Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
                .title("测试用例管理API")
                .description("测试用例管理API")
                .contact(new Contact("sunny", "", "sunny.wang@v.photos"))
                .version("1.0")
                .build();
    }
}
