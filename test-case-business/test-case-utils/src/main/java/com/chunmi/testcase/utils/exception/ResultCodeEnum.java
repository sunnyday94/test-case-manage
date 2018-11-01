/**
 * This class was created by sunny. It's distributed as
 * part of the test-case-utils Mod.
 *
 * ��Ȩ����(C) Ψ��(�Ϻ�)����Ƽ����޹�˾ 2014-2023
 * Copyright 2014-2023 Vphotos TECHNOLOGY CO..
 *
 * This software is the confidential and proprietary information of
 * Vphotos Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Vphotos.
 *
 * File Created @ [2018��11��1��, ����4:04:58 (CST)]
 */

package com.chunmi.testcase.utils.exception;

public enum ResultCodeEnum {
    /*0-9999״̬����ʹ��,ϵͳ��״̬��*/
    �ɹ�(0, 200, "succeed"),
    δ����״̬��(1, 503, "unknow error"),
    ϵͳ�쳣(2, 500, "service error"),
    �����쳣(3, 400, "paramter error"),
    �汾����Ч(4, 403, "unknow version"),
    �������Դ������(5, 404, "not found"),
    ��ת��½(6,401,"go to login"),
    �ϴ����ļ����Ϸ�(7,400,"file illegality"),
    �ϴ��ļ�ʧ��(8,500,"file error"),


    /*10000-49999�ڲ�ϵͳ�Խӷ���״̬��*/
    �����Ѵ���(10000,200,"existed"),


    /*50000-99999Ϊ������ϵͳ����״̬��*/;
    public final int flag;
    public final int httpStatus;
    public final String defaultMsg;

    ResultCodeEnum(int f, int status, String msg) {
        this.flag = f;
        this.defaultMsg = msg;
        this.httpStatus = status;
    }

    public static ResultCodeEnum getByFlag(int v) {
        for (ResultCodeEnum x : ResultCodeEnum.values()) {
            if (x.flag == v) {
                return x;
            }
        }
        return null;
    }
}

