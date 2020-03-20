package com.oa.controller;

import com.oa.bean.Attendance;
import com.oa.enumutil.Result;
import com.oa.service.impl.AttendanceServiceImpl;
import com.oa.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public CommonResult<List<Attendance>> getCount(){
        List<Attendance> attendanceList = attendanceService.getCount();
        return new CommonResult<List<Attendance>>(Result.SUCCESS.getCode(),Result.SUCCESS.getMessage(),attendanceList);
    }

    /**
     * 获取已打卡人数
     * * @author CHTW
     * @return
     */
    @RequestMapping(value = "/getCountW",method = RequestMethod.GET)
    public CommonResult<Integer> getCountW(){
        List<Attendance> attendanceList = attendanceService.getCountW();
        return new CommonResult<Integer>(Result.SUCCESS.getCode(),Result.SUCCESS.getMessage(),attendanceList.size());
    }

    /**
     * 获取已打卡人数
     * @author CHTW
     * @return
     */
    @RequestMapping(value = "/getCountWX",method = RequestMethod.GET)
    public CommonResult<Integer> getCountWX(){
        List<Attendance> attendanceList = attendanceService.getCountWX();
        return new CommonResult<Integer>(Result.SUCCESS.getCode(),Result.SUCCESS.getMessage(),attendanceList.size());
    }
}
