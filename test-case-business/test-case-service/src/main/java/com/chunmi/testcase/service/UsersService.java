/**
 * FileName: UsersService
 * Author:   sunny
 * Date:     2018/11/12 22:40
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chunmi.testcase.service;

import com.chunmi.testcase.model.po.Users;
import com.chunmi.testcase.utils.PageBean;

/**
 * 〈一句话功能简述〉
 * @author sunny
 * @create 2018/11/12
 * @since 1.0.0
 */
public interface UsersService {

    Users selectUserByName(String userName);

    Integer addUser(Users user);


    /**
      * @Description: 根据条件分页查询用户列表
      * @Author: sunny
      * @Date: 22:40 2018/11/12
      */
    PageBean<Users> selectUserListByCondition(Users user, Integer pageCurrent, Integer pageSize, Integer pageCount);


    Integer updateUserStatus(Users user);

    Integer updateUserPassword(Users users);

}