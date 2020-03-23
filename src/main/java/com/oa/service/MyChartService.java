package com.oa.service;

import com.oa.utils.MyChartData;

import java.util.List;

/**
 * @author CHTW
 * @date 2020/3/23 14:36
 */
public interface MyChartService {

    /**
     * 获取饼形图json数据
     * @author CHTW
     * @return
     */
    List<MyChartData> data();
}
