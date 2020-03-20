package com.oa.service;

import com.github.pagehelper.PageInfo;
import com.oa.bean.Employees;
import com.oa.utils.CommonResult;

import java.util.List;

/**
 * @author CHTW
 * @date 2020/3/19 9:18
 */
public interface EmployeesService {

    public List<Employees> getCount();

    public PageInfo<Employees> getPageAll(int page, int size);
}
