package com.oa.controller;

import com.github.pagehelper.PageInfo;
import com.oa.bean.Employees;
import com.oa.enumutil.Result;
import com.oa.service.impl.EmployeesServiceImpl;
import com.oa.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     *
     * @return
     * @author CHTW
     */
    @RequestMapping(value = "/getCount", method = RequestMethod.GET)
    public CommonResult<Integer> getCount() {
        List<Employees> employeesList = employeesService.getCount();
        return new CommonResult<Integer>(Result.SUCCESS.getCode(), Result.SUCCESS.getMessage(), employeesList.size());
    }

    /**
     * 获取员工总数
     *
     * @return
     * @author CHTW
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public CommonResult<List<Employees>> getAll() {
        List<Employees> employeesList = employeesService.getCount();
        return new CommonResult<List<Employees>>(Result.SUCCESS.getCode(), Result.SUCCESS.getMessage(), employeesList);
    }

    /**
     * 获取用户列表
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/easyuiGetData", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> easyuiGetData(@RequestParam(defaultValue = "1", required = false) int page, Integer rows, String eId, String name, String dept) {
        PageInfo<Employees> info = employeesService.easyuiGetData(page, rows, eId, name, dept);
        Map<String, Object> maps = new HashMap<String, Object>();
        if (info != null) {
            maps.put("total", info.getTotal());
            maps.put("rows", info.getList());
            return maps;
        }
        return null;
    }

}
