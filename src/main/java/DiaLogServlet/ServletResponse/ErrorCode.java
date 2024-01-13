package DiaLogServlet.ServletResponse;

public enum ErrorCode {

    SUCCESS(200, "SUCCESS"),
    DATA_NOT_FOUND_ERROR(40400, "DATA_NOT_FOUND_ERROR"),
    NO_AUTH_ERROR(40101, "UNAUTHORIZED"),
    USER_EXIST(40102, "USER_EXIST"),
    DIFFERENT_PASSWORD(40103,"DIFFERENT_PASSWORD" ),
    OPERATION_ERROR(50001, "OPERATION_ERROR");

    private final int code;

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
