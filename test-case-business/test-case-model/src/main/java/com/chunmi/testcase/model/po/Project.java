package com.chunmi.testcase.model.po;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
public class Project implements Serializable{

	/**
	 * ����id
	 */
    private Long id;


    /**
     * ��Ŀ����
     */
    private String projectName;


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