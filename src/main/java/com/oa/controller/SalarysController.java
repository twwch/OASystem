package com.oa.controller;

import com.github.pagehelper.PageInfo;
import com.oa.bean.Employees;
import com.oa.bean.Salarys;
import com.oa.enumutil.Result;
import com.oa.service.impl.EmployeesServiceImpl;
import com.oa.service.impl.SalarysServiceImpl;
import com.oa.utils.CommonResult;
import com.oa.utils.ResultSalarys;
import com.oa.utils.TimeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CHTW
 * @date 2020/3/23 10:34
 */
@RestController
@RequestMapping("/salarys")
public class SalarysController {

    @Autowired
    private SalarysServiceImpl salarysService;

    @Autowired
    private EmployeesServiceImpl employeesService;

    /**
     * 模拟发放工资
     *
     * @return
     * @author CHTW
     */
    @RequestMapping(value = "/sendMoney", method = RequestMethod.GET)
    public CommonResult<Integer> sendMoney() {
        int i = salarysService.sendMoney();
        if (i > 0) {
            return new CommonResult<Integer>(Result.SUCCESS.getCode(), "发放成功", i);
        }
        return new CommonResult<Integer>(Result.FAIL.getCode(), "该月工资已发放！");
    }

    @RequestMapping(value = "/easyuiGetData", method = RequestMethod.GET)
    public Map<String, Object> easyuiGetData(@RequestParam(defaultValue = "1", required = false) int page, Integer rows, String eId) {
        PageInfo<Salarys> info = salarysService.easyuiGetData(page, rows, eId);
        List<Salarys> list = info.getList();
        List<ResultSalarys> salarysList = new ArrayList<ResultSalarys>();
        for (Salarys salarys : list) {
            ResultSalarys resultSalarys = new ResultSalarys();
            BeanUtils.copyProperties(salarys,resultSalarys);
            resultSalarys.setIssueWithTime(TimeUtils.getInTime(salarys.getIssueTime()));
            Employees employees = employeesService.getEmpById(salarys.geteId());
            resultSalarys.setName(employees.getName());
            salarysList.add(resultSalarys);
        }
        Map<String, Object> maps = new HashMap<String, Object>();
        if(info != null){
            maps.put("total", info.getTotal());
            maps.put("rows", salarysList);
            return maps;
        }
        return null;
    }
}