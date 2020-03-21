package com.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa.bean.EmpLost;
import com.oa.bean.EmpLostExample;
import com.oa.mapper.EmpLostMapper;
import com.oa.service.EmpLostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author CHTW
 * @date 2020/3/20 15:01
 */
@Service
public class EmpLostServiceImpl implements EmpLostService {
    @Autowired
    private EmpLostMapper empLostMapper;

    public int add(EmpLost empLost) {
        return empLostMapper.insert(empLost);
    }

    public PageInfo<EmpLost> easyuiGetData(int nowpage, int size, String eId, String name) {
        PageHelper.startPage(nowpage, size);
        EmpLostExample example = new EmpLostExample();
        EmpLostExample.Criteria ctr = example.createCriteria();
        if (!StringUtils.isEmpty(eId)) {
            ctr.andEIdEqualTo(eId);
        }
        if (!StringUtils.isEmpty(name)) {
            ctr.andNameEqualTo(name);
        }

        List<EmpLost> lists = empLostMapper.selectByExample(example);
        PageInfo<EmpLost> info = new PageInfo<EmpLost>(lists);
        return info;
    }
}
