package com.chunmi.testcase.model.po;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
public class Users implements Serializable{
    
	/**
	 * 主键id
	 */
    private Long id;


    /**
     * 用户名
     */
    private String userName;

    
    /**
     * 用户密码
     */
    private String password;


    /**
     * 角色id
     */
    private Long roleId;


    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;


    /**
     * 是否禁用(0=否;1=是)
     */
    private String isDisabled;
    
    
    /**
     * 角色
     */
    private Role role;

}