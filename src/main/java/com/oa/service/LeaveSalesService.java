package com.oa.service;

import com.github.pagehelper.PageInfo;
import com.oa.bean.LeaveSales;

/**
 * @author CHTW
 * @date 2020/3/19 11:12
 */
public interface LeaveSalesService {
    /**
     * 已请假员工列表
     * @param nowpage
     * @param size
     * @param eId
     * @param name
     * @author CHTW
     * @return
     */
    PageInfo<LeaveSales> getLeave(int nowpage, int size, String eId, String name);

    /**
     * 销假
     * @param id
     * @return
     */
    int selling(Integer id);

    /**
     * 获取已经销假列表
     * @param nowpage
     * @param size
     * @param eId
     * @param name
     * @author CHTW
     * @return
     */
    PageInfo<LeaveSales> getSelling(int nowpage, int size, String eId, String name);
    /**
     * 获取未审核
     * @param nowpage
     * @param size
     * @param eId
     * @param name
     * @author CHTW
     * @return
     */
    PageInfo<LeaveSales> getUnaudited(int nowpage, int size, String eId, String name);

    /**
     * 通过审核
     * @param id
     * @author CHTW
     * @return
     */
    Integer passLeave(Integer id);

    /**
     * 未通过审核
     * @param id
     * @author CHTW
     * @return
     */
    Integer notPassLeave(Integer id,String failMessage);


    /**
     * 获取未通过审核列表
     * @param nowpage
     * @param size
     * @param eId
     * @param name
     * @author CHTW
     * @return
     */
    PageInfo<LeaveSales> getNotPass(int nowpage, int size, String eId, String name);
}
