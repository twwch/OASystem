package com.oa.service;

import com.github.pagehelper.PageInfo;
import com.oa.bean.Employees;
import com.oa.bean.Salarys;
import com.oa.utils.ResultEmp;

import java.util.List;

/**
 * @author CHTW
 * @date 2020/3/19 9:18
 */
public interface EmployeesService {

    /**
     * session存储员工号
     */
    String SESSION_EID = "";

    List<Employees> getCount();

    PageInfo<Employees> easyuiGetData(int nowpage, int size, String eId, String name, String dept);

    int dele(String eId);

    int addemp(ResultEmp resultEmp);

    ResultEmp getByeId(String eId);

    /**
     * 普通员工登录，判断账号密码是否正确
     * @param employees
     * @return
     */
    int login(Employees employees);

    /**
     * 通过session的工号获取员工的信息
     * @param employees
     * @return
     */
    Employees getEmpById(Employees employees);

    /**
     * 考勤打卡入库
     * @param attendance
     * @return
     */
    int attendance(Attendance attendance);

    int updateEmp(ResultEmp resultEmp);

    int admin(Integer id,Integer gradeId);

    PageInfo<Employees> easyuiGetDataAdmin(int nowpage, int size, String eId, String name, String dept);

    int removeAdmin(Integer id,Integer gradeId);

    int adminLogin(String eId,String ePassword);


    /**
     * 通过员工的工号获取工资列表信息
     * @param salarys
     * @return
     */
    List<Salarys> salaryList(Salarys salarys);
}
