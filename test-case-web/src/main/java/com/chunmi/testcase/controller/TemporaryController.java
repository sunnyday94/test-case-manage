package com.chunmi.testcase.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.chunmi.testcase.model.po.CaseDetail;
import com.chunmi.testcase.model.po.ProjectModule;
import com.chunmi.testcase.service.CaseDetailService;
import com.chunmi.testcase.service.ProjectModuleService;
import com.chunmi.testcase.utils.Response;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TemporaryController {
	
	@Autowired
	private ProjectModuleService moduleService;
	
	@Autowired
	private CaseDetailService caseDetailService;

	/**
	 * 导入测试用例
	 * @return
	 */
	@PostMapping(value="/importTestcase")
	@ResponseBody
	public Response importProjectName() {
		try {
			File file = new File("E:/testcase.xlsx"); 
			if (!file.exists()) {
				System.out.println("文件不存在");
			}
			InputStream inputStream = new FileInputStream(file);
			String fileName = file.getName();
			Workbook wb = null;		
			if(isExcel2003(fileName)){				
				wb = new HSSFWorkbook(inputStream);// 解析xls格式
			}else if (isExcel2007(fileName)){
				wb = new XSSFWorkbook(inputStream);// 解析xlsx格式
			}
			this.readContent(wb);
			return Response.getSuccess();
		} catch (Exception e) {
			log.error("errorMsg:{}",e.getMessage());
		}
		return null;
	}
	
	
	/**
	 * 读取excel内容
	 * @param wb
	 */
	public void readContent(Workbook wb) {
		Sheet sheet = wb.getSheetAt(0);// 第一个工作表
		int firstRowIndex = sheet.getFirstRowNum(); //获取第一行(从0开始)
		int lastRowIndex = sheet.getLastRowNum();   //获取最后一行(从0开始)
		
		List<CaseDetail> caseDetailList = new ArrayList<CaseDetail>();
		//从第二行开始(第一行为标题)
		for(int rIndex =firstRowIndex+1;rIndex<=lastRowIndex;rIndex++) {
			Row row = sheet.getRow(rIndex);
			if(row !=null) {
				CaseDetail caseDetail = new CaseDetail();
				int firstCellIndex = row.getFirstCellNum(); //获取第一个单元格(从0开始)
				int lastCellIndex = row.getLastCellNum(); //获取最后一个单元格(从1开始)
				for(int cIndex = firstCellIndex;cIndex<lastCellIndex;cIndex++) {
					Cell cell = row.getCell(cIndex);
					if(cell !=null) {
						if(cIndex == 0)
							caseDetail.setCaseName(cell.getStringCellValue().trim());
						if(cIndex ==1)
							caseDetail.setCaseTitle(cell.getStringCellValue().trim());
						if(cIndex == 2) {
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
						if(cIndex == 3) {
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
						if(cIndex == 4) {
							ProjectModule pm = new ProjectModule();
							pm.setProjectId(1L);
							pm.setModuleName(cell.getStringCellValue());
							pm = moduleService.selectModuleByProjectIdAndModuleName(pm);
							caseDetail.setModuleId(pm.getId());
						}
						if(cIndex == 5)
							caseDetail.setTestConditions(cell.getStringCellValue().trim());
						if(cIndex == 6)
							caseDetail.setDetailSteps(cell.getStringCellValue().trim());
						if(cIndex == 7)
							caseDetail.setExpectedResult(cell.getStringCellValue().trim());
						if(cIndex == 8) {
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
						if(cIndex == 9)
							caseDetail.setBugId(cell.getStringCellValue());
						if(cIndex == 10)
							caseDetail.setRemarks(cell.getStringCellValue());
					}
				}
				caseDetailList.add(caseDetail);
			}
		}
		if(caseDetailList!=null && caseDetailList.size()>0)
			caseDetailService.batchInsertCaseDetail(caseDetailList);
	}
	
	
	
	public  boolean isExcel2003(String filePath) {
		return filePath.matches("^.+\\.(?i)(xls)$");

	}

	public  boolean isExcel2007(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}
}
