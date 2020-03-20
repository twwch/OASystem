package com.oa.controller;

import com.oa.bean.Employees;
import com.oa.enumutil.Result;
import com.oa.service.impl.EmployeesServiceImpl;
import com.oa.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CHTW
 * @date 2020/3/19 9:27
 */
@RestController
@RequestMapping("/empinfo")
public class EmployeesContrller {
    @Autowired
    private EmployeesServiceImpl employeesService;

    /**
     * 获取员工总数
     * @author CHTW
     * @return
     */
    @RequestMapping(value = "/getCount",method = RequestMethod.GET)
    public CommonResult<Integer> getCount(){
        List<Employees> employeesList = employeesService.getCount();
        return new CommonResult<Integer>(Result.SUCCESS.getCode(),Result.SUCCESS.getMessage(),employeesList.size());
    }

    /**
     * 获取员工总数
     * @author CHTW
     * @return
     */
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public CommonResult<List<Employees>> getAll(){
        List<Employees> employeesList = employeesService.getCount();
        return new CommonResult<List<Employees>>(Result.SUCCESS.getCode(),Result.SUCCESS.getMessage(),employeesList);
    }

    @RequestMapping(value = "/databases",method = RequestMethod.GET)
    public CommonResult<List<Employees>> databases(int page){
        List<Employees> employeesList = employeesService.getPageAll(page,10).getList();
        return new CommonResult<List<Employees>>(Result.SUCCESS.getCode(),Result.SUCCESS.getMessage(),employeesList);
    }
}
