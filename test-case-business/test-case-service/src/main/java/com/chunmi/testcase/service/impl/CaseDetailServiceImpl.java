/**
 * This class was created by sunny. It's distributed as
 * part of the test-case-service Mod.
 *
 * ��Ȩ����(C) �Ϻ����׵��ӿƼ����޹�˾ 2014-2023
 * Copyright 2014-2023 CHUNMI TECHNOLOGY CO..
 *
 * This software is the confidential and proprietary information of
 * CHUNMI Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with CHUNMI.
 *
 * File Created @ [2018��4��8��, ����10:34:25 (CST)]
 */
package com.chunmi.testcase.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chunmi.testcase.mapper.CaseDetailMapper;
import com.chunmi.testcase.model.po.CaseDetail;
import com.chunmi.testcase.model.vo.CaseDetailVo;
import com.chunmi.testcase.service.CaseDetailService;
import com.chunmi.testcase.utils.PageBean;
import com.chunmi.testcase.utils.PageRequest;

@Service
public class CaseDetailServiceImpl implements CaseDetailService {
	
	@Autowired
	private CaseDetailMapper caseDetailMapper;
	
	@Override
	public PageBean<CaseDetail> selectTestCaseList(CaseDetail testCase, Integer pageCurrent, Integer pageSize,
			Integer pageCount) {
		PageBean<CaseDetail> pb = new PageBean<CaseDetail>();
		//�ж�
		if(pageCurrent==0) pageCurrent =1;  
		if(pageSize==0)  pageSize = 12;  //ÿҳ��ʾ12������
		Integer rows = caseDetailMapper.selectTestCaseCountsByCondition(testCase);
		pb.setRows(rows);
		//�����ҳ
		pageCount = rows%pageSize == 0 ? (rows/pageSize) : (rows/pageSize) + 1;
		//������ҳ��
		pageCount = pageCount ==0 ? 1: pageCount;  
		//�����ǰҳ>=���ҳ,�����õ�ǰҳΪ���ҳ
		if(pageCurrent>=pageCount) {
			pb.setPageCurrent(pageCount);
		}
		PageRequest pageRequest = new PageRequest(pageCurrent, pageSize);
		pb.setPageCurrent(pageCurrent);  //���õ�ǰҳ
		pb.setPageSize(pageSize);       //ÿҳ��ʾ��Ŀ
		pb.setPageCount(pageCount);     //��ҳ��
		pb.setObjectBean(testCase);      //���ò�ѯ����
		List<CaseDetail> testCaseList = caseDetailMapper.selectTestCaseListByCondition(pageRequest,testCase);
		pb.setList(testCaseList);
		return pb;
	}

	@Override
	public CaseDetail selectTestCaseByConditions(String caseName) {
		return caseDetailMapper.selectTestCaseByConditions(caseName);
	}

	@Override
	public Integer addTestCase(CaseDetail testCase) {
		return caseDetailMapper.insertSelective(testCase);
	}

	@Override
	public CaseDetailVo selectTestCaseDetailById(Long id) {
		return caseDetailMapper.selectTestCaseDetailById(id);
	}

	@Override
	public Integer delTestCaseDetailById(Long id) {
		return caseDetailMapper.delTestCaseDetailById(id);
	}

	@Override
	public Integer updateTestCase(CaseDetail caseDetail) {
		return caseDetailMapper.updateByPrimaryKeySelective(caseDetail);
	}

	@Override
	public PageBean<CaseDetailVo> selectExportTestCaseByConditions(CaseDetail caseDetail) {
		PageBean<CaseDetailVo> pb = new PageBean<CaseDetailVo>();
		List<CaseDetailVo> caseDetailList = caseDetailMapper.selectExportTestCaseByConditions(caseDetail);
		pb.setList(caseDetailList);
		return pb;
	}

	@Override
	public void exportTestCase(PageBean<CaseDetailVo> pb, HttpServletResponse response) {
		List<CaseDetailVo> caseDetailList = pb.getList();
		// ��һ�� ����һ��webbook,��Ӧһ��excel�ļ�
		HSSFWorkbook wb = new HSSFWorkbook();
	    // �ڶ��� ��webbook�����sheet����Ӧexcel�е�sheet
		HSSFSheet sheet = wb.createSheet("��������");
	    // ������ ��sheet����ӱ�ͷ��0�У��˴���Ҫע���ϰ汾poi��Excel����������������short
		HSSFRow row = sheet.createRow((int) 0);
	    // ���Ĳ� ������Ԫ����ʽ
		HSSFCellStyle style = wb.createCellStyle();
	    HSSFFont hssfFont = wb.createFont(); // ����������ʽ
		hssfFont.setFontName("����_GB2312"); // ����
		hssfFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//������ʾ 
	    style.setFont(hssfFont); 
	    
	    style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ����  
	    style.setFillForegroundColor(IndexedColors.YELLOW.getIndex()); // ���ñ���ɫ 
		style.setFillPattern(CellStyle.SOLID_FOREGROUND); 
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //�±߿�    
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//��߿�    
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);//�ϱ߿�    
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);//�ұ߿�   
					
	    // ���ÿ��
		sheet.setColumnWidth(0, 1000);
		sheet.setColumnWidth(1, 6000);
		sheet.setColumnWidth(2, 24000);
		sheet.setColumnWidth(3, 2000);
		sheet.setColumnWidth(4, 2000);
		sheet.setColumnWidth(5, 6000); 
		sheet.setColumnWidth(6, 30000);	
		sheet.setColumnWidth(7, 18000);	
		sheet.setColumnWidth(8, 18000);	
		sheet.setColumnWidth(9, 2000);	
		sheet.setColumnWidth(10, 2000);	
		sheet.setColumnWidth(11, 6000);	
		
		//���岽 ������Ԫ��
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("���");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("��������");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("��������");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("���ȼ�");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("���Է�ʽ");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("ģ��");
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellValue("����ǰ��");
		cell.setCellStyle(style);
		cell = row.createCell(7);
		cell.setCellValue("��ϸ����");
		cell.setCellStyle(style);
		cell = row.createCell(8);
		cell.setCellValue("�������");
		cell.setCellStyle(style);
		cell = row.createCell(9);
		cell.setCellValue("ʵ�ʽ��");
		cell.setCellStyle(style);
		cell = row.createCell(10);
		cell.setCellValue("BUG_ID");
		cell.setCellStyle(style);
		cell = row.createCell(11);
		cell.setCellValue("��ע");
		cell.setCellStyle(style);
		
		OutputStream outputStream = null;
		try {
			for(int i =0;i<caseDetailList.size();i++){
				CaseDetailVo caseDetail = caseDetailList.get(i);
				row = sheet.createRow(i+1);
				row.setHeightInPoints(40); //�����и�
				row.createCell(0).setCellValue(caseDetail.getId());
				row.createCell(1).setCellValue(caseDetail.getCaseName());
				row.createCell(2).setCellValue(caseDetail.getCaseTitle());
				row.createCell(3).setCellValue(caseDetail.getPriority());
				row.createCell(4).setCellValue(caseDetail.getTestMode());
				row.createCell(5).setCellValue(caseDetail.getModuleName());
				row.createCell(6).setCellValue(caseDetail.getTestConditions());
				row.createCell(7).setCellValue(caseDetail.getDetailSteps());
				row.createCell(8).setCellValue(caseDetail.getExpectedResult());
				row.createCell(9).setCellValue(caseDetail.getActualResultValue());
				row.createCell(10).setCellValue(caseDetail.getBugId());
				row.createCell(11).setCellValue(caseDetail.getRemarks());
			}
			outputStream = response.getOutputStream();
			wb.write(outputStream);
			outputStream.flush();			
		} catch (Exception e) {
			e.getMessage();
		}finally {
			if(outputStream!=null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
					
				}
			}
		}
		
	}

	@Override
	public Integer batchInsertCaseDetail(List<CaseDetail> caseDetailList) {
		return caseDetailMapper.batchInsertCaseDetail(caseDetailList);
	}

}
