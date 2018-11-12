/**
 * FileName: CaseDetailService
 * Author:   sunny
 * Date:     2018/11/12 22:29
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chunmi.testcase.service;

import com.chunmi.testcase.model.po.CaseDetail;
import com.chunmi.testcase.model.vo.CaseDetailVo;
import com.chunmi.testcase.utils.PageBean;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * @author sunny
 * @create 2018/11/12
 * @since 1.0.0
 */
public interface CaseDetailService {

    PageBean<CaseDetail> selectTestCaseList(CaseDetail testCase, Integer pageCurrent, Integer pageSize,
                                            Integer pageCount);

    CaseDetail selectTestCaseByConditions(String caseName);

    Integer addTestCase(CaseDetail testCase);

    CaseDetailVo selectTestCaseDetailById(Long id);

    Integer delTestCaseDetailById(Long id);

    Integer updateTestCase(CaseDetail caseDetail);

    PageBean<CaseDetailVo> selectExportTestCaseByConditions(CaseDetail caseDetail);

    void exportTestCase(PageBean<CaseDetailVo> pb, HttpServletResponse response);

    Integer batchInsertCaseDetail(List<CaseDetail> caseDetailList);

}