package com.chunmi.testcase.model.po;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@JsonInclude(Include.NON_NULL)
public class ProjectModule implements Serializable{

	/**
	 * 主键id
	 */
    private Long id;


    /**
     * 模块名称
     */
    private String moduleName;


    /**
     * 项目id
     */
    private Long projectId;
    
    
    /**
     * 项目
     */
    private Project project;

}