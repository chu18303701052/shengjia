package com.digov.api.util.time;

import com.digov.api.util.common.CommonUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 * 时间工具类
 *
 * @author yuyongxing
 */
public class DateUtil {
    /**
     * 默认时间格式
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时间格式 yyyy-MM-dd
     */
    public static final String FORMAT_Y_M_D = "yyyy-MM-dd";
    /**
     * 时间格式
     * HH:mm
     */
    public static final String FORMAT_H_M = "HH:mm";
    /**
     * 时间格式
     * yyyyMM
     */
    public static final String FORMAT_YM = "yyyyMM";
    /**
     * 时间格式
     * yyyy-MM
     */
    public static final String FORMAT_Y_M = "yyyy-MM";
    /**
     * 时间格式
     * yyyy
     */
    public static final String FORMAT_Y = "yyyy";
    /**
     * 时间格式
     * MM
     */
    public static final String FORMAT_M = "MM";
    /**
     * 时间格式 MM-dd HH:mm:ss
     */
    public static final String FORMAT_M_D_H_M_S = "MM-dd HH:mm:ss";
    /**
     * 时间格式
     * HH
     */
    public static final String FORMAT_H = "HH";

    /**
     *
     * @return
     */
    public static int getCurrentYearInt() {
        String str = dateToStr(new Date(), "yyyy");
        Integer year = Integer.valueOf(str);
        return year;
    }
    public static int getCurrentMonthInt() {
        String str = dateToStr(new Date(), "yyyyMM");
        Integer year = Integer.valueOf(str);
        return year;
    }

    public static int getCurrentMonthInt(Date date) {
        String str = dateToStr(date, "yyyyMM");
        Integer year = Integer.valueOf(str);
        return year;
    }

    /**
     * 获取当天的结束时间
     *
     * @return
     */
    public static Date getTodayEnd() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    /**
     * 获取某一天的结束时间
     *
     * @return
     */
    public static Long getOneDayEnd(Date date) {
        if (CommonUtil.isEmpty(date)) {
            return null;
        }
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.setTime(date);
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime().getTime();
    }


    /**
     * 获取多少天后的结束时间
     *
     * @param days
     */
    public static Date getDaysEnd(Long time, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    /**
     * 获取当天的开始
     *
     * @return
     */
    public static Date getTodayBegin() {
//		Calendar currentDate = new GregorianCalendar();
//		currentDate.set(Calendar.HOUR_OF_DAY, 0);
//		currentDate.set(Calendar.MINUTE, 0);
//		currentDate.set(Calendar.SECOND, 0);
//		currentDate.setTimeZone(TimeZone.getTimeZone("UTC+8"));
//        return currentDate.getTime();
        Long todayBeginMS = getTodayBeginMS();
        return new Date(todayBeginMS);
    }

    /**
     * 获取当天的开始的毫秒数
     *
     * @return
     */
    public static Long getTodayBeginMS(Long currentTimeMS) {
        if (CommonUtil.isEmpty(currentTimeMS)) {
            currentTimeMS = System.currentTimeMillis();
        }
        SimpleDateFormat sdfOne = new SimpleDateFormat("yyyy-MM-dd");
        long zero;
        try {
            zero = sdfOne.parse(sdfOne.format(currentTimeMS)).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            zero = getTodayBeginMS();
        }
        return zero;
    }

    public static Long getTodayBeginMS() {
        long current = System.currentTimeMillis();
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        return zero;
    }

    /**
     * 获取今年的开始
     *
     * @return
     */
    public static Date getNowYearBegin() {
        return getYearStartDate(getCurrentTimeMS());
    }

    /**
     * 获取当前展示的时间
     *
     * @return
     */
    public static Date getNowShowDate() {
        Date date = new Date();
//        Date date = new Date(1577784931000L);
        return date;
    }

    /**
     * 获取当年的开始时间戳
     *
     * @param timeStamp 毫秒级时间戳
     * @return
     */
    public static Date getYearStartDate(Long timeStamp) {
        String timeZone = "GMT+8:00";
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        calendar.setTimeInMillis(timeStamp);
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.DATE, 0);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当年的最后时间戳
     *
     * @param timeStamp 毫秒级时间戳
     * @return
     */
    public static Date getYearEndDate(Long timeStamp) {
        String timeZone = "GMT+8:00";
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        calendar.setTimeInMillis(timeStamp);
        int year = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

    /**
     * 获取指定日期所在月份开始的时间戳
     *
     * @param date 指定日期
     * @return
     */
    public static Long getMonthBegin(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND, 0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月第一天的时间戳
        return c.getTimeInMillis();
    }
    /**
     * 获取指定日期所在月份结束的时间戳
     * @param date 指定日期
     * @return
     */
    public static Long getMonthEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        //设置为当月最后一天
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至23
        c.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至59
        c.set(Calendar.MINUTE, 59);
        //将秒至59
        c.set(Calendar.SECOND,59);
        //将毫秒至999
        c.set(Calendar.MILLISECOND, 999);
        // 获取本月最后一天的时间戳
        return c.getTimeInMillis();
    }
    /**
     * 根据年份的后两位获取年份的全称
     *
     * @param year
     * @return
     */
    public static String getYearByEndTwo(String year) {
        String style = "";
        if (!CommonUtil.isEmpty(year) && year.length() == 2) {
            Integer yearInt = Integer.valueOf(year);
            Integer nowYear = getNowYearEndTwo();
            if (yearInt < (nowYear + 1)) {
                style = "20" + year;
            } else {
                style = "19" + year;
            }
        } else if (!CommonUtil.isEmpty(year) && year.length() == 4) {
            return year;
        }
        return style;
    }

    /**
     * 判断该字符串是不是为年份的后两位
     *
     * @param str
     * @return
     */
    public static boolean checkYearEndTwo(String str) {
        if (CommonUtil.isEmpty(str)) {
            return false;
        }
        String regEx = "\\d{2}";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 显示时间到现在，有多少分钟，有多少小时，有多少天
     *
     * @param createTime
     * @param createTime
     * @return
     */
    public static String getDiffNowByCreateTime(Date createTime) {
        String showDay = "";
        if (CommonUtil.isEmpty(createTime)) {
            return showDay;
        }

        //获取相差多少分钟
        long diffT = getDiffSecondByDate(new Date(), createTime) / 60;
        if (diffT < 1l) {
            showDay = "刚刚";
        }
        if (0l < diffT && diffT < 60l) {
            showDay = diffT + "分钟前";
        }
        diffT = diffT / 60;
        if (0l < diffT && diffT < 24l) {
            showDay = diffT + "小时前";
        }
        diffT = diffT / 24;
        if (0l < diffT) {
            showDay = diffT + "天前";
            if (diffT == 1l) {
                showDay = "昨天";
            }
        }
        return showDay;
    }

    /**
     * 获取两个时间相差几个月
     *
     * @param time1
     * @param time2
     * @return
     */
    public static int getDiffMonthDate(Date time1, Date time2) {
        if (CommonUtil.isEmpty(time1)
                || CommonUtil.isEmpty(time2)) {
            return 0;
        }
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(time1);
        aft.setTime(time2);
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
        return Math.abs(month + result);
    }
    /**
     * 获取两个时间相差几个月
     *
     * @param timeStr1
     * @param timeStr1
     * @return
     */
    public static int getDiffMonthDate(String timeStr1, String timeStr2, String format) {
        if (CommonUtil.isEmpty(timeStr1)
                || CommonUtil.isEmpty(timeStr2)) {
            return 0;
        }

        Date time1 = strtoDate(timeStr1, format);
        Date time2 = strtoDate(timeStr2, format);

        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(time1);
        aft.setTime(time2);
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
        return Math.abs(month + result);
    }

    /**
     * 获取两个时间相差多少秒
     *
     * @param time1
     * @param time2
     * @return
     */
    public static long getDiffSecondByDate(Date time1, Date time2) {
        long diffSec = 0l;
        if (CommonUtil.isEmpty(time1) || CommonUtil.isEmpty(time2)) {
            return diffSec;
        }
        long time1Se = time1.getTime();
        long time2Se = time2.getTime();
        if (time1Se > time2Se) {
            diffSec = (time1Se - time2Se) / 1000;
        } else {
            diffSec = (time2Se - time1Se) / 1000;
        }
        return diffSec;
    }

    /**
     * 获取当前年份的后两位
     *
     * @return
     */
    public static Integer getNowYearEndTwo() {
        return Integer.valueOf(dateToStr(new Date(), "yyyy").substring(2, 4));
    }

    /**
     * Date ---> String
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateToStr(Date date, String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(date);
    }

    /**
     * String --> Date
     *
     * @param date_string
     * @param format
     * @return
     */
    public static Date strtoDate(String date_string, String format) {
        try {
            SimpleDateFormat sf = new SimpleDateFormat(format);
            return sf.parse(date_string);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将一个时间毫秒数，转为指定形式的字符串
     *
     * @param dateLong 时间毫秒数
     * @param format   时间格式
     * @return 相应格式的时间
     */
    public static String secondMSToDateStr(Long dateLong, String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        Date date = new Date(dateLong);
        return sf.format(date);
    }

    /**
     * 将一个字符串形式的时间，转为时间毫秒数
     *
     * @param dateStr 字符串形式的时间
     * @param format  时间格式
     * @return 时间毫秒数
     */
    public static Long dateStrToSecondMS(String dateStr, String format) {
        try {
            SimpleDateFormat sf = new SimpleDateFormat(format);
            Date date = sf.parse(dateStr);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间添加或减少 几个月
     * calendarType : 年月日时分秒
     * example:Calendar.MONTH
     *
     * @param time         操作的时间
     * @param count        正数：+时间 负数：-时间
     * @param calendarType
     * @return
     */
    public static Date addDate(Date time, int count, int calendarType) {
        if (!CommonUtil.isEmpty(time) && !CommonUtil.isEmpty(count)) {
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(time);
            rightNow.add(calendarType, count);
            return rightNow.getTime();
        }
        return null;
    }

    /**
     * 获取当前时间的秒数
     *
     * @return
     */
    public static long getCurrentTimeS() {
        long currentTimeS = System.currentTimeMillis() / 1000;
        return currentTimeS;
    }

    /**
     * 获取当前时间的毫秒数
     *
     * @return
     */
    public static long getCurrentTimeMS() {
        long currentTimeMS = System.currentTimeMillis();
        return currentTimeMS;
    }

    /**
     * 获取以小时为最大单位的时间格式显示
     * HH:mm:ss
     *
     * @return
     */
    public static String getDiffTimeStr(Long diffS, String split) {
        if (CommonUtil.isEmpty(diffS)
                || CommonUtil.isEmpty(split)) {
            return null;
        }
        long diffSL = diffS.longValue();
        if (diffSL < 0L) {
            return null;
        }

        StringBuffer strBuf = new StringBuffer();
        //一小时
        long hh = 1 * 60 * 60L;
        //一分钟
        long mm = 1 * 60L;
        //一秒
//		long ss = 1L;
        //小时
        if (diffSL > hh) {
            long hour = diffSL / hh;
            diffSL = diffSL - hour * hh;
            strBuf.append(hour);
        } else {
            strBuf.append("00");
        }
        strBuf.append(split);
        //分钟
        if (diffSL > mm) {
            long mmL = diffSL / mm;
            diffSL = diffSL - mmL * mm;
            if (mmL < 10L) {
                strBuf.append("0");
            }
            strBuf.append(mmL);
        } else {
            strBuf.append("00");
        }
        strBuf.append(split);
        //秒数
        if (diffSL < 10L) {
            strBuf.append("0");
        }
        strBuf.append(diffSL);
        return strBuf.toString();
    }

    /**
     * 获取两个时间字符串的间隔天数，end - start
     *
     * @param startDateStr 起始时间字符串
     * @param endDateStr   截止时间字符串
     * @param format       时间格式
     * @return 间隔天数
     */
    public static Integer getDiffDayBetween(String startDateStr, String endDateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            long start = sdf.parse(startDateStr).getTime();
            long end = sdf.parse(endDateStr).getTime();
            return (int) ((end - start) / (24 * 60 * 60 * 1000));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取指定时间字符串距离此刻的间隔天数，now - date
     *
     * @param dateStr 给定的时间字符串
     * @param format  时间格式
     * @return 间隔天数
     */
    public static Integer getDiffDayUtilNow(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        long day = 0L;
        try {
            day = sdf.parse(dateStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long now = System.currentTimeMillis();
        return (int) ((now - day) / (24 * 60 * 60 * 1000));
    }

    /**
     * 获取指定时间字符串距离此刻的间隔天数，now - date
     *
     * @param time 给定的时间毫秒数
     * @return 间隔天数
     */
    public static Integer getDiffDayUtilNow(Long time) {
        long now = System.currentTimeMillis();
        return (int) ((now - time) / (24 * 60 * 60 * 1000));
    }

    /**
     * 获取指定天的结束时间
     *
     * @return
     */
    public static Date getDayEnd(Date date) {

        if (CommonUtil.isEmpty(date)) {
            return null;
        }

        Calendar todayEnd = Calendar.getInstance();
        todayEnd.setTime(date);
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }


    /**
     * 获取多少天前的起始时间
     *
     * @param days
     */
    public static Date getDaysStart(Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, (-1) * days);
        return calendar.getTime();
    }

    public static String getMonthAdd(int month, String date) {
        YearMonth yearMonth = YearMonth.parse(date);
        String accDate = yearMonth.minus(-month, ChronoUnit.MONTHS).toString();
        return accDate;
    }

    public static String getMon(String date) {

        String day = StringUtils.substring(date, 4);
        if (day.startsWith("0")) {
            return day.replace("0", "");
        } else {
            return day;
        }
    }

    /**
     * 获取 当年的月份集合
     * @return
     */
    public static List<Integer> getNowYearMonthList(Date minDate, Date maxDate) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(minDate);
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(maxDate);
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(Integer.parseInt(sdf.format(curr.getTime())));
            curr.add(Calendar.MONTH, 1);
        }
        return result;
    }

    /**
     * 判断时间是否为连续的
     * @return
     */
    public static boolean isSeriesMonthList(List<Integer> monthList) {
        if (monthList.size() == 1) {
            return true;
        }
        Integer minMonth = monthList.get(0);
        Integer maxMonth = monthList.get(monthList.size() - 1);

        Date minDate = strtoDate(String.valueOf(minMonth), "yyyyMM");
        Date maxDate = strtoDate(String.valueOf(maxMonth), "yyyyMM");
        List<Integer> monthRightList = getNowYearMonthList(minDate, maxDate);
        if(monthList.size() != monthRightList.size()){
            return false;
        }

        for (int i = 0; i < monthRightList.size(); i++) {
            Integer month = monthRightList.get(i);
            Integer targetMonth = monthList.get(i);
            if(month.intValue() != targetMonth.intValue()){
                return false;
            }
        }
        return true;
    }

    /**
     * 获取上个月
     * @param date 日期
     * @param format 格式化
     * @return
     */
    public static String getLastMonth(Date date, String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
        String lastMonth = dateToStr(cal.getTime(), format);
        return lastMonth;
    }

    public static void main(String[] args) {
//        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
//        List<Integer> list = new ArrayList<>();
//        list.add(201901);
//        list.add(201903);
//        System.out.println(isSeriesMonthList(list));
////        202003
//        List<Integer> integerList = getNowYearMonthList(strtoDate("202004", "yyyyMM"), strtoDate("202004", "yyyyMM"));
//        for (Integer integer : integerList) {
//            System.out.println(integer);
//        }
//
//
//        Integer diffDay = DateUtil.getDiffDayBetween("202001", "202005", "yyyyMM");
//        System.out.println(diffDay);
        int yyyyMM = getDiffMonthDate("201601", "201601", "yyyyMM");
        System.out.println(yyyyMM);
    }

}
