package com.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa.bean.LeaveSales;
import com.oa.bean.LeaveSalesExample;
import com.oa.enumutil.LeaveSalesEnum;
import com.oa.mapper.LeaveSalesMapper;
import com.oa.service.LeaveSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author CHTW
 * @date 2020/3/19 11:13
 */
@Service
public class LeaveSalesServiceImpl implements LeaveSalesService {

    @Autowired
    private LeaveSalesMapper leaveSalesMapper;

    /**
     * 获取已请假的员工（未销假并且审核通过）
     * @param nowpage
     * @param size
     * @param eId
     * @param name
     * @author CHTW
     * @return
     */
    @Override
    public PageInfo<LeaveSales> getLeave(int nowpage, int size, String eId, String name) {
        PageHelper.startPage(nowpage, size);
        LeaveSalesExample leaveSalesExample = new LeaveSalesExample();
        LeaveSalesExample.Criteria crt = leaveSalesExample.createCriteria();
        if (!StringUtils.isEmpty(eId)) {
            crt.andEIdEqualTo(eId);
        }
        if (!StringUtils.isEmpty(name)) {
            crt.andNameEqualTo(name);
        }
        crt.andSalesStateEqualTo(LeaveSalesEnum.WEIXIAO.getCode());//未销假
        crt.andAudtiStateEqualTo(LeaveSalesEnum.QING_YI.getCode());//审核通过
        List<LeaveSales> leaveSales = leaveSalesMapper.selectByExample(leaveSalesExample);
        PageInfo<LeaveSales> info = new PageInfo<LeaveSales>(leaveSales);
        return info;
    }

    /**
     * 销假
     * @param id
     * @author CHTW
     * @return
     */
    @Override
    public int selling(Integer id) {
        LeaveSales leaveSales = new LeaveSales();
        leaveSales.setSalesState(LeaveSalesEnum.YIXIAO.getCode());
        leaveSales.setId(id);
        return leaveSalesMapper.updateByPrimaryKeySelective(leaveSales);
    }
    /**
     * 获取已经销假列表
     * @param nowpage
     * @param size
     * @param eId
     * @param name
     * @author CHTW
     * @return
     */
    @Override
    public PageInfo<LeaveSales> getSelling(int nowpage, int size, String eId, String name) {
        PageHelper.startPage(nowpage, size);
        LeaveSalesExample leaveSalesExample = new LeaveSalesExample();
        LeaveSalesExample.Criteria crt = leaveSalesExample.createCriteria();
        if (!StringUtils.isEmpty(eId)) {
            crt.andEIdEqualTo(eId);
        }
        if (!StringUtils.isEmpty(name)) {
            crt.andNameEqualTo(name);
        }
        crt.andSalesStateEqualTo(LeaveSalesEnum.YIXIAO.getCode());//未销假
        List<LeaveSales> leaveSales = leaveSalesMapper.selectByExample(leaveSalesExample);
        PageInfo<LeaveSales> info = new PageInfo<LeaveSales>(leaveSales);
        return info;
    }
    /**
     * 获取未审核列表
     * @param nowpage
     * @param size
     * @param eId
     * @param name
     * @author CHTW
     * @return
     */
    @Override
    public PageInfo<LeaveSales> getUnaudited(int nowpage, int size, String eId, String name) {
        PageHelper.startPage(nowpage,size);
        LeaveSalesExample leaveSalesExample = new LeaveSalesExample();
        LeaveSalesExample.Criteria crt = leaveSalesExample.createCriteria();
        if (!StringUtils.isEmpty(eId)) {
            crt.andEIdEqualTo(eId);
        }
        if (!StringUtils.isEmpty(name)) {
            crt.andNameEqualTo(name);
        }
        crt.andAudtiStateEqualTo(LeaveSalesEnum.QING_WEI.getCode());//未审核
        List<LeaveSales> leaveSales = leaveSalesMapper.selectByExample(leaveSalesExample);
        PageInfo<LeaveSales> info = new PageInfo<LeaveSales>(leaveSales);
        return info;
    }

    /**
     * 通过审核
     * @param id
     * @author CHTW
     * @return
     */
    @Override
    public Integer passLeave(Integer id) {
        LeaveSales leaveSales = new LeaveSales();
        leaveSales.setId(id);
        leaveSales.setFailMessage("审核通过");
        leaveSales.setAudtiState(LeaveSalesEnum.QING_YI.getCode());//通过审核
        leaveSales.setSalesState(LeaveSalesEnum.WEIXIAO.getCode());//未销假
        return leaveSalesMapper.updateByPrimaryKeySelective(leaveSales);
    }
    /**
     * 未通过审核
     * @param id
     * @author CHTW
     * @return
     */
    @Override
    public Integer notPassLeave(Integer id,String failMessage) {
        LeaveSales leaveSales = new LeaveSales();
        leaveSales.setId(id);
        leaveSales.setFailMessage(failMessage);
        leaveSales.setAudtiState(LeaveSalesEnum.QING_NO.getCode());//未通过审核
        return leaveSalesMapper.updateByPrimaryKeySelective(leaveSales);
    }
    /**
     * 获取未通过审核列表
     * @param nowpage
     * @param size
     * @param eId
     * @param name
     * @author CHTW
     * @return
     */
    @Override
    public PageInfo<LeaveSales> getNotPass(int nowpage, int size, String eId, String name) {
        PageHelper.startPage(nowpage,size);
        LeaveSalesExample leaveSalesExample = new LeaveSalesExample();
        LeaveSalesExample.Criteria crt = leaveSalesExample.createCriteria();
        if (!StringUtils.isEmpty(eId)) {
            crt.andEIdEqualTo(eId);
        }
        if (!StringUtils.isEmpty(name)) {
            crt.andNameEqualTo(name);
        }
        crt.andAudtiStateEqualTo(LeaveSalesEnum.QING_NO.getCode());//未通过审核
        List<LeaveSales> leaveSales = leaveSalesMapper.selectByExample(leaveSalesExample);
        PageInfo<LeaveSales> info = new PageInfo<LeaveSales>(leaveSales);
        return info;
    }
}
