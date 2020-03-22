package com.oa.controller;

import com.github.pagehelper.PageInfo;
import com.oa.bean.PubIp;
import com.oa.enumutil.Result;
import com.oa.service.impl.PublicIPServiceImpl;
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
 * @date 2020/3/21 10:02
 */
@RestController
@RequestMapping("/ip")
public class PublicIpController {

    @Autowired
    private PublicIPServiceImpl publicIPService;

    /**
     * 获取IP列表
     *
     * @param page
     * @param rows
     * @return
     * @author CHTW
     */
    @RequestMapping(value = "/easyuiGetData", method = RequestMethod.GET)
    public Map<String, Object> easyuiGetData(@RequestParam(defaultValue = "1", required = false) int page, Integer rows) {
        PageInfo<PubIp> info = publicIPService.easyuiGetData(page, rows);
        Map<String, Object> maps = new HashMap<String, Object>();
        if (info != null) {
            maps.put("total", info.getTotal());
            maps.put("rows", info.getList());
            return maps;
        }
        return null;
    }

    /**
     * 添加IP
     *
     * @param pubIp
     * @return
     * @author CHTW
     */
    @RequestMapping(value = "addip", method = RequestMethod.GET)
    public CommonResult<Integer> addip(PubIp pubIp) {
        if (pubIp != null) {
            return new CommonResult<Integer>(Result.SUCCESS.getCode(), "添加IP成功", publicIPService.add(pubIp));
        } else {
            return new CommonResult<Integer>(Result.FAIL.getCode(), "添加IP失败", 0);
        }
    }

    /**
     * 通过ID获取
     *
     * @param id
     * @return
     * @author CHTW
     */
    @RequestMapping(value = "getById", method = RequestMethod.GET)
    public CommonResult<PubIp> getById(Integer id) {
        PubIp pubIp = publicIPService.getById(id);
        if (pubIp != null) {
            return new CommonResult<PubIp>(Result.SUCCESS.getCode(), "获取成功", pubIp);
        } else {
            return new CommonResult<PubIp>(Result.FAIL.getCode(), "获取失败");
        }
    }

    /**
     * 修改ip
     *
     * @param pubIp
     * @return
     * @author CHTW
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult<Integer> update(PubIp pubIp) {
        if (pubIp != null) {
            return new CommonResult<Integer>(Result.SUCCESS.getCode(), "修改成功", publicIPService.update(pubIp));
        } else {
            return new CommonResult<Integer>(Result.FAIL.getCode(), "获取失败", 0);
        }
    }

    /**
     * 删除ip
     * @author CHTW
     * @param id
     * @return
     */
    @RequestMapping(value = "/dele", method = RequestMethod.GET)
    public CommonResult<Integer> dele(Integer id) {
        int i = publicIPService.dele(id);
        if (i > 0) {
            return new CommonResult<Integer>(Result.SUCCESS.getCode(), "删除IP成功", i);
        } else {
            return new CommonResult<Integer>(Result.FAIL.getCode(), "删除失败，IP不存在", 0);
        }
    }
}
