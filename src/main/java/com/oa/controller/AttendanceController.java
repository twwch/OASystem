package com.oa.controller;

import com.github.pagehelper.PageInfo;
import com.oa.bean.Attendance;
import com.oa.enumutil.AttendanceEnum;
import com.oa.enumutil.Result;
import com.oa.service.EmployeesService;
import com.oa.service.impl.AttendanceServiceImpl;
import com.oa.utils.CommonResult;
import com.oa.utils.ResultAtta;
import com.oa.utils.TimeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CHTW
 * @date 2020/3/22 12:26
 */
@RestController
@RequestMapping("/Attendance")
public class AttendanceController {

    @Autowired
    private AttendanceServiceImpl attendanceService;

    /**
     * 获取打卡成功的列表
     * @param page
     * @param rows
     * @param eId
     * @param name
     * @return
     */
    @RequestMapping(value = "/easyuiGetData", method = RequestMethod.GET)
    public Map<String, Object> easyuiGetData(@RequestParam(defaultValue = "1", required = false) int page, Integer rows, String eId, String name) {
        PageInfo<Attendance> info = attendanceService.getAttendanceList(page, rows, eId, name, AttendanceEnum.DAKASUCCESS.getCode());
        Map<String, Object> maps = new HashMap<String, Object>();
        List<ResultAtta> lists = new ArrayList<ResultAtta>();
        for (Attendance attendance : info.getList()) {
            ResultAtta resultAtta = new ResultAtta();
            BeanUtils.copyProperties(attendance,resultAtta);
            resultAtta.setDakaTime(TimeUtils.getInTime1(attendance.getTakeTime()));
            lists.add(resultAtta);
        }
        if (info != null) {
            maps.put("total", info.getTotal());
            maps.put("rows", lists);
            return maps;
        }
        return null;
    }

    /**
     * 获取迟到或早退列表
     * @param page
     * @param rows
     * @param eId
     * @param name
     * @return
     */
    @RequestMapping(value = "/easyuiGetDataNot", method = RequestMethod.GET)
    public Map<String,Object> easyuiGetDataNot(@RequestParam(defaultValue = "1", required = false) int page, Integer rows, String eId, String name) {
        PageInfo<Attendance> info = attendanceService.getAttendanceList(page, rows, eId, name,AttendanceEnum.CHIDAO.getCode());
        Map<String, Object> maps = new HashMap<String, Object>();
        List<ResultAtta> lists = new ArrayList<ResultAtta>();
        for (Attendance attendance : info.getList()) {
            ResultAtta resultAtta = new ResultAtta();
            BeanUtils.copyProperties(attendance,resultAtta);
            resultAtta.setDakaTime(TimeUtils.getInTime1(attendance.getTakeTime()));
            lists.add(resultAtta);
        }
        if (info != null) {
            maps.put("total", info.getTotal());
            maps.put("rows", lists);
            return maps;
        }
        return null;
    }

    /**
     * @Description: attendanceList  处理员工获取自己打卡考勤的信息
     * @param: [session]
     * @return: com.oa.utils.CommonResult
     * @auther: zqq
     * @date: 20/3/23 14:19
     */
    @RequestMapping(value = "/myattendance", method = RequestMethod.GET)
    public CommonResult attendanceList(HttpSession session){
        Attendance attendance = new Attendance();
        attendance.seteId((String) session.getAttribute(EmployeesService.SESSION_EID));
        List<Attendance> attendceListSuccess = attendanceService.getAttendceListSuccess(attendance);
        return new CommonResult(Result.SUCCESS.getCode(),"获取成功",attendceListSuccess);
    }
}
