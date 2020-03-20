package com.oa.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author CHTW
 * @date 2020/3/20 17:33
 */
public class TimeUtils {

    public static Date getDate(String inWithTime){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
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
}
