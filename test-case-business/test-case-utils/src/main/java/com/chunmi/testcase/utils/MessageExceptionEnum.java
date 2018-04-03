package com.chunmi.testcase.utils;

/**
 * 异常信息枚举�?
 * 
 * @author Niko.Yu
 *
 */
public enum MessageExceptionEnum {
	
	SUCCER_HANDLE(10000, "成功"),
	ERROR_USERNAME_OR_PASSWORD(10001, "用户名或密码错误"),
	ERROR_TOKEN_EXPIR(10002, "token已过�?"),
	ERROR_TOKEN_VALI_FAIL(10003, "token已失�?"),
	EMPTY_MOBILE(10004, "手机号不能为�?"),
	EMPTY_PASSWORD(10005, "密码不能为空"),
	EMPTY_UNIQUE(10006, "图形验证码标识不能为�?"),
	EMPTY_CONFIRMPASSWORD(10007, "确认密码不能为空"),
	PASSWORD_NOT_MATCH(10008, "两次密码不一�?"),
	CUSTOMER_HAS_REGISTERED(10009, "该用户已注册"),
	EMPTY_SIGN(10010, "签名不能为空"),
	SIGN_NOT_MATCH(10011, "签名不一�?"),
	EMPTY_OLD_PASSWORD(10012, "旧密码不能为�?"),
	WRONG_OLD_PASSWORD(10013, "旧密码错�?"),
	EMPTY_TOKEN(10014, "token不能为空"),
	EMPTY_LOGINTYPE(10015, "登录类型不能为空"),
	EMPTY_CUSTOMER_ID(10016, "用户ID不能为空"),
	ERROR_MOBILE_UNIQ(10017, "该手机号不是注册手机�?"),
	ERROR_MOBILE_CODE(10018, "手机短信码不能为�?"),
	ERROR_MOBILE_CODE_YOUWU(10019, "手机短信码不�?�?"),
	ERROR_MOBILE_CODE_FAIL(10020, "手机短信码已过期"),
	NONEXIT_TOKEN(10021, "token不存�?"),
	EMPTY_PIC(10022, "上传图片字符串不能为�?"),
	EMPTY_USERNAME(10023, "纯米账号不能为空"),
	EMPTY_XIAOMIUN(10024, "小米账号不能为空"),
	EMPTY_DEVICEID(10025, "设备ID不能为空"),
	EMPTY_MSGTYPE(10026, "发�?�验证码类型不能为空"),
	EMPTY_MSG_CODE(10027, "短信验证码不存在"),
	REAPTED_NICKNAME(10028, "昵称重复,请重新输入昵�?"),
	EMPTY_NICKNAME(10029, "用户昵称不能为空"),
	EMPTY_RANDOM(10030, "用户昵称不能为空"),
	SIGN_FALIURE(10031, "生成签名失败"),
	NO_SEARCH_RESULT(10032, "未查询到记录"),
	MI_LOGIN_FALIED(10033, "小米账号未登录成�?"),
	NO_SEARCH_CUSTOMER(10034, "未查询到用户"),
	ERROR_HANDLE(505, "服务器异常，请稍后重�?"), EMPTY_HEADPIC(10035, "图像不能为空"), 
	EMPTY_REALNAME(10036, "真实姓名不能为空"), EMPTY_GENDER(10037, "性别不能为空"),
	MI_BIND_OTHERS(10038, "小米账号已经绑定其他账号,更改小米账号后重�?"),
	SIGN_AGAIN_ERROR(10039, "用户已签�?,请勿再签�?"),
	EMPTY_TYPE(10040, "type不能为空"),
	UPDATE_ERROR(10041, "更新错误"),
	BUSINESS_EMPTY_ERROR(10042, "业务ID不能为空"), 
	BUSINESS_TYPE_ERROR(10043, "业务操作类型错误"),
	OPERATIONID_EMPTY_ERROR(10044,"操作者ID不能为空"),
	CUSTOMERID_EMPTY_ERROR(10045,"被操作�?�ID不能为空"),
	CUSTOMERID_OPERATIONID_EMPTY_ERROR(10046,"操作者ID和被操作者ID不能为空"),
	EMPTY_COUPONID(10047, "优惠券ID不能为空"),
	CUSTOMER_LEVEL_TOO_LOW(10048, "用户等级不够,无法操作此行�?"),
	EMPTY_COUPONNO(10049, "优惠券号码不能为�?"),
	EMPTY_EWXCHANGE_RULES(10050, "未查询到优惠券兑换规�?"),
	EXCEED_THE_UPPER_LIMIT(10051, "超过兑换上限"),
	EMPTY_GOODS_ID(10052, "商品ID不能为空"),
	EMPTY_ORDER_MONEY(10053, "订单金额不能为空"),
	ERROR_VERSION(10054, "版本号错�?"),
	CORRECT_PARAMS(10055, "OK"),
	SENSITIVE_NICKNAME(10056, "此昵称含有敏感词,请重新输�?"),
	ERROR_NO_CONFIG_TASKTYPE(10057,"没有任务类型的相关配�?"),
	ERROR_NO_TASK_RECORD(10058,"没有相应任务的完成记�?"),
	ERROR_NO_CONFIG_TASK(10059,"没有任务的相关配�?"),
	SAVE_RECORD_ERROR(10060,"保存经验积分记录不成�?"),
	USER_NOT_EXISTED_ERROR(10061,"用户不存�?"),
	PIC_TOO_LARGE(10062,"上传文件超过50MB，请重新上传"),
	NO_PERMISSION(10063,"你没有权限进行此操作!"), 
	TIMESETTINGISWRONG(10064,"结束时间必须大于�?始时�?"), 
	ERROR_EXPIRED(10065,"活动时间已结�?,活动�?启失�?"), 
	CANNOT_UPLOAD_REPEATEDLY(10066,"本次活动作品不可重复上传!"),
	ACTIVE_IS_CLOSED(10067,"活动已关�?,不能再投�?"), 
	VOTE_NUM_IS_MAX(10068,"投票次数已上�?"),
	PROJECT_EXISTED(10069,"项目已存在!"),
	VERSION_EXISTED(10070,"版本号已存在!");
	
	private Integer code;
	private String message;

	private MessageExceptionEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * 根据code查询�?个MessageExceptionEnum
	 * 
	 * @param code
	 * @return
	 */
	public static MessageExceptionEnum getByStatusID(int code) {
		MessageExceptionEnum[] bes = values();
		for (MessageExceptionEnum be : bes) {
			if (be.getCode() == code) {
				return be;
			}
		}
		return null;
	}

	/**
	 * 根据code 查询 message
	 * 
	 * @param code
	 * @return
	 */
	public static String getStatusName(int code) {
		MessageExceptionEnum[] bes = values();
		for (MessageExceptionEnum be : bes) {
			if (be.getCode() == code) {
				return be.getMessage();
			}
		}
		return null;
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
	
	
}
