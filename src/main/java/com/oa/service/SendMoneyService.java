package com.oa.service;

/**
 * @author CHTW
 * @date 2020/3/23 13:54
 */
public interface SendMoneyService {

    /**
     * 判断该月是否发工资
     * @param month
     * @author CHTW
     * @return
     */
    Boolean isSend(String month);

    /**
     * 添加
     * @author CHTW
     * @param month
     * @return
     */
    int add(String month);
}
