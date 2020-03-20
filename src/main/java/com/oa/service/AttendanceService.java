package com.oa.service;

import com.oa.bean.Attendance;
import com.oa.utils.CommonResult;

import java.util.List;

/**
 * @author CHTW
 * @date 2020/3/19 10:30
 */
public interface AttendanceService {

    /**
     * 已打卡人数
     * @author CHTW
     * @return
     */
    public List<Attendance> getCount();
    /**
     * 未打卡
     * @author CHTW
     * @return
     */
    public List<Attendance> getCountW();

    /**
     * 获取无效打卡信息
     * @author CHTW
     * @return
     */
    public List<Attendance> getCountWX();

}
