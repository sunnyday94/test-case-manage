package com.chunmi.testcase.mapper;

import com.chunmi.testcase.model.po.Role;

public interface RoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_role
     *
     * @mbg.generated Fri Mar 30 15:28:36 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_role
     *
     * @mbg.generated Fri Mar 30 15:28:36 CST 2018
     */
    int insert(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_role
     *
     * @mbg.generated Fri Mar 30 15:28:36 CST 2018
     */
    int insertSelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_role
     *
     * @mbg.generated Fri Mar 30 15:28:36 CST 2018
     */
    Role selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_role
     *
     * @mbg.generated Fri Mar 30 15:28:36 CST 2018
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_role
     *
     * @mbg.generated Fri Mar 30 15:28:36 CST 2018
     */
    int updateByPrimaryKey(Role record);
}