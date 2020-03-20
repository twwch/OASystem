package com.oa.service.impl;

import com.oa.bean.EmpLost;
import com.oa.mapper.EmpLostMapper;
import com.oa.service.EmpLostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
