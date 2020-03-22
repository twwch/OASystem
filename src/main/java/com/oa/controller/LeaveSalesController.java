package com.oa.controller;

import com.github.pagehelper.PageInfo;
import com.oa.bean.LeaveSales;
import com.oa.enumutil.Result;
import com.oa.service.impl.LeaveSalesServiceImpl;
import com.oa.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
}
