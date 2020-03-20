package com.oa.enumutil;

/**
 * @author CHTW
 * @date 2020/3/18 13:01
 */
public enum Result {
    SUCCESS(200,"成功"),FAIL(500,"失败");

    private int code;
    private String message;

    private Result(int code, String message) {
        this.code = code;
        this.message = message;
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
}
