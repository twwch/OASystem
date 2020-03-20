package com.oa.enumutil;

import lombok.*;

/**
 * @author CHTW
 * @date 2020/3/19 13:23
 */
@Getter
@AllArgsConstructor
public enum AttendanceEnum {
    YIDAKA(0,"打卡成功"),WDAKA(1,"未打卡"),DAKA2(2,"已打卡，但时间或地点不正确");
    private int code;
    private String message;

}
