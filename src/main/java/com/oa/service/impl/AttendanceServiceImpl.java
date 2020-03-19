package com.oa.service.impl;

import com.oa.bean.Attendance;
import com.oa.bean.AttendanceExample;
import com.oa.enumutil.Result;
import com.oa.mapper.AttendanceMapper;
import com.oa.service.AttendanceService;
import com.oa.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @return
     */
    public CommonResult<Integer> getCount() {
        AttendanceExample attendanceExample = new AttendanceExample();
        AttendanceExample.Criteria re = attendanceExample.createCriteria();
        re.andTakeStateNotEqualTo(Result.WDAKA.getCode());
        List<Attendance> attendanceList = attendanceMapper.selectByExample(attendanceExample);
        return new CommonResult<Integer>(Result.SUCCESS.getCode(),Result.SUCCESS.getMessage(),attendanceList.size());
    }
}
