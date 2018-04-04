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
	 * ����id
	 */
    private Long id;


    /**
     * ������������
     */
    private String caseName;


    /**
     * ������������
     */
    private String caseTitle;


    /**
     * ���ȼ�(0=��;1=��;2=��)
     */
    private String priority;

 
    /**
     * ���Է�ʽ(0=�ֶ�;1=�Զ�)
     */
    private String testMode;


    /**
     * ģ��id
     */
    private Long moduleId;


    /**
     * ����ǰ��
     */
    private String testConditions;


    /**
     * ��ϸ����
     */
    private String detailSteps;


    /**
     * �������
     */
    private String expectedResult;


    /**
     * ʵ�ʽ��id
     */
    private Long actualResultId;


    /**
     * bug Id
     */
    private String bugId;


    /**
     * ��ע
     */
    private String remarks;


    /**
     * ����ʱ��
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;


    /**
     * ɾ��ʱ��
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date delTime;


    /**
     * ɾ����־(0=δɾ��;1=ɾ��)
     */
    private String delFlag;
    
    
    /**
     * ��Ŀģ��
     */
    private Module module;
    
    
    /**
     * ʵ�ʽ��
     */
    private ActualResult actualResult;
    
    
    /**
     * ��Ŀid
     */
    private Long projectId;
    
    
    /**
     * �汾id
     */
    private Long versionId;
    
    
    /**
     * ��Ŀ
     */
    private Project project;
    
    
    /**
     * �汾
     */
    private ProjectVersion projectVersion;

}