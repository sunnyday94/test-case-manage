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
 * File Created @ [2018��4��2��, ����10:50:50 (CST)]
 */
package com.chunmi.testcase.service;

import com.chunmi.testcase.model.po.Users;
import com.chunmi.testcase.utils.PageBean;

public interface UsersService {

	Users selectVoteUsersByName(String userName);

	Integer addUser(Users user);

	/**
	 * 
	 * @description: <p class="detail">��ѯ�û��б�</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018��4��2��-����2:36:40
	 * @param @param user
	 * @param @param pageCurrent
	 * @param @param pageSize
	 * @param @param pageCount
	 * @param @return
	 * @return PageBean<User>
	 */
	PageBean<Users> selectUserListByCondition(Users user, Integer pageCurrent, Integer pageSize, Integer pageCount);


	Integer updateUserStatus(Users user);

}
