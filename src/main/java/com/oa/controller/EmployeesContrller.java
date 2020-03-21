package com.oa.controller;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.github.pagehelper.PageInfo;
import com.oa.bean.Attendance;
import com.oa.bean.Employees;
import com.oa.bean.PubIp;
import com.oa.bean.Salarys;
import com.oa.enumutil.EmpEnum;
import com.oa.enumutil.Result;
import com.oa.mapper.EmployeesMapper;
import com.oa.service.EmployeesService;
import com.oa.service.IpService;
import com.oa.service.impl.EmployeesServiceImpl;
import com.oa.service.impl.IpServiceImpl;
import com.oa.utils.CommonResult;
import jdk.nashorn.internal.ir.ReturnNode;
import com.oa.utils.ResultEmp;
import com.oa.utils.TimeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

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
    public Map<String, Object> easyuiGetData(@RequestParam(defaultValue = "1", required = false) int page, Integer rows, String eId, String name, String dept) {
        PageInfo<Employees> info = employeesService.easyuiGetData(page, rows, eId, name, dept);
        Map<String, Object> maps = new HashMap<String, Object>();
        List<ResultEmp> resultEmps = new ArrayList<ResultEmp>();
//        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");//格式化时间
        for (Employees employees : info.getList()) {
            ResultEmp resultEmp = new ResultEmp();
            BeanUtils.copyProperties(employees, resultEmp);
            Date date = employees.getInTime();
            if (date != null) {
                resultEmp.setInWithTime(TimeUtils.getInTime(date));
            }
            //System.out.println(resultEmp);
            resultEmps.add(resultEmp);
        }
        if (info != null) {
            maps.put("total", info.getTotal());
            maps.put("rows", resultEmps);
            return maps;
        }
        return null;
    }

    /**
     * 删除员工
     *
     * @param eId
     * @return
     */
    @RequestMapping(value = "/dele", method = RequestMethod.GET)
    public CommonResult<Integer> dele(String eId) {
        // System.out.println("eId="+eId);
        int res = employeesService.dele(eId);
        if (res > 0) {
            return new CommonResult<Integer>(Result.SUCCESS.getCode(), Result.SUCCESS.getMessage(), res);
        }
        return new CommonResult<Integer>(Result.FAIL.getCode(), Result.FAIL.getMessage(), 0);
    }

    /**
     * 添加员工
     *
     * @param resultEmp
     * @return
     */
    @RequestMapping(value = "/addemp", method = RequestMethod.GET)
    public CommonResult<Integer> addemp(ResultEmp resultEmp) {
        int res = employeesService.addemp(resultEmp);
        if (res > 0) {
            return new CommonResult<Integer>(Result.SUCCESS.getCode(), Result.SUCCESS.getMessage(), res);
        }
        return new CommonResult<Integer>(Result.FAIL.getCode(), Result.FAIL.getMessage(), 0);
    }

    @RequestMapping(value = "/getByeId", method = RequestMethod.GET)
    public CommonResult<ResultEmp> addemp(String eId) {
        ResultEmp resultEmp = employeesService.getByeId(eId);
        if (resultEmp != null) {
            return new CommonResult<ResultEmp>(Result.SUCCESS.getCode(), "查找成功", resultEmp);
        }
        return new CommonResult<ResultEmp>(Result.FAIL.getCode(), Result.FAIL.getMessage(), new ResultEmp());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult<Integer> updateEmp(ResultEmp resultEmp) {
        //System.out.println(resultEmp);
        int i = employeesService.updateEmp(resultEmp);
        return new CommonResult<Integer>(Result.SUCCESS.getCode(), "修改成功", i);

    }

    /**
     * 设置为管理员
     * @param
     * @return
     */
    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public CommonResult<Integer> admin(Integer id,Integer gradeId){
        int i = employeesService.admin(id,gradeId);
        if (i > 0) {
            return new CommonResult<Integer>(Result.SUCCESS.getCode(), "修改成功", i);
        }
        return new CommonResult<Integer>(Result.FAIL.getCode(), "修改失败", 0);
    }

    @RequestMapping(value = "/easyuiGetDataAdmin", method = RequestMethod.GET)
    public Map<String, Object> easyuiGetDataAdmin(@RequestParam(defaultValue = "1", required = false) int page, Integer rows, String eId, String name, String dept) {
        PageInfo<Employees> info = employeesService.easyuiGetDataAdmin(page, rows, eId, name, dept);
        Map<String, Object> maps = new HashMap<String, Object>();
        List<ResultEmp> resultEmps = new ArrayList<ResultEmp>();
//        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");//格式化时间
        for (Employees employees : info.getList()) {
            ResultEmp resultEmp = new ResultEmp();
            BeanUtils.copyProperties(employees, resultEmp);
            Date date = employees.getInTime();
            if (date != null) {
                resultEmp.setInWithTime(TimeUtils.getInTime(date));
            }
            //System.out.println(resultEmp);
            resultEmps.add(resultEmp);
        }
        if (info != null) {
            maps.put("total", info.getTotal());
            maps.put("rows", resultEmps);
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

    /**
     * @Description: sign  根据ip  当前时间  已经session的工号进行打卡
     * @param: [ip, now, session]
     * @return: com.oa.utils.CommonResult
     * @auther: zqq
     * @date: 20/3/21 13:05
     */
    @RequestMapping(value = "/sign")
    public CommonResult sign(@RequestParam(required = true) String ip, String now, HttpSession session) throws ParseException {
        Employees emp = new Employees();
        PubIp pubIp = new PubIp();
        pubIp.setIp(ip);
        if (ipService.judgeIp(pubIp) == 1){
            emp.seteId((String) session.getAttribute(EmployeesService.SESSION_EID));
            Employees empById = employeesService.getEmpById(emp);
            Date date = TimeUtils.getDate(now);
            Attendance attendance = new Attendance();
            attendance.seteId(empById.geteId());
            attendance.setName(empById.getName());
            attendance.setTakeTime(date);
            // 判断时间小于12点就是上班打卡
            if ((Integer.parseInt(now.split(" ")[1].split(":")[0]) < 12) && (!TimeUtils.judgeUpTime(now.split(" ")[1]))){
                // 判断是否迟到 1表示迟到
                attendance.setTakeState(1);
            }else if((Integer.parseInt(now.split(" ")[1].split(":")[0]) < 23) && (!TimeUtils.judgeDownTime(now.split(
                    " ")[1]))) {
                // 判断是否早退 2表示早退
                attendance.setTakeState(2);
            }else {
                // 0表示正常上班
                attendance.setTakeState(0);
            }
            int code = employeesService.attendance(attendance);
            if (code != 1){
                return new CommonResult(404,"打卡失败请重试");
            }
            return new CommonResult(500,"打卡成功");
        }
        return new CommonResult(404,"请连接公司网络打卡");
    }


    @RequestMapping(value = "/removeAdmin", method = RequestMethod.GET)
    public CommonResult<Integer> removeAdmin(Integer id){
        int i = employeesService.removeAdmin(id, EmpEnum.NOTADMIN.getCode());
        if (i > 0) {
            return new CommonResult<Integer>(Result.SUCCESS.getCode(), "修改成功", i);
        }
        return new CommonResult<Integer>(Result.FAIL.getCode(), "修改失败", 0);
    }


    /**
     * @Description: salary  出来获取工资列表请求
     * @param: [session]
     * @return: com.oa.utils.CommonResult  装有工资的列表
     * @auther: zqq
     * @date: 20/3/21 21:07
     */
    @RequestMapping(value = "/salary")
    public CommonResult salary(HttpSession session){
        Salarys salarys = new Salarys();
        salarys.seteId((String) session.getAttribute(EmployeesService.SESSION_EID));
        List<Salarys> salarysList = employeesService.salaryList(salarys);
        return new CommonResult<List<Salarys>>(Result.SUCCESS.getCode(),"获取成功",salarysList);
    }
}
