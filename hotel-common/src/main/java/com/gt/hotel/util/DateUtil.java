package com.gt.hotel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期处理相关工具类
 */
public class DateUtil {
    /**
     * 日期格式枚举类，根据需要添加其他格式
     **/
    public enum DatePattern {
        ISO_SECOND("yyyy-MM-dd'T'HH:mm:ss", "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$"),
        ISO_MINUTE("yyyy-MM-dd'T'HH:mm", "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}$"),
        DATE_TIME("yyyy-MM-dd HH:mm:ss", "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$"),
        DATE_TIME_FULL("yyyy-MM-dd HH:mm:ss,SSS", "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{3}$"),
        DATE_ONLY("yyyy-MM-dd", "^\\d{4}-\\d{2}-\\d{2}$"),
        YEAR_MONTH("yyyy-MM", "^\\d{4}-\\d{2}$");

        DatePattern(String pattern, String regex) {
            this.pattern = pattern;
            this.regex = regex;
        }

        public String getPattern() {
            return pattern;
        }

        public String getRegex() {
            return regex;
        }

        private String pattern;
        private String regex;

        /**
         * 根据日期字符串，判断该日期的格式类型。
         *
         * @param dateStr 日期字符串
         * @return 日期的格式类型，比如getPatternByDateStr("2016-04-27 10:15:08")返回："yyyy-MM-dd HH:mm:ss"
         */
        public static String getPatternByDateStr(String dateStr) {
            for (DatePattern df : DatePattern.values()) {
                if (RegexUtils.matches(dateStr, df.getRegex())) {
                    return df.getPattern();
                }
            }
            return null;
        }
    }

    /**
     * 字符串转换为Date对象，自动匹配日期格式
     *
     * @param strDate 日期字符串
     * @return Date
     */
    public static Date stringToDate(String dateStr) {
        return stringToDate(dateStr, DatePattern.getPatternByDateStr(dateStr));
    }

    /**
     * 把字符串转换为Date类型
     *
     * @param dateStr 日期字符串
     * @param pattern 日期格式
     * @return
     */
    public static Date stringToDate(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 将指定的日期转换成long时间戳
     *
     * @param String date 需要转换的日期，自动匹配日期格式
     * @return long 时间戳，单位：秒
     */
    public static long stringToTimestamp(String dateStr) {
        String d = dateStr.trim();
        String pattern = DatePattern.getPatternByDateStr(dateStr);

        return stringToTimestamp(d, pattern);
    }

    /**
     * 将指定的日期转换成long时间戳
     *
     * @param String date 需要转换的日期
     * @param String dateFormat 需要转换的日期格式
     * @return long 时间戳，单位：秒
     */
    public static long stringToTimestamp(String dateStr, String pattern) {
        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(pattern).parse(dateStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp / 1000;
    }

    /**
     * 日期转String
     *
     * @return
     */
    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

	/*public static String locadateListToString(LocadateList date) {
    return date.toString();
	}*/

    /**
     * 将long时间戳转换成日期
     *
     * @param long timestamp 时间戳，单位：秒
     * @return String 日期字符串
     */
    public static Date timestampToDate(long timestamp) {
        SimpleDateFormat sd = new SimpleDateFormat(DatePattern.DATE_TIME.getPattern());
        sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return new Date(timestamp * 1000);
    }

    /**
     * 将long时间戳转换pattern格式的String
     *
     * @param timestamp
     * @param pattern   格式
     * @return 格式化后的时间
     */
    public static String timestampToString(long timestamp, String pattern) {
        Date date = timestampToDate(timestamp);
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }


    /**
     * 获取系统当前时间字符串（"yyyy-MM-dd HH:mm:ss"）
     *
     * @return
     */
    public static String nowToString() {
        return nowToString(DatePattern.DATE_TIME);
    }

    /**
     * 获取系统当前时间，指定格式
     *
     * @return
     */
    public static String nowToString(DatePattern pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern.getPattern());
        return df.format(new Date());
    }

    /**
     * 将当前日期转换成long时间戳
     *
     * @return long 时间戳，单位：秒
     */
    public static long nowToTimestamp() {
        long timestamp = System.currentTimeMillis();
        return timestamp / 1000;
    }

    /**
     * 和当前时间比较（Date格式）
     *
     * @param date1 比较日期
     * @return 负数：之前；0：当前；正数：之后
     */
    public static int compareDateWithNow(Date date1) {
        Date date2 = new Date();
        int rNum = date1.compareTo(date2);
        return rNum;
    }

    /**
     * 和当前时间比较(时间戳比较)
     *
     * @param date
     * @return
     */
    public static int compareDateWithNow(long date1) {
        long date2 = nowToTimestamp();
        if (date1 > date2) {
            return 1;
        } else if (date1 < date2) {
            return -1;
        } else {
            return 0;
        }
    }


    /**
     * 两个日期相差的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date beginDate, Date endDate) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(beginDate);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(endDate);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                    timeDistance += 366;
                } else {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else {
            return day2 - day1;
        }
    }

    /**
     * 根据开始时间和结束时间返回时间段内的时间集合
     *
     * <pre>
     *     例如： beginDate: 2017-12-10 endDate: 2017-12-20
     *     获取 开始日期与结束日期之间，每一天的日期。 2017-12-10，2017-12-11，2017-12-12 ....
     * </pre>
     *
     * @param beginDate  开始日期
     * @param endDate 结束日期
     * @return List<Date> 日期列表
     */
    public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
        List<Date> dateList = new ArrayList<>();
        //把开始时间加入集合
        dateList.add(beginDate);
        Calendar cal = Calendar.getInstance();
        //使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(beginDate);
        while (true) {
            //根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                dateList.add(cal.getTime());
            } else {
                break;
            }
        }
        //把结束时间加入集合
        dateList.add(endDate);
        return dateList;
    }


    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String bDate = "2017-12-10";
        String eDate = "2017-12-20";
        Date beginDate = sdf.parse(bDate);
        Date endDate = sdf.parse(eDate);
        List<Date> twoDate = getDatesBetweenTwoDate(beginDate, endDate);
        for (Date o : twoDate) {
            System.out.println(sdf.format(o));
        }

    }
}
