/**
 * This class was created by sunny. It's distributed as
 * part of the test-case-service Mod.
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
 * File Created @ [2018��5��22��, ����11:32:15 (CST)]
 */
package com.chunmi.testcase.service;

import org.apache.poi.ss.usermodel.Workbook;

public interface TemporaryService {

	void readContent(Workbook wb,Long projectId,Long versionId);
}
