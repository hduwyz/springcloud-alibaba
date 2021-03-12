package com.websocket.demo.service;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Calendar;
import java.util.Date;

@Data
public class HutoolTest {

    public static void main(String[] args) {
//        //当前时间
//        Date date = DateUtil.date();
//        //当前时间
//        Date date2 = DateUtil.date(Calendar.getInstance());
//        //当前时间
//        Date date3 = DateUtil.date(System.currentTimeMillis());
//        //当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
//        String now = DateUtil.now();
//        //当前日期字符串，格式：yyyy-MM-dd
//        String today= DateUtil.today();


//        String dateStr1 = "2017-03-01 22:33:23";
//        Date date1 = DateUtil.parse(dateStr1);
//
//        String dateStr2 = "2017-04-01 23:33:23";
//        Date date2 = DateUtil.parse(dateStr2);
//
////相差一个月，31天
//        long betweenDay = DateUtil.between(date1, date2, DateUnit.DAY);
        DataX annotation = User.class.getAnnotation(DataX.class);
        annotation.name();
        System.out.println("============" + annotation.name());

    }

    @DataX
    public class User{
        int e;
    }



}
