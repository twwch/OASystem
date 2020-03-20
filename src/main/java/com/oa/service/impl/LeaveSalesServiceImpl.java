package com.oa.service.impl;

import com.oa.bean.LeaveSales;
import com.oa.bean.LeaveSalesExample;
import com.oa.enumutil.LeaveSalesEnum;
import com.oa.enumutil.Result;
import com.oa.mapper.LeaveSalesMapper;
import com.oa.service.LeaveSalesService;
import com.oa.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 获取请假信息人数(未审核)
     *  @author CHTW
     * @return
     */
    public List<LeaveSales> getCount() {
        LeaveSalesExample example = new LeaveSalesExample();
        LeaveSalesExample.Criteria re = example.createCriteria();
        re.andAudtiStateEqualTo(LeaveSalesEnum.QING_WEI.getCode());
        List<LeaveSales> list = leaveSalesMapper.selectByExample(example);
        return  list;
    }

    /**
     * 已经销假
     * @author CHTW
     * @return
     */
    public List<LeaveSales> getXiaoJia() {
        LeaveSalesExample example = new LeaveSalesExample();
        LeaveSalesExample.Criteria re = example.createCriteria();
        re.andSalesStateEqualTo(LeaveSalesEnum.YIXIAO.getCode());
        List<LeaveSales> list = leaveSalesMapper.selectByExample(example);
        return  list;
    }


}
