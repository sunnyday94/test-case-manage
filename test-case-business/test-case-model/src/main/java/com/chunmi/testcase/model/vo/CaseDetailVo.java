/**
 * This class was created by sunny. It's distributed as
 * part of the test-case-model Mod.
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
 * File Created @ [2018年4月9日, 下午2:25:37 (CST)]
 */
package com.chunmi.testcase.model.vo;

import com.chunmi.testcase.model.po.CaseDetail;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=true)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
public class CaseDetailVo extends CaseDetail {

	/**
	 * 模块名称
	 */
	private String moduleName;
	
	/**
	 * 实际结果值
	 */
	private String actualResultValue;
}
