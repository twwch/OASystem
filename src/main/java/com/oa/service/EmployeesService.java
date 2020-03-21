package com.oa.service;

import com.github.pagehelper.PageInfo;
import com.oa.bean.Attendance;
import com.oa.bean.Employees;
import com.oa.utils.ResultEmp;

import java.util.List;

/**
 * @author CHTW
 * @date 2020/3/19 9:18
 */
public interface EmployeesService {

    public String SESSION_EID = "";

    List<Employees> getCount();

    PageInfo<Employees> easyuiGetData(int nowpage, int size, String eId, String name, String dept);

    int dele(String eId);

    int addemp(ResultEmp resultEmp);

    ResultEmp getByeId(String eId);



    public int login(Employees employees);

    public Employees getEmpById(Employees employees);

    public int attendance(Attendance attendance);

    int updateEmp(ResultEmp resultEmp);

    int admin(Integer id,Integer gradeId);

    PageInfo<Employees> easyuiGetDataAdmin(int nowpage, int size, String eId, String name, String dept);

    int removeAdmin(Integer id,Integer gradeId);
}
