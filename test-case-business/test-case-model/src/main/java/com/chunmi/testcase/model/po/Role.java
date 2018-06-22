package com.chunmi.testcase.model.po;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
public class Role implements Serializable{

	/**
	 * Ö÷¼üid
	 */
    private Long id;


    /**
     * ½ÇÉ«Ãû³Æ
     */
    private String roleName;
}