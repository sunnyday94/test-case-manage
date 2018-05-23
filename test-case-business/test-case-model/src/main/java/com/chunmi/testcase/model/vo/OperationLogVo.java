/**
 * This class was created by sunny. It's distributed as
 * part of the test-case-model Mod.
 *
 * ��Ȩ����(C) �Ϻ����׵��ӿƼ����޹�˾ 2014-2023
 * Copyright 2014-2023 CHUNMI TECHNOLOGY CO..
 *
 * This software is the confidential and proprietary information of
 * CHUNMI Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with CHUNMI.
 *
 * File Created @ [2018��5��23��, ����5:44:17 (CST)]
 */
package com.chunmi.testcase.model.vo;

import com.chunmi.testcase.model.po.OperationLog;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(Include.NON_NULL)
public class OperationLogVo extends OperationLog{

	/**
	 * ������
	 */
	private String userName;
	
}
