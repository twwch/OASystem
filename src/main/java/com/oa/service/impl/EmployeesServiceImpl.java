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
     *
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
     *
     * @param resultEmp
     * @return
     */
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
     * @param eId
     * @param gradeId
     * @return
     */
    public int admin(String eId,Integer gradeId ) {
        EmployeesExample employeesExample = new EmployeesExample();
        EmployeesExample.Criteria criteria = employeesExample.createCriteria();
        criteria.andEIdEqualTo(eId);
        criteria.andGradeIdEqualTo(gradeId);
        if(employeesMapper.selectByExample(employeesExample).size() > 0){
            return 0;
        }
        EmployeesExample employeesExample2 = new EmployeesExample();
        EmployeesExample.Criteria criteria2 = employeesExample.createCriteria();
        criteria.andEIdEqualTo(eId);
        Employees employees = new Employees();
        employees.setGradeId(gradeId);
        return employeesMapper.updateByExampleSelective(employees,employeesExample2);
    }

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


    public int removeAdmin(String eId, Integer gradeId) {
        EmployeesExample employeesExample = new EmployeesExample();
        EmployeesExample.Criteria criteria = employeesExample.createCriteria();
        criteria.andEIdEqualTo(eId);
        EmployeesExample employeesExample2 = new EmployeesExample();
        EmployeesExample.Criteria criteria2 = employeesExample.createCriteria();
        Employees employees = new Employees();
        employees.setGradeId(gradeId);
        return employeesMapper.updateByExampleSelective(employees,employeesExample2);
    }
}
