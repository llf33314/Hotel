package com.gt.hotel.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具
 */
public class RegexUtils {
    /**
     * 正则 匹配数字
     */
    private static final String NUMBER = "\\d+";

    /**
     * 判断是否匹配
     *
     * @param str   字符串
     * @param regex 规则
     * @return true/false
     */
    public static boolean matches(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 使用正则表达式regex从文本str（比如html文档）中获取匹配的字符串
     *
     * @param str   字符串
     * @param regex 规则
     * @return 匹配到的所有字符串
     */
    public static List<String> getContentByPattern(String str, String regex) {
        List<String> resultList = new ArrayList<>();
        //根据正则表达式构造一个Pattern对象
        Pattern p = Pattern.compile(regex);

        if (str == null) {
            System.out.println("str不能为NULL！");
            return resultList;
        }

        if (p == null) {
            System.out.println("构造Pattern时发生错误！");
            return resultList;
        }
        //利用patter对象为被匹配的文本构造一个Matcher对象
        Matcher m = p.matcher(str);
        //如果在任何位置中发现匹配的字符串……
        while (m.find()) {
            //保存匹配到的字符串
            resultList.add(m.group());
        }
        return resultList;
    }

    /**
     * 使用正则表达式regex从文本str中获取第一个匹配的字符串
     *
     * @param str
     * @param regex
     * @return
     */
    public static String getFirstMatch(String str, String regex) {
        List<String> ss = getContentByPattern(str, regex);
        if (ss.size() > 0) {
            return ss.get(0);
        } else {
            return null;
        }

    }

    /**
     * 根据正则表达式regex，把str中所有被匹配到的字符串替换成replace
     *
     * @param str     字符串
     * @param regex   规则
     * @param replace 替换
     */
    public static String replaceContentByPattern(String str, String regex, String replace) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.replaceAll(replace);
    }

    /**
     * 从str中找到第一串数字
     *
     * @param str 字符串
     * @return str
     */
    public static String findFirstNumber(String str) {
        Pattern p = Pattern.compile(NUMBER);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return m.group();
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(matches("2016", "\\d{4}"));
        System.out.println(matches("2016-03-15 20:50:00", "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$"));
    }
}
