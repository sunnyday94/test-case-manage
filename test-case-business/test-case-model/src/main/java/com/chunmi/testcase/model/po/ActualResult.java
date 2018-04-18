package com.chunmi.testcase.model.po;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@JsonInclude(Include.NON_NULL)
public class ActualResult implements Serializable{

	/**
	 * 主键id
	 */
    private Long id;

    /**
     * 实际结果
     */
    private String actualResult;
}