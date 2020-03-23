package com.oa.service.impl;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa.bean.Attendance;
import com.oa.bean.Employees;
import com.oa.bean.Salarys;
import com.oa.bean.SalarysExample;
import com.oa.mapper.SalarysMapper;
import com.oa.service.SalarysService;
import com.oa.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author CHTW
 * @date 2020/3/23 10:39
 */
@Service
public class SalarysServiceImpl implements SalarysService {

    @Autowired
    private SalarysMapper salarysMapper;

    @Autowired
    private EmployeesServiceImpl employeesService;

    @Autowired
    private AttendanceServiceImpl attendanceService;

    @Autowired
    private SendMoneyServiceImpl sendMoneyService;

    /**
     * 模拟发放工资
     *
     * @return
     * @author CHTW
     */
    @Override
    public int sendMoney() {
        Date date = new Date();
        String time = TimeUtils.sendMoneyTime(date);//yyyy年MM月dd日，表示发放的那一个月的年月日，操作时要出去日
        int insertCount = 0;
        if(!sendMoneyService.isSend(time.substring(0,8))) {
            sendMoneyService.add(time.substring(0,8));
            //获取所有列表
            List<Employees> employeesList = employeesService.getAll();
            for (Employees employees : employeesList) {
                Salarys salarys = new Salarys();
                //设置员工号
                salarys.seteId(employees.geteId());
                //设置基础工资
                salarys.setBasicWage(employees.getLowSalary().toString());
                //设置工作天数
                List<Attendance> suDay = attendanceService.getSuDay(employees.geteId());
                int workDays = suDay.size() / 2;
                salarys.setWorkDays(workDays);
                //设置迟到或早退天数
                List<Attendance> fialDays = attendanceService.getFialDay(employees.geteId());
                int failDay = fialDays.size() / 2;
                salarys.setLeaveEarlyDays(failDay);
                //设置流水号
                salarys.setSerialNum(IdUtil.simpleUUID());
                //设置提成(基础工资0.2工作天数*0.2)
                double royalty = employees.getLowSalary() * workDays * 0.2;
                salarys.setRoyalty(royalty);
                //设置到账薪水(提成+基础工资-迟到或早退的钱)
                salarys.setSalary(royalty + employees.getLowSalary() - failDay * 10);
                //需要把id设置为空，让mysql生成，不让上面的操作覆盖
                salarys.setId(null);
                //设置发放的那一个月的工资
                salarys.setMonth(time.substring(0, 8));
                //设置发放工资的时间
                salarys.setIssueTime(date);
                //设置公司账号：6795484756321456
                salarys.setAccountOther("6795484756321456");
                //salarysList.add(salarys);
                insertCount += salarysMapper.insert(salarys);
            }
        }
        return insertCount;
    }

    /**
     * 获取薪水列表
     * @param nowpage
     * @param size
     * @param eId
     * @author CHTW
     * @return
     */
    @Override
    public PageInfo<Salarys> easyuiGetData(int nowpage, int size, String eId) {
        PageHelper.startPage(nowpage,size);
        SalarysExample example = new SalarysExample();
        SalarysExample.Criteria rt = example.createCriteria();
        if(!StringUtils.isEmpty(eId)){
            rt.andEIdEqualTo(eId);
        }
        List<Salarys> salarysList = salarysMapper.selectByExample(example);
        PageInfo<Salarys> info = new PageInfo<Salarys>(salarysList);
        return info;
    }
}
