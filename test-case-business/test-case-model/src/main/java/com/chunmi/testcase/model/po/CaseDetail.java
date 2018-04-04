package com.chunmi.testcase.model.po;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.Module;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@JsonInclude(Include.NON_NULL)
public class CaseDetail implements Serializable{

	/*
	 * 主键id
	 */
    private Long id;


    /**
     * 测试用例名称
     */
    private String caseName;


    /**
     * 测试用例标题
     */
    private String caseTitle;


    /**
     * 优先级(0=低;1=中;2=高)
     */
    private String priority;

 
    /**
     * 测试方式(0=手动;1=自动)
     */
    private String testMode;


    /**
     * 模块id
     */
    private Long moduleId;


    /**
     * 测试前提
     */
    private String testConditions;


    /**
     * 详细步骤
     */
    private String detailSteps;


    /**
     * 期望结果
     */
    private String expectedResult;


    /**
     * 实际结果id
     */
    private Long actualResultId;


    /**
     * bug Id
     */
    private String bugId;


    /**
     * 备注
     */
    private String remarks;


    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;


    /**
     * 删除时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date delTime;


    /**
     * 删除标志(0=未删除;1=删除)
     */
    private String delFlag;
    
    
    /**
     * 项目模块
     */
    private Module module;
    
    
    /**
     * 实际结果
     */
    private ActualResult actualResult;
    
    
    /**
     * 项目id
     */
    private Long projectId;
    
    
    /**
     * 版本id
     */
    private Long versionId;
    
    
    /**
     * 项目
     */
    private Project project;
    
    
    /**
     * 版本
     */
    private ProjectVersion projectVersion;

}