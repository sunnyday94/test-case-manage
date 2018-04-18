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

}