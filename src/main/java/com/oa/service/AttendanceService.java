package com.oa.service;

import com.github.pagehelper.PageInfo;
import com.oa.bean.Attendance;

import java.util.List;

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

    /**
     * 员工端获取打开成功列表
     * @param attendance
     * @return
     */
    List<Attendance> getAttendceListSuccess(Attendance attendance);
}
