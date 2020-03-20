package com.oa.service;

import com.github.pagehelper.PageInfo;
import com.oa.bean.Employees;
import com.oa.utils.ResultEmp;

import java.util.List;

/**
 * @author CHTW
 * @date 2020/3/19 9:18
 */
public interface EmployeesService {

    List<Employees> getCount();

    PageInfo<Employees> easyuiGetData(int nowpage, int size, String eId, String name, String dept);

    int dele(String eId);

    int addemp(ResultEmp resultEmp);

    ResultEmp getByeId(String eId);

    int updateEmp(ResultEmp resultEmp);

    int admin(String eId,Integer gradeId);

    PageInfo<Employees> easyuiGetDataAdmin(int nowpage, int size, String eId, String name, String dept);

    int removeAdmin(String eId,Integer gradeId);
}
