package com.oa.enumutil;

import lombok.*;

/**
 * @author CHTW
 * @date 2020/3/19 13:23
 */
@Getter
@AllArgsConstructor
public enum AttendanceEnum {
    DAKASUCCESS(0,"打卡成功"),CHIDAO(1,"迟到"),ZAOTUI(2,"早退");
    private int code;
    private String message;

}
