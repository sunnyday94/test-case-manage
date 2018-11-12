/**
 * This class was created by sunny. It's distributed as
 * part of the test-case-service Mod.
 *
 * ???????(C) ?????????????????? 2014-2023
 * Copyright 2014-2023 CHUNMI TECHNOLOGY CO..
 *
 * This software is the confidential and proprietary information of
 * CHUNMI Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with CHUNMI.
 *
 * File Created @ [2018??4??2??, ????10:51:19 (CST)]
 */
package com.chunmi.testcase.service.impl;


import java.util.List;

import com.chunmi.testcase.mapper.UsersMapper;
import com.chunmi.testcase.service.UsersService;
import org.springframework.stereotype.Service;
import com.chunmi.testcase.model.po.Users;
import com.chunmi.testcase.utils.PageBean;
import com.chunmi.testcase.utils.PageRequest;

import javax.annotation.Resource;

@Service
public class UsersServiceImpl implements UsersService {

    @Resource
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
        //判断
        if(pageSize == 0) pageSize = 12;         //每页条目数
        if(pageCurrent == 0) pageCurrent = 1;    //当前页
        Integer rows = usersMapper.selectUserCountsByCondition(user);  //总条目数
        pb.setRows(rows);   //设置总条数
        //计算分页
        pageCount = rows%pageSize == 0 ? (rows/pageSize) : (rows/pageSize) + 1;
        //设置分页
        pageCount = pageCount ==0 ? 1: pageCount;
        //判断当前页是否>=最大页
        if(pageCurrent>=pageCount) {
            pb.setPageCurrent(pageCount);
        }
        PageRequest pageRequest = new PageRequest(pageCurrent,pageSize);
        pb.setPageSize(pageSize);
        pb.setPageCount(pageCount);
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
