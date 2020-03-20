package com.oa.controller;

import com.oa.bean.LeaveSales;
import com.oa.enumutil.Result;
import com.oa.service.LeaveSalesService;
import com.oa.service.impl.LeaveSalesServiceImpl;
import com.oa.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CHTW
 * @date 2020/3/19 11:23
 */

@RestController
@RequestMapping("/leaveinfo")
public class LeaveSalesController {

    @Autowired
    private LeaveSalesServiceImpl leaveSalesService;

    /**
     * 获取请假人数
     * @return
     */
    @RequestMapping(value = "/getCount",method = RequestMethod.GET)
    public CommonResult<Integer> getCount(){
        List<LeaveSales> leaveSalesList = leaveSalesService.getCount();
        return new CommonResult<Integer>(Result.SUCCESS.getCode(),Result.SUCCESS.getMessage(),leaveSalesList.size());
    }

    /**
     * 获取销假人数
     * @return
     */
    @RequestMapping(value = "/getCountYX",method = RequestMethod.GET)
    public CommonResult<Integer> getCountXIAO(){
        List<LeaveSales> leaveSalesList = leaveSalesService.getXiaoJia();
        return new CommonResult<Integer>(Result.SUCCESS.getCode(),Result.SUCCESS.getMessage(),leaveSalesList.size());
    }
}
