package com.oa.service.impl;

import com.oa.bean.Attendance;
import com.oa.bean.AttendanceExample;
import com.oa.enumutil.AttendanceEnum;
import com.oa.enumutil.Result;
import com.oa.mapper.AttendanceMapper;
import com.oa.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author CHTW
 * @date 2020/3/19 10:30
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    public AttendanceMapper attendanceMapper;

    /**
     * 获取已打卡人数
     * @author CHTW
     * @return
     */
    public List<Attendance> getCount() {
        AttendanceExample attendanceExample = new AttendanceExample();
        AttendanceExample.Criteria re = attendanceExample.createCriteria();
        re.andTakeStateNotEqualTo(AttendanceEnum.WDAKA.getCode());
        List<Attendance> attendanceList = attendanceMapper.selectByExample(attendanceExample);
        return attendanceList;
    }

    /**
     * 获取未打卡人数
     * @author CHTW
     * @return
     */
    public List<Attendance> getCountW() {
        AttendanceExample attendanceExample = new AttendanceExample();
        AttendanceExample.Criteria re = attendanceExample.createCriteria();
        re.andTakeStateEqualTo(AttendanceEnum.WDAKA.getCode());
        List<Attendance> attendanceList = attendanceMapper.selectByExample(attendanceExample);
        return attendanceList;
    }

    public List<Attendance> getCountWX() {
        AttendanceExample attendanceExample = new AttendanceExample();
        AttendanceExample.Criteria re = attendanceExample.createCriteria();
        re.andTakeStateEqualTo(AttendanceEnum.DAKA2.getCode());
        List<Attendance> attendanceList = attendanceMapper.selectByExample(attendanceExample);
        return attendanceList;
    }

}
