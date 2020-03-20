package com.oa.service;

import com.github.pagehelper.PageInfo;
import com.oa.bean.EmpLost;

/**
 * @author CHTW
 * @date 2020/3/20 15:00
 */
public interface EmpLostService {

    int add(EmpLost empLost);

    PageInfo<EmpLost> easyuiGetData(int nowpage, int size, String eId, String name);
}
