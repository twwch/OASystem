package com.oa.service.impl;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa.bean.*;
import com.oa.mapper.AttendanceMapper;
import com.oa.enumutil.EmpEnum;
import com.oa.mapper.EmployeesMapper;
import com.oa.mapper.SalarysMapper;
import com.oa.service.EmployeesService;
import com.oa.utils.ResultEmp;
import com.oa.utils.TimeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
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

    @Autowired
    private EmpLostServiceImpl empLostService;

    private SalarysMapper salarysMapper;

    /**
     * 获取员工数量
     *
     * @return
     * @author CHTW
     */
    @Override
    public List<Employees> getCount() {
        List<Employees> employeesList = employeesMapper.selectByExample(new EmployeesExample());
        return employeesList;
    }

    @Override
    public PageInfo<Employees> easyuiGetData(int nowpage, int size, String eId, String name, String dept) {
        PageHelper.startPage(nowpage, size);
        EmployeesExample example = new EmployeesExample();
        EmployeesExample.Criteria ctr = example.createCriteria();
        if (!StringUtils.isEmpty(eId)) {
            ctr.andEIdEqualTo(eId);
        }
        if (!StringUtils.isEmpty(name)) {
            ctr.andNameEqualTo(name);
        }
        if (!StringUtils.isEmpty(dept)) {
            ctr.andDeptEqualTo(dept);
        }
        List<Employees> lists = employeesMapper.selectByExample(example);
        PageInfo<Employees> info = new PageInfo<Employees>(lists);
        return info;
    }

    /**
     * 删除员工
     *
     * @param eId
     * @return
     */
    @Override
    public int dele(String eId) {
        EmployeesExample example = new EmployeesExample();
        EmployeesExample.Criteria ctr = example.createCriteria();
        ctr.andEIdEqualTo(eId);
        List<Employees> employeesList = employeesMapper.selectByExample(example);
        EmpLost empLost = new EmpLost();
        BeanUtils.copyProperties(employeesList.get(0), empLost);
        empLost.setLostTime(new Date());
        int i = empLostService.add(empLost);
        return employeesMapper.deleteByExample(example);
    }

    /**
     * 添加员工
     *
     * @param resultEmp
     * @return
     */
    @Override
    public int addemp(ResultEmp resultEmp) {
        //System.out.println(resultEmp);
        Employees employees = new Employees();
        BeanUtils.copyProperties(resultEmp, employees);
        String inTime = resultEmp.getInWithTime();
        /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(inTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        employees.setInTime(TimeUtils.getDate(inTime));
        // UUID uuid = UUID.randomUUID();
        employees.seteAccount(IdUtil.simpleUUID());
        //System.out.println(employees);
        int i = employeesMapper.insert(employees);
        return i;
    }

    /**
     * 通过工号查询
     *
     * @param eId
     * @return
     */
    @Override
    public ResultEmp getByeId(String eId) {
        EmployeesExample example = new EmployeesExample();
        EmployeesExample.Criteria ctr = example.createCriteria();
        ctr.andEIdEqualTo(eId);
        List<Employees> employeesList = employeesMapper.selectByExample(example);
        Employees employees = employeesList.get(0);
        ResultEmp resultEmp = new ResultEmp();
        BeanUtils.copyProperties(employees, resultEmp);
        Date date = employees.getInTime();
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (date != null) {
            resultEmp.setInWithTime(TimeUtils.getInTime(date));
        }
        return resultEmp;
    }

    /**
     * 修改
     *
     * @param resultEmp
     * @return
     */
    @Override
    public int updateEmp(ResultEmp resultEmp) {
        Employees employees = new Employees();
        BeanUtils.copyProperties(resultEmp, employees);
        String inTime = resultEmp.getInWithTime();
        if (inTime != null && !"".equals(inTime)) {
            employees.setInTime(TimeUtils.getDate(inTime));
        }
        return employeesMapper.updateByPrimaryKey(employees);
    }

    /**
     * 设置管理员
     *
     * @param gradeId
     * @return
     */
    @Override
    public int admin(Integer id, Integer gradeId) {
        EmployeesExample employeesExample = new EmployeesExample();
        EmployeesExample.Criteria criteria = employeesExample.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andGradeIdEqualTo(gradeId);
        Employees employees1 = employeesMapper.selectByExample(employeesExample).get(0);
        System.out.println(employees1);
        if (employeesMapper.selectByExample(employeesExample).size() > 0) {
            //criteria = employeesExample.createCriteria();
            Employees employees = new Employees();
            employees.setGradeId(EmpEnum.ISADMIN.getCode());
            employees.setId(id);
            return employeesMapper.updateByPrimaryKeySelective(employees);
        }
        return 0;
    }

    @Override
    public PageInfo<Employees> easyuiGetDataAdmin(int nowpage, int size, String eId, String name, String dept) {
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
        ctr.andGradeIdEqualTo(EmpEnum.ISADMIN.getCode());
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
            return employees.getePassword().equals(employeesList.get(0).getePassword()) ? 0 : 1;
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
        return insert;
    }

    @Override
    public int removeAdmin(Integer id, Integer gradeId) {
        Employees employees = new Employees();
        employees.setId(id);
        employees.setGradeId(gradeId);
        return employeesMapper.updateByPrimaryKeySelective(employees);
    }

    /**
     * @Description: salaryList  通过工号获取该员工的工资信息列表
     * @param: [salarys]
     * @return: java.util.List<com.oa.bean.Salarys>   返回该员工的工资列表
     * @auther: zqq
     * @date: 20/3/21 20:55
     */
    @Override
    public List<Salarys> salaryList(Salarys salarys) {
        SalarysExample salarysExample = new SalarysExample();
        SalarysExample.Criteria criteria = salarysExample.createCriteria();
        criteria.andEIdEqualTo(salarys.geteId());
        List<Salarys> salarys1 = salarysMapper.selectByExample(salarysExample);
        for (Salarys s : salarys1) {
            s.setSerialNum(null);
            s.setBasicWage(null);
            s.setRoyalty(null);
            s.setWorkDays(null);
            s.setWorkDays(null);
            s.setLeaveEarlyDays(null);
            s.setIssueTime(null);
            s.setAccountOther(null);
        }
        return salarys1;
    }
}
