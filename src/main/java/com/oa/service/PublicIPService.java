package com.oa.service;

import com.github.pagehelper.PageInfo;
import com.oa.bean.PubIp;

/**
 * @author CHTW
 * @date 2020/3/21 9:55
 */
public interface PublicIPService {

    PageInfo<PubIp> easyuiGetData(int nowpage, int size);

    Integer add(PubIp pubIp);

    PubIp getById(Integer id);

    Integer update(PubIp pubIp);

    Integer dele(Integer id);
}
