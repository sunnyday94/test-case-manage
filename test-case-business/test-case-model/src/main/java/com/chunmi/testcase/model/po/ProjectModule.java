package com.chunmi.testcase.model.po;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@JsonInclude(Include.NON_NULL)
public class ProjectModule implements Serializable{

	/**
	 * ����id
	 */
    private Long id;


    /**
     * ģ������
     */
    private String moduleName;


    /**
     * ��Ŀid
     */
    private Long projectId;
    
    
    /**
     * ��Ŀ
     */
    private Project project;
    
    
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

}