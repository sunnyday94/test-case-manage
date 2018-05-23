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
	 * ����id
	 */
    private Long id;

    /**
     * ������id
     */
    private Long userId;
    
    
    /**
     * ����
     */
    private String params;

    /**
     * ������
     */
    private String method;

    /**
     * ������Ϣ
     */
    private String message;

    /**
     * ����ʱ��
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date operationTime;

    /**
     * ɾ��ʱ��
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date delTime;

    /**
     * ɾ�����
     */
    private String delFlag;
    
    
    /**
     * �û���Ϣ
     */
    private Users users;

}