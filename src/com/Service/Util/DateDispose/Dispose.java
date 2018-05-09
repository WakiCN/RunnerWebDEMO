package com.Service.Util.DateDispose;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 提供格式化时间的工具类,内有包含已设置好的时间格式
 */
public class Dispose {
    public static final String YEAR = "yy";
    public static final String MONTH = "yy/MM/dd";
    public static final String DAY = "yy/MM/dd";
    public static final String SECONDS = "yy/MM/dd/HH:mm:ss";

    /**
     * 接收时间以及转换格式后转换成对应的String字符串
     *
     * @param type 转换格式字符串
     */
    public static String formateDate(Date d, String type) {
        return new SimpleDateFormat(type).format(d);
    }

    /**
     * 接收字符串以及转换格式后转换成对应的Date类型
     *
     * @param type 转换格式字符串
     */
    public static Date formateString(String time, String type) {
        try {
            return new SimpleDateFormat(type).parse(time);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
