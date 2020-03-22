package com.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa.bean.Attendance;
import com.oa.bean.AttendanceExample;
import com.oa.enumutil.AttendanceEnum;
import com.oa.mapper.AttendanceMapper;
import com.oa.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author CHTW
 * @date 2020/3/22 11:33
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceMapper attendanceMapper;

    /**
     * 获取打卡情况列表（含分页）
     * @param nowpage 当前页面
     * @param size 页面大小
     * @param eId 员工id
     * @param name 员工姓名
     * @param code 通过code判断是查询打卡成功还是迟到或早退
     * @return
     */
    @Override
    public PageInfo<Attendance> getAttendanceList(int nowpage, int size, String eId, String name,int code) {
        PageHelper.startPage(nowpage, size);
        AttendanceExample example = new AttendanceExample();
        AttendanceExample.Criteria ctr = example.createCriteria();
        if (!StringUtils.isEmpty(eId)) {
            ctr.andEIdEqualTo(eId);
        }
        if (!StringUtils.isEmpty(name)) {
            ctr.andNameEqualTo(name);
        }
        if(code == AttendanceEnum.DAKASUCCESS.getCode()) {
            ctr.andTakeStateEqualTo(AttendanceEnum.DAKASUCCESS.getCode());
        }else{
            ctr.andTakeStateNotEqualTo(AttendanceEnum.DAKASUCCESS.getCode());
        }
        List<Attendance> lists = attendanceMapper.selectByExample(example);
        PageInfo<Attendance> info = new PageInfo<Attendance>(lists);
        return info;
    }


}
