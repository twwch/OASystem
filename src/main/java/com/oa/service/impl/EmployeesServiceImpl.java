package com.oa.service.impl;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa.bean.EmpLost;
import com.oa.bean.Employees;
import com.oa.bean.EmployeesExample;
import com.oa.enumutil.EmpEnum;
import com.oa.mapper.EmployeesMapper;
import com.oa.service.EmployeesService;
import com.oa.utils.ResultEmp;
import com.oa.utils.TimeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private EmpLostServiceImpl empLostService;

    /**
     * 获取员工数量
     *
     * @return
     * @author CHTW
     */
    public List<Employees> getCount() {
        List<Employees> employeesList = employeesMapper.selectByExample(new EmployeesExample());
        return employeesList;
    }

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
    public int addemp(ResultEmp resultEmp) {
        //System.out.println(resultEmp);
        Employees employees = new Employees();
        BeanUtils.copyProperties(resultEmp, employees);
        String inTime = resultEmp.getInWithTime();

        employees.setInTime(TimeUtils.getDate(inTime));
        // UUID uuid = UUID.randomUUID();
        employees.seteAccount(IdUtil.simpleUUID());

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
     * 移出管理员但不删除
     * @author CHTW
     * @param id
     * @param gradeId
     * @return
     */
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
    public int adminLogin(String eId, String ePassword) {
        EmployeesExample example = new EmployeesExample();
        EmployeesExample.Criteria ctr = example.createCriteria();
        ctr.andEIdEqualTo(eId);
        ctr.andEPasswordEqualTo(ePassword);
        List<Employees> employeesList = employeesMapper.selectByExample(example);
        return employeesList.size();
    }

}
