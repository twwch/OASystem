package com.oa.service;

import com.oa.bean.LeaveSales;
import com.oa.utils.CommonResult;

import java.util.List;

/**
 * @author CHTW
 * @date 2020/3/19 11:12
 */
public interface LeaveSalesService {
    /**
     * 获取请假信息人数(未审核)
     *  @author CHTW
     * @return
     */
    public List<LeaveSales> getCount();

    public List<LeaveSales> getXiaoJia();

}
