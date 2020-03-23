package com.oa.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author CHTW
 * @date 2020/3/23 11:45
 */
public class Test {
    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");//注意月份是MM
        //获取前月的第一天
        Calendar cal_1=Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String firstDay = format.format(cal_1.getTime());
        System.out.println(firstDay.substring(0,8));
        System.out.println("-----1------firstDay:"+firstDay);

        //获取前月的最后一天
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天
        String lastDay = format.format(cale.getTime());
        System.out.println("-----2------lastDay:"+lastDay);
    }
}
