package com.chunmi.testcase.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chunmi.testcase.model.po.ProjectVersion;
import com.chunmi.testcase.utils.PageRequest;

public interface ProjectVersionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_version
     *
     * @mbg.generated Fri Mar 30 15:28:36 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_version
     *
     * @mbg.generated Fri Mar 30 15:28:36 CST 2018
     */
    int insert(ProjectVersion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_version
     *
     * @mbg.generated Fri Mar 30 15:28:36 CST 2018
     */
    int insertSelective(ProjectVersion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_version
     *
     * @mbg.generated Fri Mar 30 15:28:36 CST 2018
     */
    ProjectVersion selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_version
     *
     * @mbg.generated Fri Mar 30 15:28:36 CST 2018
     */
    int updateByPrimaryKeySelective(ProjectVersion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_version
     *
     * @mbg.generated Fri Mar 30 15:28:36 CST 2018
     */
    int updateByPrimaryKey(ProjectVersion record);

	Integer selectProjectVersionCountsByCondition(@Param("version")ProjectVersion projectVersion);

	List<ProjectVersion> selectProjectVersionByCondition(@Param("pageRequest")PageRequest pageRequest, @Param("version")ProjectVersion projectVersion);

	ProjectVersion seletProjectVersionByProjectIdAndVersionNum(@Param("version")ProjectVersion projectVersion);
}