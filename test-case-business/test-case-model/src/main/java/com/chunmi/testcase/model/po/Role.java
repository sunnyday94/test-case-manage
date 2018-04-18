package com.chunmi.testcase.model.po;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@JsonInclude(Include.NON_NULL)
public class Role implements Serializable{

	/**
	 * ����id
	 */
    private Long id;


    /**
     * ��ɫ����
     */
    private String roleName;
}