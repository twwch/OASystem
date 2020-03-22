package com.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa.bean.PubIp;
import com.oa.bean.PubIpExample;
import com.oa.mapper.PubIpMapper;
import com.oa.service.PublicIPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CHTW
 * @date 2020/3/21 9:57
 */
@Service
public class PublicIPServiceImpl implements PublicIPService {
    @Autowired
    private PubIpMapper  pubIpMapper;

    /**
     * 获取ip列表
     * @author CHTW
     * @param nowpage
     * @param size
     * @return
     */
    public PageInfo<PubIp> easyuiGetData(int nowpage, int size) {
        PageHelper.startPage(nowpage, size);
        PubIpExample example = new PubIpExample();
        PubIpExample.Criteria ctr = example.createCriteria();
        List<PubIp> lists = pubIpMapper.selectByExample(example);
        PageInfo<PubIp> info = new PageInfo<PubIp>(lists);
        return info;
    }

    /**
     * 添加ip
     * @author CHTW
     * @param pubIp
     * @return
     */
    public Integer add(PubIp pubIp) {
        return pubIpMapper.insert(pubIp);
    }

    /**
     * 通过ID获取
     * @author CHTW
     * @param id
     * @return
     */
    public PubIp getById(Integer id) {
        return pubIpMapper.selectByPrimaryKey(id);
    }

    /**
     * 通过id更新
     * @author CHTW
     * @param pubIp
     * @return
     */
    public Integer update(PubIp pubIp) {
        return pubIpMapper.updateByPrimaryKey(pubIp);
    }

    /**
     * 删除ip
     * @author CHTW
     * @param id
     * @return
     */
    public Integer dele(Integer id) {
        return pubIpMapper.deleteByPrimaryKey(id);
    }
}
