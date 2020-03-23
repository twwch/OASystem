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

    /**
     * 获取某个月某员工打卡成功的列表
     * @param eId
     * @author CHTW
     * @return
     */
    List<Attendance> getSuDay(String eId);
    /**
     * 获取某个月某员工迟到或早退的列表
     * @param eId
     * @author CHTW
     * @return
     */
    List<Attendance> getFialDay(String eId);

    /**
     * 本月打卡成功
     * @author CHTW
     * @return
     */
    List<Attendance> getSuDAKAMonth();

    /**
     * 本月打卡迟到
     * @author CHTW
     * @return
     */
    List<Attendance> getCIDAODAKAMonth();
    /**
     * 本月打卡早退
     * @author CHTW
     * @return
     */
    List<Attendance> getZAODAKAMonth();
}
