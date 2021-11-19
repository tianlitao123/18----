package com.example.zhihu.tools;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @data 2021年11月13日15:34
 * 返回当前系统年月
 */
@Configuration
public class GetTime {
    Date date = new Date();
    SimpleDateFormat year = new SimpleDateFormat("yyyy");
    SimpleDateFormat month = new SimpleDateFormat("MM");
    SimpleDateFormat day = new SimpleDateFormat("dd");
    SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
    public int getYear(){
        return Integer.parseInt(year.format(date));
    }
    public int getMonth(){
        return Integer.parseInt(month.format(date));
    }
    public int getDay(){
        return Integer.parseInt(day.format(date));
    }
    public String getTime() {
        return time.format(date);
    }
}
