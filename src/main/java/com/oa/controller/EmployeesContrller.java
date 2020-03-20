package com.oa.controller;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.github.pagehelper.PageInfo;
import com.oa.bean.Attendance;
import com.oa.bean.Employees;
import com.oa.bean.PubIp;
import com.oa.enumutil.Result;
import com.oa.mapper.EmployeesMapper;
import com.oa.service.EmployeesService;
import com.oa.service.IpService;
import com.oa.service.impl.EmployeesServiceImpl;
import com.oa.service.impl.IpServiceImpl;
import com.oa.utils.CommonResult;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private IpServiceImpl ipService;

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


    /**
     * @Description: login  员工登录
     * @param: [eId, password]
     * @return: com.oa.utils.CommonResult
     * @auther: zqq
     * @date: 20/3/20 20:57
     */
    @RequestMapping(value = "/login")
    public CommonResult login(@RequestParam(required = true) String eId,
                              @RequestParam(required = true) String password, HttpSession session){
        Employees employees =new Employees();
        employees.seteId(eId);
        employees.setePassword(SecureUtil.md5(password));
        int code = employeesService.login(employees);
        if (code == -1){
            return new CommonResult(404,"账号不存在");
        }else if (code == 1){
            return new CommonResult(404,"密码错误");
        }
        session.setAttribute(EmployeesService.SESSION_EID, eId);
        return new CommonResult(500,"登录成功");
    }

    @RequestMapping(value = "/sign")
    public CommonResult sign(@RequestParam(required = true) String ip, String now, HttpSession session) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(now);
        Employees emp = new Employees();

        PubIp pubIp = new PubIp();
        pubIp.setIp(ip);
        if (ipService.judgeIp(pubIp) == 1){
            emp.seteId((String) session.getAttribute(EmployeesService.SESSION_EID));
            Employees empById = employeesService.getEmpById(emp);
            Attendance attendance = new Attendance();
            attendance.seteId(empById.geteId());
            attendance.setName(empById.getName());
            attendance.setTakeTime(date);
            int code = employeesService.attendance(attendance);
            if (code != 1){
                return new CommonResult(404,"打卡失败请重试");
            }
            return new CommonResult(500,"打卡成功");
        }

        return new CommonResult(404,"请连接公司网络打卡");
    }
}
