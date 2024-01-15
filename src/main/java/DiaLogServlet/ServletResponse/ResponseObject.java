/*

A Java Object Class to store response message
able to store whether 2 or 3 parameter depending on whether data is attached

 */

package DiaLogServlet.ServletResponse;

public class ResponseObject {
    private int code;
    private String message;
    private Object data;

    // Constructor for responses without additional data
    public ResponseObject(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    // Constructor for responses with additional data
    public ResponseObject(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // Getters and Setters
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
}
