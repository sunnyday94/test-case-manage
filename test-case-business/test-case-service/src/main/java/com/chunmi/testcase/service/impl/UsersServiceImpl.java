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
 * File Created @ [2018��4��2��, ����10:51:19 (CST)]
 */
package com.chunmi.testcase.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chunmi.testcase.mapper.UsersMapper;
import com.chunmi.testcase.model.po.Users;
import com.chunmi.testcase.service.UsersService;
import com.chunmi.testcase.utils.PageBean;
import com.chunmi.testcase.utils.PageRequest;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersMapper usersMapper;
	
	@Override
	public Users selectUserByName(String userName) {
		return usersMapper.selectUserByName(userName);
	}

	@Override
	public Integer addUser(Users user) {
		return usersMapper.insertSelective(user);
	}

	@Override
	public PageBean<Users> selectUserListByCondition(Users user, Integer pageCurrent, Integer pageSize,
			Integer pageCount) {
		PageBean<Users> pb = new PageBean<Users>();
		//�ж�
		if(pageSize == 0) pageSize = 12;         //ÿҳ12������
		if(pageCurrent == 0) pageCurrent = 1;    //��ǰҳ
		Integer rows = usersMapper.selectUserCountsByCondition(user).intValue();  //����Ŀ��
		pb.setRows(rows);   //��������Ŀ��
		//�����ҳ
		pageCount = rows%pageSize == 0 ? (rows/pageSize) : (rows/pageSize) + 1;
		//������ҳ��
		pageCount = pageCount ==0 ? 1: pageCount;  
		//�����ǰҳ>=���ҳ,�����õ�ǰҳΪ���ҳ
		if(pageCurrent>=pageCount) {
			pb.setPageCurrent(pageCount);
		}
		PageRequest pageRequest = new PageRequest(pageCurrent,pageSize);
		pb.setPageSize(pageSize);       //ÿҳ��ʾ��Ŀ
		pb.setPageCount(pageCount);     //��ҳ��
		pb.setObjectBean(user);
		List<Users> userList = usersMapper.selectUserListByCondition(user,pageRequest);
		pb.setList(userList);
		return pb;
	}

	@Override
	public Integer updateUserStatus(Users user) {
		return usersMapper.updateUserStatus(user);
	}

	@Override
	public Integer updateUserPassword(Users users) {
		return usersMapper.updateByPrimaryKeySelective(users);
	}

}
