package com.oa.enumutil;

/**
 * @author CHTW
 * @date 2020/3/19 11:14
 */
public enum LeaveSalesEnum {
    QING_WEI(0,"未审核"),QING_YI(1,"审核通过"),QING_NO(2,"审核未通过"),
    YIXIAO(0,"销假"),WEIXIAO(1,"未销假");
    private int code;
    private String message;

    LeaveSalesEnum(int code, String message) {
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
