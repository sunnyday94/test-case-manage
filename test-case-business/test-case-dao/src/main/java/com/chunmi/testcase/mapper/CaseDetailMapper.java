package com.chunmi.testcase.mapper;

import java.util.List;

import com.chunmi.testcase.mapper.basedap.IBaseMapperDao;
import org.apache.ibatis.annotations.Param;

import com.chunmi.testcase.model.po.CaseDetail;
import com.chunmi.testcase.model.vo.CaseDetailVo;
import com.chunmi.testcase.utils.PageRequest;

public interface CaseDetailMapper extends IBaseMapperDao<CaseDetail,Long> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_casedetail
     *
     * @mbg.generated Wed Apr 04 10:20:05 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_casedetail
     *
     * @mbg.generated Wed Apr 04 10:20:05 CST 2018
     */
    int insert(CaseDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_casedetail
     *
     * @mbg.generated Wed Apr 04 10:20:05 CST 2018
     */
    int insertSelective(CaseDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_casedetail
     *
     * @mbg.generated Wed Apr 04 10:20:05 CST 2018
     */
    CaseDetail selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_casedetail
     *
     * @mbg.generated Wed Apr 04 10:20:05 CST 2018
     */
    int updateByPrimaryKeySelective(CaseDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cm_casedetail
     *
     * @mbg.generated Wed Apr 04 10:20:05 CST 2018
     */
    int updateByPrimaryKey(CaseDetail record);

	Integer selectTestCaseCountsByCondition(@Param("testCase")CaseDetail testCase);

	List<CaseDetail> selectTestCaseListByCondition(@Param("pageRequest")PageRequest pageRequest, @Param("testCase")CaseDetail testCase);

	CaseDetail selectTestCaseByConditions(@Param("caseName")String caseName);

	CaseDetailVo selectTestCaseDetailById(@Param("id")Long id);

	Integer delTestCaseDetailById(@Param("id")Long id);

	List<CaseDetailVo> selectExportTestCaseByConditions(@Param("testCase")CaseDetail caseDetail);

	Integer batchInsertCaseDetail(@Param("caseDetailList")List<CaseDetail> caseDetailList);
}