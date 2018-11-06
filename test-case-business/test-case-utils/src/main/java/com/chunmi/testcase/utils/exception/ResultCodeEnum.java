/**
 * This class was created by sunny. It's distributed as
 * part of the test-case-utils Mod.
 *
 * 版权所有(C) 唯存(上海)网络科技有限公司 2014-2023
 * Copyright 2014-2023 Vphotos TECHNOLOGY CO..
 *
 * This software is the confidential and proprietary information of
 * Vphotos Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Vphotos.
 *
 * File Created @ [2018年11月1日, 下午4:04:58 (CST)]
 */

package com.chunmi.testcase.utils.exception;

public enum ResultCodeEnum {
    /*0-9999状态码务使用,系统级状态码*/
    成功(0, 200, "succeed"),
    未定义状态码(1, 503, "unknow error"),
    系统异常(2, 500, "service error"),
    参数异常(3, 400, "paramter error"),
    版本号无效(4, 403, "unknow version"),
    请求的资源不存在(5, 404, "not found"),
    跳转登陆(6,401,"go to login"),
    上传的文件不合法(7,400,"file illegality"),
    上传文件失败(8,500,"file error"),


    /*10000-49999内部系统对接返回状态码*/
    数据已存在(10000,200,"existed"),


    /*50000-99999为第三方系统返回状态码*/;
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

