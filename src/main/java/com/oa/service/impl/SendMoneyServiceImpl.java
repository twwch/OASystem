package com.oa.service.impl;

import com.oa.bean.Sendmoney;
import com.oa.bean.SendmoneyExample;
import com.oa.mapper.SendmoneyMapper;
import com.oa.service.SendMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CHTW
 * @date 2020/3/23 13:56
 */
@Service
public class SendMoneyServiceImpl implements SendMoneyService {
    @Autowired
    private SendmoneyMapper sendmoneyMapper;

    /**
     * 判断该月是否发工资
     *
     * @param month
     * @return
     * @author CHTW
     */
    @Override
    public Boolean isSend(String month) {
        SendmoneyExample example = new SendmoneyExample();
        SendmoneyExample.Criteria re = example.createCriteria();
        re.andMonthEqualTo(month);
        int i = sendmoneyMapper.selectByExample(example).size();
        return i > 0 ? true : false;
    }


    /**
     * 添加
     * @author CHTW
     * @param month
     * @return
     */
    public int add(String month){
        Sendmoney sendmoney = new Sendmoney();
        sendmoney.setMonth(month);
        return sendmoneyMapper.insert(sendmoney);
    }
}
