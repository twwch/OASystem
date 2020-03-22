package com.oa.service;

import com.github.pagehelper.PageInfo;
import com.oa.bean.Attendance;

/**
 * @author CHTW
 * @date 2020/3/22 11:28
 */
public interface AttendanceService {
    /**
     * 获取打卡成功列表
     * @author CHTW
     * @return
     */
    PageInfo<Attendance> getAttendanceList(int nowpage, int size, String eId, String name,int code);
}
