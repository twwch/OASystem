package com.oa.service.impl;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa.bean.*;
import com.oa.mapper.*;
import com.oa.enumutil.EmpEnum;
import com.oa.mapper.AttendanceMapper;
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

    @Autowired
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
     *@author CHTW
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
     *@author CHTW
     * @param resultEmp
     * @return
     */
    @Override
    public int addemp(ResultEmp resultEmp) {
        //System.out.println(resultEmp);
        Employees employees = new Employees();
        BeanUtils.copyProperties(resultEmp, employees);
        String inTime = resultEmp.getInWithTime();

        employees.setInTime(TimeUtils.getDate(inTime));
        // UUID uuid = UUID.randomUUID();
        employees.seteAccount(IdUtil.simpleUUID());//随机账号
        employees.setContractNum(IdUtil.simpleUUID());//合同编号
        //System.out.println(employees);
        int i = employeesMapper.insertSelective(employees);
        return i;
    }

    /**
     * 通过工号查询
     *@author CHTW
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
     *@author CHTW
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
        EmployeesExample employeesExample = new EmployeesExample();
        EmployeesExample.Criteria ctr = employeesExample.createCriteria();
        ctr.andEIdEqualTo(employees.geteId());
        return employeesMapper.updateByExampleSelective(employees,employeesExample);
        //return employeesMapper.updateByPrimaryKeySelective(employees);
    }

    /**
     * 设置管理员
     *@author CHTW
     * @param gradeId
     * @return
     */
    @Override
    public int admin(Integer id, Integer gradeId) {
        EmployeesExample employeesExample = new EmployeesExample();
        EmployeesExample.Criteria criteria = employeesExample.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andGradeIdEqualTo(gradeId);
        if (employeesMapper.selectByExample(employeesExample).size() > 0) {
            Employees employees = new Employees();
            employees.setGradeId(EmpEnum.ISADMIN.getCode());
            employees.setId(id);
            return employeesMapper.updateByPrimaryKeySelective(employees);
        }
        return 0;
    }

    /**
     * 获取管理员列表
     * @author CHTW
     * @param nowpage
     * @param size
     * @param eId
     * @param name
     * @param dept
     * @return
     */
    @Override
    public PageInfo<Employees> easyuiGetDataAdmin(int nowpage, int size, String eId, String name, String dept) {
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
    /**
     * 移出管理员但不删除
     * @author CHTW
     * @param id
     * @param gradeId
     * @return
     */
    @Override
    public int removeAdmin(Integer id, Integer gradeId) {
        Employees employees = new Employees();
        employees.setId(id);
        employees.setGradeId(gradeId);
        return employeesMapper.updateByPrimaryKeySelective(employees);
    }

    /**
     * 管理员登录接口
     * @author CHTW
     * @param eId
     * @param ePassword
     * @return
     */
    @Override
    public int adminLogin(String eId, String ePassword) {
        EmployeesExample example = new EmployeesExample();
        EmployeesExample.Criteria ctr = example.createCriteria();
        ctr.andEIdEqualTo(eId);
        ctr.andEPasswordEqualTo(ePassword);
        List<Employees> employeesList = employeesMapper.selectByExample(example);
        return employeesList.size();
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

    /**
     * @Description: getWagesByEidAndId 获取某用户的某个月工资
     * @param: [salarys] 工资信息
     * @return: com.oa.bean.Salarys   一个工资类
     * @auther: zqq
     * @date: 20/3/22 18:36
     */
    @Override
    public Salarys getWagesByEidAndId(Salarys salarys) {
        SalarysExample salarysExample = new SalarysExample();
        SalarysExample.Criteria criteria = salarysExample.createCriteria();
        criteria.andEIdEqualTo(salarys.geteId());
        criteria.andIdEqualTo(salarys.getId());
        List<Salarys> salarys1 = salarysMapper.selectByExample(salarysExample);
        return salarys1.get(0);
    }

    /**
     * @Description: getMessage 获取员工信息
     * @param: [employees]  只包含工号的Employee类
     * @return: com.oa.bean.Employees 返回一个包含员工信息的类
     * @auther: zqq
     * @date: 20/3/22 20:28
     */
    @Override
    public Employees getMessage(Employees employees) {
        EmployeesExample employeesExample = new EmployeesExample();
        EmployeesExample.Criteria criteria = employeesExample.createCriteria();
        criteria.andEIdEqualTo(employees.geteId());
        List<Employees> employeesList = employeesMapper.selectByExample(employeesExample);
        return employeesList.get(0);
    }



    /**
     * 获取所有员工
     * @author CHTW
     * @return
     */
    @Override
    public List<Employees> getAll() {
        return employeesMapper.selectByExample(new EmployeesExample());
    }

    @Override
    public Employees getEmpById(String eId) {
        EmployeesExample employeesExample = new EmployeesExample();
        EmployeesExample.Criteria criteria = employeesExample.createCriteria();
        criteria.andEIdEqualTo(eId);
        List<Employees> employeesList = employeesMapper.selectByExample(employeesExample);
        return employeesList.get(0);
    }
}
