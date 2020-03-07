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
 * File Created @ [2018年3月30日, 下午3:21:19 (CST)]
 */
package com.chunmi.testcase.test.duckbill;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GeneratorSqlmap {
	public static void main(String[] args) throws Exception {
		try {
		     // 信息缓存
            List<String> warnings = new ArrayList<>();
            // 覆盖已有的重名文件
            boolean overwrite = true;
            // 准备 配置文件
            File configFile = new File("E:\\Projects\\userCenter\\src\\main\\resources\\generatorConfig.xml");
            // 1.创建 配置解析器
            ConfigurationParser parser = new ConfigurationParser(warnings);
            // 2.获取 配置信息
            Configuration config = parser.parseConfiguration(configFile);
            // 3.创建 默认命令解释调回器
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            // 4.创建 mybatis的生成器
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            // 5.执行，关闭生成器
            myBatisGenerator.generate(null);	
            System.out.println("生成成功!");
		} catch (Exception e) {
			System.err.println("生成失败!");
			e.printStackTrace();
		}
	}

}

