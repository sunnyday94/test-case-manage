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
 * File Created @ [2018��4��2��, ����10:30:14 (CST)]
 */
package com.chunmi.testcase.model.vo;

import com.chunmi.testcase.model.po.Users;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
public class UsersVo extends Users{

	/**
	 * ȷ������
	 */
	private String confirmPassword;
}
