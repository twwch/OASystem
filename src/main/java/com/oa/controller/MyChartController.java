package com.oa.controller;

import com.oa.enumutil.Result;
import com.oa.service.impl.MyChartServiceImpl;
import com.oa.utils.CommonResult;
import com.oa.utils.MyChartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CHTW
 * @date 2020/3/23 14:33
 */
@RestController
@RequestMapping("/myChart")
public class MyChartController {
    @Autowired
    private MyChartServiceImpl myChartService;
    /**
     * 获取饼形图json数据
     * @author CHTW
     * @return
     */
    @RequestMapping(value = "/data",method = RequestMethod.GET)
    public CommonResult<List<MyChartData>> data(){
        return new CommonResult<List<MyChartData>>(Result.SUCCESS.getCode(),"json数据获取成功",myChartService.data());
    }
}
