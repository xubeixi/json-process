package com.xubeixi.json.predefined.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * @author xubeixi
 * @date 2020-02-16 15:27
 * 日期格式化工具类
 */
public class DateFormatUtils {
    /**
     * 年-月-日 时:分:秒:毫秒 最全版
     */
    public final static String DEFAULT_PATTERN_DATETIME_FULL = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 年-月-日 时:分:秒:毫秒 中文版
     */
    public final static String DEFAULT_PATTERN_DATETIME_FULL_CHINESE = "yyyy年MM月dd日 HH:mm:ss.SSS";

    /**
     * 年-月-日 时:分:秒:毫秒 中文版全
     */
    public final static String DEFAULT_PATTERN_DATETIME_CHINESE_FULL = "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒";

    /**
     * 年-月-日 时:分:秒 标准样式
     */
    public final static String DEFAULT_PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 年-月-日 时:分:秒 中文版1
     */
    public final static String DEFAULT_PATTERN_DATETIME_CHINESE_ONE = "yyyy年MM月dd日 HH:mm:ss";

    /**
     * 年-月-日 时:分:秒 中文版2
     */
    public final static String DEFAULT_PATTERN_DATETIME_CHINESE_TWO = "yyyy年MM月dd日 HH时mm分ss秒";

    /**
     * 年-月-日
     */
    public final static String DEFAULT_PATTERN_DATE = "yyyy-MM-dd";

    /**
     * 年-月-日 中文版
     */
    public final static String DEFAULT_PATTERN_DATE_CHINESE = "yyyy年MM月dd日";

    /**
     * 年-月 中文版
     */
    public final static String DEFAULT_PATTERN_DATE_YEAR_MONTH_CHINESE = "yyyy年MM月";

    /**
     * 月-日
     */
    public final static String DEFAULT_PATTERN_MONTH = "MM-dd";

    /**
     * 日
     */
    public final static String DEFAULT_PATTERN_DAY = "dd";

    /**
     * 时:分:秒
     */
    public final static String DEFAULT_PATTERN_TIME = "HH:mm:ss";

    /**
     * 年月日时分秒毫秒  紧凑版
     */
    public final static String DEFAULT_PATTERN_DATETIME_SIMPLE_FULL = "yyyyMMddHHmmssSSS";

    /**
     * 年月日时分秒
     */
    public final static String DEFAULT_PATTERN_DATETIME_SIMPLE = "yyyyMMddHHmmss";

    /**
     * 年月日
     */
    public final static String DEFAULT_PATTERN_DATETIME_DATE = "yyyyMMdd";

    /**
     * 月日
     */
    public final static String DEFAULT_PATTERN_DATETIME_MONTH = "MMdd";

    /**
     * 时分秒毫秒
     */
    public final static String DEFAULT_PATTERN_DATETIME_TIME_FULL = "HHmmss";

    /**
     * 格式化年月日为字符串
     *
     * @param localDate 传入需要格式化的日期
     * @param pattern   需要格式化的格式
     * @return String 返回格式化的日期
     */
    public static String formatterLocalDateToString(LocalDate localDate, String pattern) {

        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(pattern);

        return getLocalDateFormat(localDate, dateTimeFormatter);

    }

    /**
     * 格式化年月日时分秒为字符串
     *
     * @param localDateTime 传入需要格式化的日期
     * @param pattern       需要格式化的格式
     * @return String 返回格式化的日期
     */
    public static String formatterLocalDateTimeToString(LocalDateTime localDateTime, String pattern) {

        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(pattern);

        return getLocalDateTimeFormat(localDateTime, dateTimeFormatter);

    }


    /**
     * 时区格式化年月日为字符串
     *
     * @param localDate 传入需要格式化的日期
     * @param pattern   需要格式化的格式
     * @param locale    国际时区   Locale.CHINA
     * @return String 返回格式化的日期
     */
    public static String formatterLocalDateToString(LocalDate localDate,
                                                    String pattern,
                                                    Locale locale
    ) {

        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(pattern, locale);

        return getLocalDateFormat(localDate, dateTimeFormatter);

    }

    /**
     * 时区格式化年月日时分秒为字符串
     *
     * @param localDateTime 传入需要格式化的日期
     * @param pattern       需要格式化的格式
     * @param locale        国际时区 Locale.CHINA
     * @return String 返回格式化的日期
     */
    public static String formatterLocalDateTimeToString(LocalDateTime localDateTime,
                                                        String pattern,
                                                        Locale locale
    ) {

        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(pattern, locale);

        return getLocalDateTimeFormat(localDateTime, dateTimeFormatter);

    }


    /**
     * LocalDate格式化日期为日期格式
     *
     * @param localDate 传入需要格式化的日期
     * @param pattern   需要格式化的格式
     * @return String 返回格式化的日期
     */
    public static LocalDate formatterStringToLocalDate(String localDate, String pattern) {

        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(pattern);

        return getLocalDateParse(localDate, dateTimeFormatter);

    }

    /**
     * localDateTime格式化日期为日期格式
     *
     * @param localDateTime 传入需要格式化的日期
     * @param pattern       需要格式化的格式
     * @return String 返回格式化的日期
     */
    public static LocalDateTime formatterStringToLocalDateTime(String localDateTime, String pattern) {

        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(pattern);
        return getLocalDateTimeParse(localDateTime, dateTimeFormatter);

    }


    /**
     * 日期格式化日期，转化为日期格式 localDate 转 LocalDate
     *
     * @param localDate 传入的日期
     * @param pattern   转化的格式
     * @return 返回结果 LocalDate
     */
    public static LocalDate formatterDateToLocalDateTime(LocalDate localDate,
                                                         String pattern) {

        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(pattern);
        String formatterDateTime = getLocalDateFormat(localDate, dateTimeFormatter);
        return getLocalDateParse(formatterDateTime);

    }

    /**
     * 日期格式化日期，转化为日期格式 localDateTime 转 localDateTime
     *
     * @param localDateTime 传入需要格式化的日期
     * @param pattern       需要格式化的格式
     * @return String 返回格式化的日期
     */
    public static LocalDateTime formatterDateTimeToLocalDateTime(LocalDateTime localDateTime,
                                                                 String pattern) {

        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(pattern);

        String formatterDateTime = getLocalDateTimeString(localDateTime, dateTimeFormatter);

        return getLocalDateTimeParse(formatterDateTime, dateTimeFormatter);

    }

    /**
     * 格式化日期格式 yyyy-MM-dd HH:mm:ss
     *
     * @param localDateTime 传入需要格式化的日期
     * @return 返回格式化后的日期字符串
     */
    public static String getAllFormatterLocalDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(DEFAULT_PATTERN_DATETIME);
        return getLocalDateTimeFormat(localDateTime, dateTimeFormatter);
    }


    /**
     * 获取LocalDate转化为字符串
     *
     * @param formatterDateTime 需要转化的数据
     * @return LocalDate
     */
    private static LocalDate getLocalDateParse(String formatterDateTime) {
        return LocalDate.parse(formatterDateTime);
    }

    /**
     * 获取LocalDate按要求转化为字符串
     *
     * @param formatterDateTime 需要转化的数据
     * @param dateTimeFormatter 格式化
     * @return LocalDate
     */
    private static LocalDate getLocalDateParse(String formatterDateTime, DateTimeFormatter dateTimeFormatter) {
        return LocalDate.parse(formatterDateTime, dateTimeFormatter);
    }

    /**
     * 获取LocalDate按照要求转化为字符串
     *
     * @param localDate         需要转化的数据
     * @param dateTimeFormatter 转化的格式
     * @return 转化后返回字符串
     */
    private static String getLocalDateFormat(LocalDate localDate, DateTimeFormatter dateTimeFormatter) {
        return dateTimeFormatter.format(localDate);
    }

    /**
     * 获取LocalDateTime按照要求转化为字符串
     *
     * @param localDateTime     需要转化的数据
     * @param dateTimeFormatter 转化的格式
     * @return 返回结果
     */
    private static String getLocalDateTimeFormat(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 获取数据按照国际标准转化的值
     *
     * @param pattern 需要转化的数据
     * @param locale  传入国际时间
     * @return 返回格式结果
     */
    private static DateTimeFormatter getDateTimeFormatter(String pattern, Locale locale) {
        return DateTimeFormatter.ofPattern(pattern, locale);
    }

    /**
     * 获取localDateTime按照国际标准转化的值
     *
     * @param localDateTime     需要转化的数据
     * @param dateTimeFormatter 格式化
     * @return 返回 LocalDateTime
     */
    private static LocalDateTime getLocalDateTimeParse(String localDateTime, DateTimeFormatter dateTimeFormatter) {
        return LocalDateTime.parse(localDateTime, dateTimeFormatter);
    }

    /**
     * 获取格式化的结果
     *
     * @param pattern 传入的格式
     * @return 返回格式化结果
     */
    public static DateTimeFormatter getDateTimeFormatter(String pattern) {
        return DateTimeFormatter.ofPattern(pattern);
    }

    /**
     * 获取格式化LocalDateTime结果
     *
     * @param localDateTime     传入的数据
     * @param dateTimeFormatter 格式化的结果
     * @return 返回字符串
     */
    private static String getLocalDateTimeString(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
        return dateTimeFormatter.format(localDateTime);
    }

    /**
     * LocalDateTime 转化为 LocalDate
     *
     * @param localDateTime 输入的时间
     * @return LocalDate的日期类型
     */
    public static LocalDate localDateTimeToLocalDate(LocalDateTime localDateTime) {

        return localDateTime.toLocalDate();
    }

    /**
     * LocalDate 转化为 LocalDateTime
     *
     * @param localDate 输入的时间
     * @return LocalDateTime的日期类型
     */
    public static LocalDateTime localDateToLocalDateTime(LocalDate localDate) {

        return localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * LocalTime 转化为 LocalDateTime
     *
     * @param localTime 输入的时间
     * @return LocalDateTime的日期类型
     */
    public static LocalDateTime localTimeToLocalDateTime(LocalTime localTime) {

        return LocalDateTime.of(LocalDate.now(), localTime);
    }

    /**
     * 获取instant转化的日期格式
     *
     * @param instant JDK8中代替Date使用的类
     * @return 时间格式
     */
    private static LocalDateTime getLocalDateAboutInstant(Instant instant) {
        return instantToLocalDateTime(instant);
    }


    /**
     * LocalDateTime 转化为 Instant
     *
     * @param localDateTime 输入的时间
     * @return Instant的日期类型
     */
    public static Instant localDateTimeToInstant(LocalDateTime localDateTime) {

        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }

    /**
     * instant  转化为 LocalDateTime
     *
     * @param instant 输入的时间
     * @return LocalDateTime的日期类型
     */
    public static LocalDateTime instantToLocalDateTime(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * instant  转化为 LocalDate
     *
     * @param instant 输入的时间
     * @return LocalDate的日期类型
     */
    public static LocalDate instantToLocalDate(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * instant  转化为 LocalTime
     *
     * @param instant 输入的时间
     * @return LocalDate的日期类型
     */
    public static LocalTime instantToLocalTime(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
    }

    /**
     * Date 转化为 LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return instantToLocalDateTime(date.toInstant());
    }

    /**
     * Date 转化为 LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        return instantToLocalDate(date.toInstant());
    }

    /**
     * Date 转化为 LocalTime
     *
     * @param date
     * @return
     */
    public static LocalTime dateToLocalTime(Date date) {
        return instantToLocalTime(date.toInstant());
    }

    /**
     * LocalDateTime 转化为 Date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        Instant instant = localDateTimeToInstant(localDateTime);
        return Date.from(instant);
    }


    /**
     * LocalDate 转化为 Date
     *
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate) {
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * LocalTime 转化为 Date
     *
     * @param localTime
     * @return
     */
    public static Date localTimeToDate(LocalTime localTime) {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), localTime);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * 当前时间10位毫秒数
     *
     * @return 10位秒
     */
    public static long getNowOfSecond() {

        LocalDateTime localDateTime = LocalDateTime.now();

        return localDateTimeToInstant(localDateTime).getEpochSecond();
    }


    /**
     * 当前时间13位毫秒数
     *
     * @return 13位毫秒数
     */
    public static long getNowOfMillion() {

        LocalDateTime localDateTime = LocalDateTime.now();

        return localDateTimeToInstant(localDateTime).toEpochMilli();
    }

    /**
     * 毫秒值转LocalDate
     *
     * @param timeStamp 时间戳毫秒值
     * @return LocalDate
     */
    public static LocalDate getMillisToLocalDate(long timeStamp) {

        return Instant.ofEpochMilli(timeStamp).atZone(ZoneId.systemDefault()).toLocalDate();

    }

    /**
     * 毫秒值转LocalDateTime
     *
     * @param timeStamp 时间戳毫秒值
     * @return LocalDateTime
     */
    public static LocalDateTime getMillisToLocalDateTime(long timeStamp) {

        return Instant.ofEpochMilli(timeStamp).atZone(ZoneId.systemDefault()).toLocalDateTime();

    }

    /**
     * LocalDate转时间戳
     *
     * @param localDate 时间输入
     * @return 时间戳
     */
    public static Long getLocalDateToMillis(LocalDate localDate) {

        return localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

    }

    /**
     * LocalDateTime转时间戳
     *
     * @param localDateTime 时间输入
     * @return 时间戳
     */
    public static Long getLocalDateTimeToMillis(LocalDateTime localDateTime) {

        return localDateTimeToInstant(localDateTime).toEpochMilli();

    }
}
