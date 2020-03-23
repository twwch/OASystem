package com.oa.service;

import com.github.pagehelper.PageInfo;
import com.oa.bean.Salarys;

/**
 * @author CHTW
 * @date 2020/3/23 10:35
 */
public interface SalarysService {

    /**
     * 模拟发工资
     * @author CHTW
     * @return
     */
    int sendMoney();

    /**
     * 获取薪水列表
     * @param nowpage
     * @param size
     * @param eId
     * @author CHTW
     * @return
     */
    PageInfo<Salarys> easyuiGetData(int nowpage, int size, String eId);
}
