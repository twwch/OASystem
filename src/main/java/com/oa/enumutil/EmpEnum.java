package com.oa.enumutil;

/**
 * @author CHTW
 * @date 2020/3/20 21:27
 */
public enum EmpEnum {
    ISADMIN(0,"是管理员"),NOTADMIN(1,"不是管理员");

    private int code;
    private String message;

    private EmpEnum(int code, String message) {
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
