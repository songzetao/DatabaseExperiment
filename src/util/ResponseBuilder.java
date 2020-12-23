package util;

import com.alibaba.fastjson.JSON;

/**
 * 统一数据返回形式
 * 
 * @author xiaodong
 *
 */
public class ResponseBuilder {
	private int code;
	private String message;
	private Object data;

	public ResponseBuilder(int code, String message) {
		this.setCode(code);
		this.setMessage(message);

	}

	public ResponseBuilder(int code, String message, Object data) {
		this(code, message);
		this.data = data;

	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static String createJson(Object data) {
		return JSON.toJSONString(
				new ResponseBuilder(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data));
	}

	public static String createJson(ResponseCode responseCode) {
		return JSON.toJSONString(new ResponseBuilder(responseCode.getCode(), responseCode.getMessage()));
	}

	public static String createJson(ResponseCode responseCode, Object data) {
		return JSON.toJSONString(new ResponseBuilder(responseCode.getCode(), responseCode.getMessage(), data));
	}

}
