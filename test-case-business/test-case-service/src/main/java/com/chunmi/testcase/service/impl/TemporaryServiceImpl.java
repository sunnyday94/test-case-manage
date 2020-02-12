/**
 * FileName: TemporaryServiceImpl
 * Author:   sunny
 * Date:     2018/11/12 22:39
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chunmi.testcase.service.impl;

import com.chunmi.testcase.model.po.CaseDetail;
import com.chunmi.testcase.model.po.ProjectModule;
import com.chunmi.testcase.service.CaseDetailService;
import com.chunmi.testcase.service.ProjectModuleService;
import com.chunmi.testcase.service.TemporaryService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * @author sunny
 * @create 2018/11/12
 * @since 1.0.0
 */
@Service
public class TemporaryServiceImpl implements TemporaryService {

    @Resource
    private ProjectModuleService projectModuleService;

    @Resource
    private CaseDetailService caseDetailService;

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void readContent(Workbook wb, Long projectId, Long versionId) {
        Sheet sheet = wb.getSheetAt(0);// 第一个工作表
        int firstRowIndex = sheet.getFirstRowNum(); //获取第一行(从0开始)
        int lastRowIndex = sheet.getLastRowNum();   //获取最后一行(从0开始)

        List<CaseDetail> caseDetailList = new ArrayList<>();
        //从第二行开始(第一行为标题)
        for(int rIndex =firstRowIndex+1;rIndex<=lastRowIndex;rIndex++) {
            Row row = sheet.getRow(rIndex);
            if(row !=null) {
                CaseDetail caseDetail = new CaseDetail();
                caseDetail.setProjectId(projectId);
                caseDetail.setVersionId(versionId);
                int firstCellIndex = row.getFirstCellNum(); //获取第一个单元格(从0开始)
                int lastCellIndex = row.getLastCellNum(); //获取最后一个单元格(从1开始)
                for(int cIndex = firstCellIndex;cIndex<lastCellIndex;cIndex++) {
                    Cell cell = row.getCell(cIndex);
                    if(cell !=null) {
                        if(cIndex == 1)
                            caseDetail.setCaseName(cell.getStringCellValue().trim());
                        if(cIndex ==2)
                            caseDetail.setCaseTitle(cell.getStringCellValue().trim());
                        if(cIndex == 3) {
                            String priority = cell.getStringCellValue().trim();
                            switch (priority) {
                                case "低":
                                    priority = "0";
                                    break;
                                case "中":
                                    priority = "1";
                                    break;
                                case "高":
                                    priority = "2";
                                    break;
                                default:
                                    break;
                            }
                            caseDetail.setPriority(priority);
                        }
                        if(cIndex == 4) {
                            String testMode = cell.getStringCellValue().trim();
                            switch (testMode) {
                                case "手动":
                                    testMode = "0";
                                    break;
                                case "自动":
                                    testMode = "1";
                                    break;
                                default:
                                    break;
                            }
                            caseDetail.setTestMode(testMode);
                        }
                        if(cIndex == 5) {
                            ProjectModule pm = new ProjectModule();
                            pm.setProjectId(projectId);
                            pm.setModuleName(cell.getStringCellValue());
                            ProjectModule selectPm = projectModuleService.selectModuleByProjectIdAndModuleName(pm);
                            if(selectPm==null) {
                                projectModuleService.insertNewModule(pm);
                                caseDetail.setModuleId(pm.getId());
                            }else {
                                caseDetail.setModuleId(selectPm.getId());
                            }
                        }
                        if(cIndex == 6)
                            caseDetail.setTestConditions(cell.getStringCellValue().trim());
                        if(cIndex == 7)
                            caseDetail.setDetailSteps(cell.getStringCellValue().trim());
                        if(cIndex == 8)
                            caseDetail.setExpectedResult(cell.getStringCellValue().trim());
                        if(cIndex == 9) {
                            String actualResult  = cell.getStringCellValue().trim().toUpperCase();
                            Long actualResultId = null;
                            switch (actualResult) {
                                case "PASS":
                                    actualResultId = 1L;
                                    break;
                                case "Failed":
                                    actualResultId = 2L;
                                    break;
                                case "N/A":
                                    actualResultId = 3L;
                                    break;
                                default:
                                    actualResultId = 4L;
                                    break;
                            }
                            caseDetail.setActualResultId(actualResultId);
                        }
                        if(cIndex == 10)
                            caseDetail.setBugId(cell.getStringCellValue());
                        if(cIndex == 11)
                            caseDetail.setRemarks(cell.getStringCellValue());
                    }
                }
                caseDetailList.add(caseDetail);
            }
        }
        if(caseDetailList.size() > 0)
            caseDetailService.batchInsertCaseDetail(caseDetailList);
    }
}
