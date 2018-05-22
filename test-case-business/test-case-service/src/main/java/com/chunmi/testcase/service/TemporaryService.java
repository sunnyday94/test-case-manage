/**
 * This class was created by sunny. It's distributed as
 * part of the test-case-service Mod.
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
 * File Created @ [2018年5月22日, 上午11:32:15 (CST)]
 */
package com.chunmi.testcase.service;

import org.apache.poi.ss.usermodel.Workbook;

public interface TemporaryService {

	void readContent(Workbook wb,Long projectId,Long versionId);
}
