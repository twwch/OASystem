package com.oa.service;

import com.oa.bean.PubIp;

/**
 * @author zqq
 * @create 2020-03-20-21:40
 */
public interface IpService {

    /**
     * 对ip进行判断，判断是否是公司的公网ip
     */
    public int judgeIp(PubIp pubIp);

}
