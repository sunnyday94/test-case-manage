/**
 * FileName: MessageExceptionEnum
 * Author:   sunny
 * Date:     2018/11/12 22:04
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chunmi.testcase.utils;

import lombok.Data;

/**
 * 〈一句话功能简述〉
 * @author sunny
 * @create 2018/11/12
 * @since 1.0.0
 */
public enum MessageExceptionEnum {
    SUCCER_HANDLE(10000, "成功"),
    ERROR_USERNAME_OR_PASSWORD(10001, "用户名或密码错误"),
    ERROR_TOKEN_EXPIR(10002, "token已过期"),
    ERROR_TOKEN_VALI_FAIL(10003, "token已失效"),
    EMPTY_MOBILE(10004, "手机号不能为空"),
    EMPTY_PASSWORD(10005, "密码不能为空"),
    EMPTY_UNIQUE(10006, "图形验证码标识不能为空"),
    EMPTY_CONFIRMPASSWORD(10007, "确认密码不能为空"),
    PASSWORD_NOT_MATCH(10008, "两次密码不一致"),
    CUSTOMER_HAS_REGISTERED(10009, "该用户已注册"),
    EMPTY_SIGN(10010, "签名不能为空"),
    SIGN_NOT_MATCH(10011, "签名不一致"),
    EMPTY_OLD_PASSWORD(10012, "旧密码不能为空"),
    WRONG_OLD_PASSWORD(10013, "旧密码错误"),
    EMPTY_TOKEN(10014, "token不能为空"),
    EMPTY_LOGINTYPE(10015, "登录类型不能为空"),
    EMPTY_CUSTOMER_ID(10016, "用户ID不能为空"),
    ERROR_MOBILE_UNIQ(10017, "该手机号不是注册手机号"),
    ERROR_MOBILE_CODE(10018, "手机短信码不能为空"),
    ERROR_MOBILE_CODE_YOUWU(10019, "手机短信码不能为空"),
    ERROR_MOBILE_CODE_FAIL(10020, "手机短信码已过期"),
    NONEXIT_TOKEN(10021, "token不存在"),
    EMPTY_PIC(10022, "上传图片字符串不能为空"),
    EMPTY_USERNAME(10023, "纯米账号不能为空"),
    EMPTY_XIAOMIUN(10024, "小米账号不能为空"),
    EMPTY_DEVICEID(10025, "设备ID不能为空"),
    EMPTY_MSGTYPE(10026, "发送验证码类型不能为空"),
    EMPTY_MSG_CODE(10027, "短信验证码不存在"),
    REAPTED_NICKNAME(10028, "昵称重复,请重新输入昵称"),
    EMPTY_NICKNAME(10029, "用户昵称不能为空"),
    EMPTY_RANDOM(10030, "用户昵称不能为空"),
    SIGN_FALIURE(10031, "生成签名失败"),
    NO_SEARCH_RESULT(10032, "未查询到记录"),
    MI_LOGIN_FALIED(10033, "小米账号未登录成功"),
    NO_SEARCH_CUSTOMER(10034, "未查询到用户"),
    ERROR_HANDLE(505, "服务器异常，请稍后重试"), EMPTY_HEADPIC(10035, "图像不能为空"),
    EMPTY_REALNAME(10036, "真实姓名不能为空"), EMPTY_GENDER(10037, "性别不能为空"),
    MI_BIND_OTHERS(10038, "小米账号已经绑定其他账号,更改小米账号后重试"),
    SIGN_AGAIN_ERROR(10039, "用户已签到,请勿再签到"),
    EMPTY_TYPE(10040, "type不能为空"),
    UPDATE_ERROR(10041, "更新错误"),
    BUSINESS_EMPTY_ERROR(10042, "业务ID不能为空"),
    BUSINESS_TYPE_ERROR(10043, "业务操作类型错误"),
    OPERATIONID_EMPTY_ERROR(10044,"操作者ID不能为空"),
    CUSTOMERID_EMPTY_ERROR(10045,"被操作者ID不能为空"),
    CUSTOMERID_OPERATIONID_EMPTY_ERROR(10046,"操作者ID和被操作者ID不能为空"),
    EMPTY_COUPONID(10047, "优惠券ID不能为空"),
    CUSTOMER_LEVEL_TOO_LOW(10048, "用户等级不够,无法操作此行操作"),
    EMPTY_COUPONNO(10049, "优惠券号码不能为空"),
    EMPTY_EWXCHANGE_RULES(10050, "未查询到优惠券兑换规则"),
    EXCEED_THE_UPPER_LIMIT(10051, "超过兑换上限"),
    EMPTY_GOODS_ID(10052, "商品ID不能为空"),
    EMPTY_ORDER_MONEY(10053, "订单金额不能为空"),
    ERROR_VERSION(10054, "版本号错误"),
    CORRECT_PARAMS(10055, "OK"),
    SENSITIVE_NICKNAME(10056, "此昵称含有敏感词,请重新输入"),
    ERROR_NO_CONFIG_TASKTYPE(10057,"没有任务类型的相关配置"),
    ERROR_NO_TASK_RECORD(10058,"没有相应任务的完成记录"),
    ERROR_NO_CONFIG_TASK(10059,"没有任务的相关配置"),
    SAVE_RECORD_ERROR(10060,"保存经验积分记录不成功"),
    USER_NOT_EXISTED_ERROR(10061,"用户不存在"),
    PIC_TOO_LARGE(10062,"上传文件超过50MB，请重新上传"),
    NO_PERMISSION(10063,"你没有权限进行此操作!"),
    TIMESETTINGISWRONG(10064,"结束时间必须大于始时��??"),
    ERROR_EXPIRED(10065,"活动时间已结束,活动开启失败"),
    CANNOT_UPLOAD_REPEATEDLY(10066,"本次活动作品不可重复上传!"),
    ACTIVE_IS_CLOSED(10067,"活动已关闭,不能再投票"),
    VOTE_NUM_IS_MAX(10068,"投票次数已上限"),
    PROJECT_EXISTED(10069,"项目已存在!"),
    VERSION_EXISTED(10070,"版本号已存在!"),
    MODULE_EXISTED(10071,"项目模块已存在!"),
    TESTCASE_EXISTED(10072,"用例名称已存在!");

    private Integer code;
    private String message;

    MessageExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
      * @Description: 根据code查询单个枚举异常
      * @Author: sunny
      * @Date: 22:07 2018/11/12
      */
    public static MessageExceptionEnum getMessageExceptionEnumByCode(Integer code){
        MessageExceptionEnum[] bes = values();
        for(MessageExceptionEnum e : bes){
            if(e.getCode().equals(code))
                return e;
        }
        return null;
    }

    /**
      * @Description: 根据code查询异常信息
      * @Author: sunny
      * @Date: 22:09 2018/11/12
      */
    public static String getExceptionMessageByCode(Integer code){
        MessageExceptionEnum[] bes = values();
        for(MessageExceptionEnum e :bes){
            if(e.getCode().equals(code))
                return e.getMessage();
        }
        return null;
    }
}