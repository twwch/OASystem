package com.oa.controller;

import com.github.pagehelper.PageInfo;
import com.oa.bean.Employees;
import com.oa.bean.LeaveSales;
import com.oa.enumutil.Result;
import com.oa.service.EmployeesService;
import com.oa.service.impl.EmployeesServiceImpl;
import com.oa.service.impl.LeaveSalesServiceImpl;
import com.oa.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CHTW
 * @date 2020/3/19 11:23
 */

@RestController
@RequestMapping("/leave")
public class LeaveSalesController {

    @Autowired
    private LeaveSalesServiceImpl leaveSalesService;

    private EmployeesServiceImpl employeesService;

    /**
     * 获取已请假的员工（未销假并且审核通过）
     * @param page
     * @param rows
     * @param eId
     * @param name
     * @author CHTW
     * @return
     */
    @RequestMapping(value = "/getLeave",method = RequestMethod.GET)
    public Map<String,Object> getLeave(@RequestParam(defaultValue = "1", required = false) int page, Integer rows, String eId, String name){
       PageInfo<LeaveSales> info = leaveSalesService.getLeave(page,rows,eId,name);
       Map<String, Object> maps = new HashMap<String, Object>();
       if(info != null){
           maps.put("total", info.getTotal());
           maps.put("rows", info.getList());
           return maps;
       }
       return null;
   }

    /**
     * 销假接口
     * @param id 主键id
     *  @author CHTW
     * @return
     */
   @RequestMapping(value = "/selling",method = RequestMethod.GET)
    public CommonResult<Integer> selling(Integer id){
       int i = leaveSalesService.selling(id);
       if(i>0) {
           return new CommonResult<Integer>(Result.SUCCESS.getCode(), "销假成功", i);
       }
       return new CommonResult<Integer>(Result.FAIL.getCode(),"销假失败");
   }

    /**
     * 获取已经销假列表
     * @param page
     * @param rows
     * @param eId
     * @param name
     * @author CHTW
     * @return
     */
    @RequestMapping(value = "/getselling",method = RequestMethod.GET)
    public Map<String,Object> getSelling(@RequestParam(defaultValue = "1", required = false) int page, Integer rows, String eId, String name){
        PageInfo<LeaveSales> info = leaveSalesService.getSelling(page,rows,eId,name);
        Map<String, Object> maps = new HashMap<String, Object>();
        if(info != null){
            maps.put("total", info.getTotal());
            maps.put("rows", info.getList());
            return maps;
        }
        return null;
    }
    /**
     * 获取未审核
     * @param page
     * @param rows
     * @param eId
     * @param name
     * @author CHTW
     * @return
     */
    @RequestMapping(value = "/getUnaudited",method = RequestMethod.GET)
    public Map<String,Object> getUnaudited(@RequestParam(defaultValue = "1", required = false) int page, Integer rows, String eId, String name){
        PageInfo<LeaveSales> info = leaveSalesService.getUnaudited(page,rows,eId,name);
        Map<String, Object> maps = new HashMap<String, Object>();
        if(info != null){
            maps.put("total", info.getTotal());
            maps.put("rows", info.getList());
            return maps;
        }
        return null;
    }
    /**
     * 通过审核
     * @param id
     * @author CHTW
     * @return
     */
    @RequestMapping(value = "/passleave",method = RequestMethod.GET)
    public CommonResult<Integer> passLeave(Integer id){
        int i = leaveSalesService.passLeave(id);
        if(i > 0){
            return  new CommonResult<Integer>(Result.SUCCESS.getCode(),"审核通过",i);
        }
        return new CommonResult<Integer>(Result.FAIL.getCode(),"审核失败");
    }

    /**
     * 未通过审核
     * @param id
     * @author CHTW
     * @return
     */
    @RequestMapping(value = "/notpassleave",method = RequestMethod.GET)
    public CommonResult<Integer> notPassLeave(Integer id,String failMessage){
        int i = leaveSalesService.notPassLeave(id,failMessage);
        if(i > 0){
            return  new CommonResult<Integer>(Result.SUCCESS.getCode(),"审核未通过",i);
        }
        return new CommonResult<Integer>(Result.FAIL.getCode(),"审核失败");
    }

    /**
     * 获取未审核
     * @param page
     * @param rows
     * @param eId
     * @param name
     * @author CHTW
     * @return
     */
    @RequestMapping(value = "/getnotpass",method = RequestMethod.GET)
    public Map<String,Object> getNotPass(@RequestParam(defaultValue = "1", required = false) int page, Integer rows, String eId, String name){
        PageInfo<LeaveSales> info = leaveSalesService.getNotPass(page,rows,eId,name);
        Map<String, Object> maps = new HashMap<String, Object>();
        if(info != null){
            maps.put("total", info.getTotal());
            maps.put("rows", info.getList());
            return maps;
        }
        return null;
    }


    /**
     * @Description: leave  员工请求处理
     * @param: [leaveSales]
     * @return: com.oa.utils.CommonResult 返回请假请求是否已经入库
     * @auther: zqq
     * @date: 20/3/23 7:54
     */
    @RequestMapping(value = "/leave")
    public CommonResult leave(LeaveSales leaveSales, HttpSession session){
        leaveSales.seteId((String) session.getAttribute(EmployeesService.SESSION_EID));
        Employees message = employeesService.getMessage((Employees) session.getAttribute(EmployeesService.SESSION_EID));
        leaveSales.setName(message.getName());
        //初始为未审核
        leaveSales.setAudtiState(0);
        //初始化为未销假
        leaveSales.setSalesState(1);
        int i = leaveSalesService.leaveCode(leaveSales);
        return i == 1 ? new CommonResult(Result.SUCCESS.getCode(),"发送成功") : new CommonResult(Result.FAIL.getCode(),
                "发送失败");
    }

    /**
     * @Description: leaveCheck  查询某员工的请假状态
     * @param: [session]
     * @return: com.oa.utils.CommonResult
     * @auther: zqq
     * @date: 20/3/23 11:11
     */
    @RequestMapping(value = "/levaeCheck", method = RequestMethod.GET)
    public CommonResult leaveCheck(HttpSession session){
        LeaveSales leaveSales = new LeaveSales();
        leaveSales.seteId((String) session.getAttribute(EmployeesService.SESSION_EID));
        List<LeaveSales> leaveList = leaveSalesService.getLeaveList(leaveSales);
        return new CommonResult<List<LeaveSales>>(Result.SUCCESS.getCode(), "获取成功", leaveList);
    }

    /**
     * @Description: backLeave  员工待销假列表查询
     * @param: [session]
     * @return: com.oa.utils.CommonResult
     * @auther: zqq
     * @date: 20/3/23 11:14
     */
    @RequestMapping("/backLeave")
    public CommonResult backLeave(HttpSession session){
        LeaveSales sales = new LeaveSales();
        sales.seteId((String) session.getAttribute(EmployeesService.SESSION_EID));
        // 1表示未销假
        sales.setSalesState(1);
        // 1表示审核通过
        sales.setAudtiState(1);
        List<LeaveSales> notBackSell = leaveSalesService.getNotBackSell(sales);
        return new CommonResult<List<LeaveSales>>(Result.SUCCESS.getCode(),"获取成功", notBackSell);
    }

    /**
     * @Description: dealVaction 处理销假请求
     * @param: [id, session]
     * @return: com.oa.utils.CommonResult
     * @auther: zqq
     * @date: 20/3/23 11:47
     */
    @RequestMapping("/dealVaction")
    public CommonResult dealVaction(int id,HttpSession session){
        LeaveSales leaveSales = new LeaveSales();
        leaveSales.setId(id);
        leaveSales.seteId((String) session.getAttribute(EmployeesService.SESSION_EID));
        int i = leaveSalesService.dealNum(leaveSales);
        return i == 1 ? new CommonResult(Result.SUCCESS.getCode(),"修改成功"):new CommonResult(Result.FAIL.getCode(),
                "修改失败");
    }
}
