package com.oa.service;

import com.github.pagehelper.PageInfo;
import com.oa.bean.Attendance;
import com.oa.bean.Employees;

import java.util.List;

/**
 * @author CHTW
 * @date 2020/3/19 9:18
 */
public interface EmployeesService {

    public String SESSION_EID = "";

    public List<Employees> getCount();

    public PageInfo<Employees> easyuiGetData(int nowpage, int size, String eId, String name, String dept);

    public int login(Employees employees);

    public Employees getEmpById(Employees employees);

    public int attendance(Attendance attendance);
}
