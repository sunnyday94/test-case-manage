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
 * File Created @ [2017年12月12日, 下午6:15:04 (CST)]
 */
package com.chunmi.testcase.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DataSourceConfig {
		@Autowired
		private Environment env;
	
	 	@Bean
	    @ConfigurationProperties(prefix = "spring.datasource")
	    public DataSource dataSource() {
	        return new com.alibaba.druid.pool.DruidDataSource();
	    }
	    
	 	/**
	 	 * 
	 	 * @description: <p class="detail">SqlSessionFactory</p>
	 	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 	 * @date: 2017年12月11日-下午2:06:40
	 	 * @param @return
	 	 * @param @throws Exception
	 	 * @return SqlSessionFactory
	 	 */
	    @Bean
	    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
	        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
	        sqlSessionFactoryBean.setDataSource(dataSource());

	        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

	        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(env.getProperty("mybatis.mapper-locations")));
	        sqlSessionFactoryBean.setTypeAliasesPackage(env.getProperty("mybatis.type-aliases-package"));
	        sqlSessionFactoryBean.setConfigLocation(resolver.getResource(env.getProperty("mybatis.config-location")));

	        return sqlSessionFactoryBean.getObject();
	    }


	    /**
	     * 
	     * @description: <p class="detail">事务管理</p>
	     * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	     * @date: 2017年12月11日-下午2:07:30
	     * @param @return
	     * @return PlatformTransactionManager
	     */
	    @Bean
	    public PlatformTransactionManager transactionManager() {
	        return new DataSourceTransactionManager(dataSource());
	    }

}
