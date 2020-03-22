package com.oa.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public static String getInTime(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        return simpleDateFormat.format(date);
    }
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


}
