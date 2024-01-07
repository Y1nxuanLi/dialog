package DiaLogServlet.Response;

/**
 * 错误码
 *
 * @author oldwai
 */
public enum ErrorCode {

    SUCCESS(200, "SUCCESS"),
    DATA_NOT_FOUND_ERROR(40400, "DATA_NOT_FOUND_ERROR"),
    NO_AUTH_ERROR(40101, "UNAUTHORIZED"),
    USER_EXIST(40102, "USER_EXIST"),
    OPERATION_ERROR(50001, "OPERATION_ERROR"),
    PARAMS_ERROR(40000, "请求参数错误"),
    NOT_LOGIN_ERROR(40100, "未登录"),

    FORBIDDEN_ERROR(40300, "禁止访问"),
    SYSTEM_ERROR(50000, "系统内部异常");


    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;

    ErrorCode(int code, String message) {
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
