package com.oa.controller;

import com.oa.service.impl.AttendanceServiceImpl;
import com.oa.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CHTW
 * @date 2020/3/19 10:44
 */
@RestController
@RequestMapping("/attend")
public class AttendanceController {
    @Autowired
    private AttendanceServiceImpl attendanceService;

    /**
     * @author CHTW
     * 获取已打卡人数
     * @return
     */
    @RequestMapping(value = "/getCount",method = RequestMethod.GET)
    public CommonResult<Integer> getCount(){
        return attendanceService.getCount();
    }
}
