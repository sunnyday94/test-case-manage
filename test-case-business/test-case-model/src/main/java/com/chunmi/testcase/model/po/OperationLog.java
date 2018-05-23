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
public class OperationLog implements Serializable{

	/**
	 * 主键id
	 */
    private Long id;

    /**
     * 操作人id
     */
    private Long userId;
    
    
    /**
     * 参数
     */
    private String params;

    /**
     * 方法名
     */
    private String method;

    /**
     * 描述信息
     */
    private String message;

    /**
     * 操作时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date operationTime;

    /**
     * 删除时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date delTime;

    /**
     * 删除标记
     */
    private String delFlag;
    
    
    /**
     * 用户信息
     */
    private Users users;

}