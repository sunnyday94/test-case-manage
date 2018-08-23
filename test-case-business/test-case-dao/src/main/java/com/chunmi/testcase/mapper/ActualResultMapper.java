package com.chunmi.testcase.mapper;

import java.util.List;

import com.chunmi.testcase.mapper.basedap.IBaseMapperDao;
import com.chunmi.testcase.model.po.ActualResult;

public interface ActualResultMapper extends IBaseMapperDao<ActualResult,Long> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_actual_result
     *
     * @mbg.generated Fri Mar 30 15:28:36 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_actual_result
     *
     * @mbg.generated Fri Mar 30 15:28:36 CST 2018
     */
    int insert(ActualResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_actual_result
     *
     * @mbg.generated Fri Mar 30 15:28:36 CST 2018
     */
    int insertSelective(ActualResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_actual_result
     *
     * @mbg.generated Fri Mar 30 15:28:36 CST 2018
     */
    ActualResult selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_actual_result
     *
     * @mbg.generated Fri Mar 30 15:28:36 CST 2018
     */
    int updateByPrimaryKeySelective(ActualResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_actual_result
     *
     * @mbg.generated Fri Mar 30 15:28:36 CST 2018
     */
    int updateByPrimaryKey(ActualResult record);

	List<ActualResult> selectAllActualResultList();
}