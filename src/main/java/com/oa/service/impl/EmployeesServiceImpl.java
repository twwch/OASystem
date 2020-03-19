package com.oa.service.impl;

import com.oa.bean.Employees;
import com.oa.bean.EmployeesExample;
import com.oa.enumutil.Result;
import com.oa.mapper.EmployeesMapper;
import com.oa.service.EmployeesService;
import com.oa.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CHTW
 * @date 2020/3/19 9:19
 */
@Service
public class EmployeesServiceImpl implements EmployeesService {

    @Autowired
    private EmployeesMapper employeesMapper;
    /**
     * 获取员工数量
     * @author CHTW
     * @return
     */
    public CommonResult<Integer> getCount() {
        List<Employees> employeesList = employeesMapper.selectByExample(new EmployeesExample());
        return new CommonResult<Integer>(Result.SUCCESS.getCode(),Result.SUCCESS.getMessage(),employeesList.size());
    }
}
