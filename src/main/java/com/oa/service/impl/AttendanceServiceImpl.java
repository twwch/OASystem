package com.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa.bean.Attendance;
import com.oa.bean.AttendanceExample;
import com.oa.enumutil.AttendanceEnum;
import com.oa.mapper.AttendanceMapper;
import com.oa.service.AttendanceService;
import com.oa.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
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

    /**
     * 获取某个月某员工打卡成功的列表
     * @param eId
     * @author CHTW
     * @return
     */
    @Override
    public List<Attendance> getSuDay(String eId) {
        AttendanceExample example = new AttendanceExample();
        AttendanceExample.Criteria re = example.createCriteria();
        re.andEIdEqualTo(eId);
        //前一月的第一天
        Date date1 = TimeUtils.getFirstDay();
        //前一月的最后一天
        Date date2 = TimeUtils.getLastDay();
        re.andTakeTimeBetween(date1,date2);
        //打卡成功
        re.andTakeStateEqualTo(AttendanceEnum.DAKASUCCESS.getCode());
        return attendanceMapper.selectByExample(example);
    }
    /**
     * 获取某个月某员工迟到或早退的列表
     * @param eId
     * @author CHTW
     * @return
     */
    @Override
    public List<Attendance> getFialDay(String eId) {
        AttendanceExample example = new AttendanceExample();
        AttendanceExample.Criteria re = example.createCriteria();
        re.andEIdEqualTo(eId);
        //前一月的第一天
        Date date1 = TimeUtils.getFirstDay();
        //前一月的最后一天
        Date date2 = TimeUtils.getLastDay();
        re.andTakeTimeBetween(date1,date2);
        //打卡成功
        re.andTakeStateNotEqualTo(AttendanceEnum.DAKASUCCESS.getCode());
        return attendanceMapper.selectByExample(example);
    }


    /**
     * 本月打卡成功
     * @author CHTW
     * @return
     */
    @Override
    public List<Attendance> getSuDAKAMonth(){
        AttendanceExample example = new AttendanceExample();
        AttendanceExample.Criteria re = example.createCriteria();
        //前一月的第一天
        Date date1 = TimeUtils.getBenFirstDay();
        //前一月的最后一天
        Date date2 = TimeUtils.getBenLastDay();
        re.andTakeStateEqualTo(AttendanceEnum.DAKASUCCESS.getCode());
        re.andTakeTimeBetween(date1,date2);
        return attendanceMapper.selectByExample(example);
    }

    /**
     * 本月打卡迟到
     * @author CHTW
     * @return
     */
    public List<Attendance> getCIDAODAKAMonth(){
        AttendanceExample example = new AttendanceExample();
        AttendanceExample.Criteria re = example.createCriteria();
        //前一月的第一天
        Date date1 = TimeUtils.getBenFirstDay();
        //前一月的最后一天
        Date date2 = TimeUtils.getBenLastDay();
        re.andTakeStateEqualTo(AttendanceEnum.CHIDAO.getCode());
        re.andTakeTimeBetween(date1,date2);
        return attendanceMapper.selectByExample(example);
    }

    /**
     * 本月打卡早退
     * @author CHTW
     * @return
     */
    @Override
    public List<Attendance> getZAODAKAMonth(){
        AttendanceExample example = new AttendanceExample();
        AttendanceExample.Criteria re = example.createCriteria();
        //前一月的第一天
        Date date1 = TimeUtils.getBenFirstDay();
        //前一月的最后一天
        Date date2 = TimeUtils.getBenLastDay();
        re.andTakeStateEqualTo(AttendanceEnum.ZAOTUI.getCode());
        re.andTakeTimeBetween(date1,date2);
        return attendanceMapper.selectByExample(example);
    }
}
