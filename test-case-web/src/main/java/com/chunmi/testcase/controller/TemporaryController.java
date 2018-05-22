package com.chunmi.testcase.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chunmi.testcase.service.TemporaryService;
import com.chunmi.testcase.utils.Response;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TemporaryController {
	
	@Autowired
	private TemporaryService temporaryService;

	/**
	 * 导入测试用例
	 * @return
	 */
	@PostMapping(value="/importTestcase")
	@ResponseBody
	public Response importProjectName(MultipartFile file,Long projectId,Long versionId) {
		try {
			String fileName = file.getOriginalFilename();
			Workbook wb = null;		
			if(isExcel2003(fileName)){				
				wb = new HSSFWorkbook(file.getInputStream());// 解析xls格式
			}else if (isExcel2007(fileName)){
				wb = new XSSFWorkbook(file.getInputStream());// 解析xlsx格式
			}
			temporaryService.readContent(wb,projectId,versionId);
			return Response.getSuccess();
		} catch (Exception e) {
			log.error("errorMsg:{}",e.getMessage());
		}
		return null;
	}
	
	
	public  boolean isExcel2003(String filePath) {
		return filePath.matches("^.+\\.(?i)(xls)$");

	}

	public  boolean isExcel2007(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}
}
