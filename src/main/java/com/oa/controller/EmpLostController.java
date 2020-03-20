package com.oa.controller;

import com.github.pagehelper.PageInfo;
import com.oa.bean.EmpLost;
import com.oa.service.impl.EmpLostServiceImpl;
import com.oa.utils.ResultEmpLost;
import com.oa.utils.TimeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author CHTW
 * @date 2020/3/20 23:07
 */
@RestController
@RequestMapping(value = "/emplost")
public class EmpLostController {
    @Autowired
    private EmpLostServiceImpl empLostService;

    @RequestMapping(value = "/lost", method = RequestMethod.GET)
    public Map<String, Object> easyuiGetData(@RequestParam(defaultValue = "1", required = false) int page, Integer rows, String eId, String name) {
        PageInfo<EmpLost> info = empLostService.easyuiGetData(page, rows, eId, name);
        Map<String, Object> maps = new HashMap<String, Object>();
        List<ResultEmpLost> resultEmps = new ArrayList<ResultEmpLost>();
//        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");//格式化时间
        for (EmpLost empLost : info.getList()) {
            ResultEmpLost resultEmpLost = new ResultEmpLost();
            BeanUtils.copyProperties(empLost, resultEmpLost);
            Date date = empLost.getLostTime();
            if (date != null) {
                resultEmpLost.setLostOutTime(TimeUtils.getInTime(date));
            }
            //System.out.println(resultEmp);
            resultEmps.add(resultEmpLost);
        }
        if (info != null) {
            maps.put("total", info.getTotal());
            maps.put("rows", resultEmps);
            return maps;
        }
        return null;
    }

}
