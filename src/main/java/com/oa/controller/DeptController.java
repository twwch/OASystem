package com.oa.controller;

import com.oa.bean.ResultDept;
import com.oa.enumutil.Result;
import com.oa.service.impl.DeptServiceImpl;
import com.oa.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CHTW
 * @date 2020/3/19 18:04
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptServiceImpl deptService;

    /**
     * 获取部门信息
     * 已废除的功能
     * @author CHTW
     * @return
     */
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public CommonResult<List<ResultDept>> getAll(){
        return new CommonResult<List<ResultDept>>(Result.SUCCESS.getCode(),"部门信息",deptService.getAll());
    }
}
