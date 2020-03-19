package com.oa.controller;

import com.oa.service.impl.EmployeesServiceImpl;
import com.oa.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        return employeesService.getCount();
    }
}
