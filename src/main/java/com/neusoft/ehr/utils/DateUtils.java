package com.neusoft.ehr.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String getTime(){
        Date day=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(day);
    }

    public static String getDate(){
        Date day=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(day);
    }

    public static Date toDate(String str) {
        //获得SimpleDateFormat类，我们转换为yyyy-MM-dd的时间格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        //使用SimpleDateFormat的parse()方法生成Date
        try {
            date = sf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }
}
