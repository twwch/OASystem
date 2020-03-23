package com.oa.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author CHTW
 * @date 2020/3/20 17:33
 */
public class TimeUtils {

    // 上班时间
    public static String upTime = "09:00:00";

    // 下班时间
    public static String downTime = "18:00:00";

    /**
     * String转date
     * @param inWithTime
     * @return
     */
    public static Date getDate(String inWithTime){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月份是MM
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(inWithTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 格式化时间
     * @param date
     * @return
     */
    public static String getInTime(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        return simpleDateFormat.format(date);
    }

    /**
     * 工资发放月份
     * @param date
     * @return
     */
    public static String sendMoneyTime(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");//注意月份是MM
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
        date = calendar.getTime();
        String resultTime = simpleDateFormat.format(date);
        return resultTime;
    }

    /**
     * 打卡时间
     * @param date
     * @return
     */
    public static String getInTime1(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月份是MM
        return simpleDateFormat.format(date);
    }
    /**
     * @Description: judgeUpTime  判断上班是否迟到
     * @param: [str]
     * @return: boolean true没迟到  false迟到
     * @auther: zqq
     * @date: 20/3/21 12:36
     */
    public static boolean judgeUpTime(String str) throws ParseException{
        return judegTime(str);
    }

    /**
     * @Description: judegTime  根据传入的时分秒字符串比较时间
     * @param: [str]
     * @return: boolean
     * @auther: zqq
     * @date: 20/3/21 12:38
     */
    private static boolean judegTime(String str) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        Date realTime = simpleDateFormat.parse(str);
        Date upTime = simpleDateFormat.parse(TimeUtils.upTime);
        Date downTime = simpleDateFormat.parse(TimeUtils.downTime);
        if (realTime.after(upTime) && realTime.before(downTime)){
            return false;
        }
        return true;
    }

    /**
     * @Description: judgeDownTime  判断下班是否早退
     * @param: [str]
     * @return: boolean  true没早退   false早退
     * @auther: zqq
     * @date: 20/3/21 12:37
     */
    public static boolean judgeDownTime(String str) throws ParseException{
        return judegTime(str);
    }

    /**
     * 获取前一个月第一天
     * @return
     */
    public static Date getFirstDay(){
        Calendar cal_1=Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        return  cal_1.getTime();
    }

    /**
     * 获取前一个月的最后一天
     */
    public static Date getLastDay(){
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天
        return cale.getTime();
    }

    /**
     * 获取本月第一天
     * @return
     */
    public static Date getBenFirstDay(){
        Calendar cale = Calendar.getInstance();
        // 获取本月的第一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return  cale.getTime();
    }

    /**
     * 获取本月最后一天
     * @return
     */
    public static Date getBenLastDay(){
        Calendar cale = Calendar.getInstance();
        // 获取本月的最后一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return cale.getTime();
    }
}
