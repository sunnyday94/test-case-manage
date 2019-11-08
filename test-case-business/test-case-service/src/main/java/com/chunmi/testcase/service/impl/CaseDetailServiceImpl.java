/**
 * This class was created by sunny. It's distributed as
 * part of the test-case-service Mod.
 *
 * 版权所有(C) 上海纯米电子科技有限公司 2014-2023
 * Copyright 2014-2023 CHUNMI TECHNOLOGY CO..
 *
 * This software is the confidential and proprietary information of
 * CHUNMI Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with CHUNMI.
 *
 * File Created @ [2018年4月8日, 上午10:34:25 (CST)]
 */
package com.chunmi.testcase.service.impl;

import com.chunmi.testcase.mapper.CaseDetailMapper;
import com.chunmi.testcase.model.po.CaseDetail;
import com.chunmi.testcase.model.vo.CaseDetailVo;
import com.chunmi.testcase.service.CaseDetailService;
import com.chunmi.testcase.utils.PageBean;
import com.chunmi.testcase.utils.PageRequest;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class CaseDetailServiceImpl implements CaseDetailService {

    @Resource
    private CaseDetailMapper caseDetailMapper;

    @Override
    public PageBean<CaseDetail> selectTestCaseList(CaseDetail testCase, Integer pageCurrent, Integer pageSize,
                                                   Integer pageCount) {
        PageBean<CaseDetail> pb = new PageBean<CaseDetail>();
        //判断
        if(pageCurrent==0) pageCurrent =1;
        if(pageSize==0)  pageSize = 12;  //每页显示12条数据
        Integer rows = caseDetailMapper.selectTestCaseCountsByCondition(testCase);
        pb.setRows(rows);
        //计算分页
        pageCount = rows%pageSize == 0 ? (rows/pageSize) : (rows/pageSize) + 1;
        //设置总页数
        pageCount = pageCount ==0 ? 1: pageCount;
        //如果当前页>=最大页,则设置当前页为最大页
        if(pageCurrent>=pageCount) {
            pb.setPageCurrent(pageCount);
        }
        PageRequest pageRequest = new PageRequest(pageCurrent, pageSize);
        pb.setPageCurrent(pageCurrent);  //设置当前页
        pb.setPageSize(pageSize);       //每页显示条目
        pb.setPageCount(pageCount);     //总页数
        pb.setObjectBean(testCase);      //设置查询条件
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
        // 第一步 创建一个webbook,对应一个excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步 在webbook中添加sheet，对应excel中的sheet
        HSSFSheet sheet = wb.createSheet("测试用例");
        // 第三步 在sheet中添加表头第0行，此处需要注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步 创建单元格样式
        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont hssfFont = wb.createFont(); // 创建字体样式
        hssfFont.setFontName("仿宋_GB2312"); // 仿宋
        hssfFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        style.setFont(hssfFont);

        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex()); // 设置背景色 (Yellow)
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        // 设置宽度
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

        //第五步 创建单元格
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("编号");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("用例名称");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("用例标题");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("优先级");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("测试方式");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("模块");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("测试前提");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue("详细步骤");
        cell.setCellStyle(style);
        cell = row.createCell(8);
        cell.setCellValue("期望结果");
        cell.setCellStyle(style);
        cell = row.createCell(9);
        cell.setCellValue("实际结果");
        cell.setCellStyle(style);
        cell = row.createCell(10);
        cell.setCellValue("BUG_ID");
        cell.setCellStyle(style);
        cell = row.createCell(11);
        cell.setCellValue("备注");
        cell.setCellStyle(style);

        OutputStream outputStream = null;
        try {
            for(int i =0;i<caseDetailList.size();i++){
                CaseDetailVo caseDetail = caseDetailList.get(i);
                row = sheet.createRow(i+1);
                row.setHeightInPoints(40); //设置行高
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
            outputStream.close();
            wb.close();
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
