package com.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa.bean.Attendance;
import com.oa.bean.AttendanceExample;
import com.oa.bean.Employees;
import com.oa.bean.EmployeesExample;
import com.oa.mapper.AttendanceMapper;
import com.oa.mapper.EmployeesMapper;
import com.oa.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author CHTW
 * @date 2020/3/19 9:19
 */
@Service
public class EmployeesServiceImpl implements EmployeesService {



    @Autowired
    private EmployeesMapper employeesMapper;

    @Autowired
    private AttendanceMapper attendanceMapper;
    /**
     * 获取员工数量
     * @author CHTW
     * @return
     */
    public List<Employees> getCount() {
        List<Employees> employeesList = employeesMapper.selectByExample(new EmployeesExample());
        return employeesList;
    }

    public PageInfo<Employees> easyuiGetData(int nowpage, int size,String eId,String name,String dept) {
        PageHelper.startPage(nowpage, size);
        EmployeesExample example = new EmployeesExample();
        EmployeesExample.Criteria ctr = example.createCriteria();
        if(!StringUtils.isEmpty(eId)) {
            ctr.andEIdEqualTo(eId);
        }
        if(!StringUtils.isEmpty(name)) {
            ctr.andNameEqualTo(name);
        }
        if(!StringUtils.isEmpty(dept)) {
            ctr.andDeptEqualTo(dept);
        }
        List<Employees> lists = employeesMapper.selectByExample(example);
        PageInfo<Employees> info = new PageInfo<Employees>(lists);
        return info;
    }


    /**
     * @Description: login  登录
     * @param: [employees]
     * @return: int   -1表示账号不存在，0表示账号密码正确，-1表示密码错误
     * @auther: zqq
     * @date: 20/3/20 18:02
     */
    @Override
    public int login(Employees employees) {
        EmployeesExample employeesExample = new EmployeesExample();
        EmployeesExample.Criteria criteria = employeesExample.createCriteria();
        criteria.andEIdEqualTo(employees.geteId());
        List<Employees> employeesList = employeesMapper.selectByExample(employeesExample);
        if (employeesList.size() == 1){
            return employees.geteId().equals(employeesList.get(0).geteId()) ? 0 : 1;
        }
        return -1;
    }

    /**
     * @Description: getEmpById  通过账户的eid（工号）获取账户信息
     * @param: [employees]
     * @return: java.lang.String
     * @auther: zqq
     * @date: 20/3/20 21:16
     */
    @Override
    public Employees getEmpById(Employees employees) {
        EmployeesExample employeesExample = new EmployeesExample();
        EmployeesExample.Criteria criteria = employeesExample.createCriteria();
        criteria.andEIdEqualTo(employees.geteId());
        List<Employees> employeesList = employeesMapper.selectByExample(employeesExample);
        return employeesList.get(0);
    }

    /**
     * @Description: attendance 插入签到
     * @param: [attendance]
     * @return: int  返回插入数量
     * @auther: zqq
     * @date: 20/3/20 21:52
     */
    @Override
    public int attendance(Attendance attendance) {
        AttendanceExample attendanceExample = new AttendanceExample();
        int insert = attendanceMapper.insert(attendance);

        return 0;
    }
}
