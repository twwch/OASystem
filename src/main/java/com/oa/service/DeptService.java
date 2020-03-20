package com.oa.service;

import com.oa.bean.Dept;
import com.oa.bean.ResultDept;

import java.util.List;

/**
 * @author CHTW
 * @date 2020/3/19 17:58
 */
public interface DeptService {
    /**
     * 获取所有部门
     * @return
     */
    public List<ResultDept> getAll();
}
