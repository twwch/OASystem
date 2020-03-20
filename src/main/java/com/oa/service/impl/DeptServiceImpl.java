package com.oa.service.impl;

import com.oa.bean.Dept;
import com.oa.bean.DeptExample;
import com.oa.bean.ResultDept;
import com.oa.enumutil.Result;
import com.oa.mapper.DeptMapper;
import com.oa.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CHTW
 * @date 2020/3/19 18:02
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    /**
     * 获取所有部门信息
     * @return
     */
    public List<ResultDept> getAll() {
        List<Dept> list = deptMapper.selectByExample(new DeptExample());
        List<ResultDept> rlist = new ArrayList<ResultDept>();
        for (Dept dept : list) {
            ResultDept resultDept = new ResultDept(dept.getId(),dept.getPid(),dept.getName());
            if("true".equals(dept.getOpen())){
                resultDept.setOpen(true);
            }
            rlist.add(resultDept);
        }
        return rlist;
    }
}
