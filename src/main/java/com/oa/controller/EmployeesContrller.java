package com.oa.controller;

import com.github.pagehelper.PageInfo;
import com.oa.bean.Employees;
import com.oa.enumutil.EmpEnum;
import com.oa.enumutil.Result;
import com.oa.service.impl.EmployeesServiceImpl;
import com.oa.utils.CommonResult;
import com.oa.utils.ResultEmp;
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
 * @date 2020/3/19 9:27
 */
@RestController
@RequestMapping("/empinfo")
public class EmployeesContrller {
    @Autowired
    private EmployeesServiceImpl employeesService;

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
     * @author CHTW
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
     * @author CHTW
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
     * @author CHTW
     */
    @RequestMapping(value = "/addemp", method = RequestMethod.GET)
    public CommonResult<Integer> addemp(ResultEmp resultEmp) {
        int res = employeesService.addemp(resultEmp);
        if (res > 0) {
            return new CommonResult<Integer>(Result.SUCCESS.getCode(), Result.SUCCESS.getMessage(), res);
        }
        return new CommonResult<Integer>(Result.FAIL.getCode(), Result.FAIL.getMessage(), 0);
    }

    /**
     * 通过员工id查找
     *
     * @param eId
     * @return
     * @author CHTW
     */
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
     *
     * @param
     * @return
     * @author CHTW
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public CommonResult<Integer> admin(Integer id, Integer gradeId) {
        if (EmpEnum.ISADMIN.getCode() == gradeId) {
            return new CommonResult<Integer>(Result.FAIL.getCode(), "已经是管理员", 0);
        } else {
            int i = employeesService.admin(id, gradeId);
            if (i > 0) {
                return new CommonResult<Integer>(Result.SUCCESS.getCode(), "修改成功", i);
            } else {
                return new CommonResult<Integer>(Result.FAIL.getCode(), "修改失败", 0);
            }
        }
    }

    /**
     * 获取管理员列表
     *
     * @param page 当前页面
     * @param rows 页面数据条数
     * @param eId  员工id
     * @param name 姓名
     * @param dept 部门
     * @return
     * @author CHTW
     */
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
     * 移出管理员，但不是删除
     *
     * @param id 主键id
     * @return
     * @author CHTW
     */
    @RequestMapping(value = "/removeAdmin", method = RequestMethod.GET)
    public CommonResult<Integer> removeAdmin(Integer id) {
        int i = employeesService.removeAdmin(id, EmpEnum.NOTADMIN.getCode());
        if (i > 0) {
            return new CommonResult<Integer>(Result.SUCCESS.getCode(), "修改成功", i);
        }
        return new CommonResult<Integer>(Result.FAIL.getCode(), "修改失败", 0);
    }


    /**
     * 管理员登录
     *
     * @param eId
     * @param ePassword
     * @return
     * @author CHTW
     */
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public CommonResult<Integer> adminLogin(String eId, String ePassword) {
        //System.out.println(ePassword);
        int i = employeesService.adminLogin(eId, ePassword);
        if (i > 0) {
            return new CommonResult<Integer>(Result.SUCCESS.getCode(), "登录成功", i);
        }
        return new CommonResult<Integer>(Result.FAIL.getCode(), "登录失败", 0);
    }
}
