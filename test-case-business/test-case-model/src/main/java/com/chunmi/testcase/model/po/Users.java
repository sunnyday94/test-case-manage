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
	 * ����id
	 */
    private Long id;


    /**
     * �û���
     */
    private String userName;

    
    /**
     * �û�����
     */
    private String password;


    /**
     * ��ɫid
     */
    private Long roleId;


    /**
     * ����ʱ��
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;


    /**
     * �Ƿ����(0=��;1=��)
     */
    private String isDisabled;
    
    
    /**
     * ��ɫ
     */
    private Role role;

}