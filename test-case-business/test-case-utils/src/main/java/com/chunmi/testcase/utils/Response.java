package com.chunmi.testcase.utils;

/**
 * 系统接口返回消息
 * ClassName: Response.
 * Function: TODO ADD FUNCTION.
 * Reason: TODO ADD REASON(可选).
 * date: 2018年3月30日-下午5:05:21.
 * @author sunny
 * @version
 */
public class Response {

	public static final String TOKEN = "123321";

	private Integer code;

	private String message = "处理成功";

	private Object result;

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

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public static Response getSuccess() {
		Response response = new Response();
		response.setCode(MessageExceptionEnum.SUCCER_HANDLE.getCode());
		response.setMessage(MessageExceptionEnum.SUCCER_HANDLE.getMessage());
		response.setResult(null);
		return response;
	}

	public static Response getSuccess(Object result) {
		Response response = new Response();
		response.setCode(MessageExceptionEnum.SUCCER_HANDLE.getCode());
		response.setMessage(MessageExceptionEnum.SUCCER_HANDLE.getMessage());
		response.setResult(result);
		return response;
	}

	public static Response buildSuccess(Integer code, Object result, String message) {
		Response r = new Response();
		r.setCode(code);
		r.setResult(result);
		r.setMessage(message);
		return r;
	}

	public static Response getError(MessageExceptionEnum mess) {
		Response response = new Response();
		response.setCode(mess.getCode());
		response.setResult(null);
		response.setMessage(mess.getMessage());
		return response;
	}

	public static Response buildResult(MessageExceptionEnum mess) {
		Response response = new Response();
		response.setCode(mess.getCode());
		response.setResult(null);
		response.setMessage(mess.getMessage());
		return response;
	}

}