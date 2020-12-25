package util;

/**
 * 返回响应的状态码
 * 
 * @author xiaodong
 *
 */
public enum ResponseCode {
	SUCCESS(1000, "success"),                       
	SERVICE_ERROR(1004, "service error"),
    INVALID_USER(2000, "invalid user"),
    USER_LOGIN_SUCCESS(2001, "user login success"),
	USER_LOGIN_FAILED(2002, "user login failed"),
	USER_LOGOUT_SUCCESS(2003, "user logout success"),
	QUERY_FAILED(3001, "query failed");         

	private int code; // 状态码
	private String message; // 返回信息

	ResponseCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}