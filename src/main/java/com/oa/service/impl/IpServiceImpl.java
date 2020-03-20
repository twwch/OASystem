package com.oa.service.impl;

import com.oa.bean.PubIp;
import com.oa.bean.PubIpExample;
import com.oa.mapper.PubIpMapper;
import com.oa.service.IpService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zqq
 * @create 2020-03-20-21:40
 */
public class IpServiceImpl implements IpService {

    @Autowired
    private PubIpMapper pubIpMapper;


    /**
     * @Description: judgeIp  查询公网ip
     * @param: [pubIp]
     * @return: int  返回和公网ip相同的数量
     * @auther: zqq
     * @date: 20/3/20 21:47
     */
    @Override
    public int judgeIp(PubIp pubIp) {
        PubIpExample pub = new PubIpExample();
        PubIpExample.Criteria criteria = pub.createCriteria().andIpEqualTo(pubIp.getIp());
        List<PubIp> pubIpList = pubIpMapper.selectByExample(pub);
        return pubIpList.size();
    }
}
