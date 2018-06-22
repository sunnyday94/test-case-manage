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
 * File Created @ [2018年5月23日, 上午11:37:28 (CST)]
 */
package com.chunmi.testcase.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)  //说明了Annotation所修饰的对象范围
@Retention(RetentionPolicy.RUNTIME) //表示需要在什么级别保存该注释信息，用于描述注解的生命周期
@Documented //Documented是一个标记注解，没有成员
@Inherited //一个标记注解，@Inherited阐述了某个被标注的类型是被继承的
public @interface Loggable {
	
	public String logDescription();
}
