package com.xubeixi.json.predefined.date;

import com.xubeixi.json.process.ProcessValue;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author xubeixi
 * @date 2021-04-27 10:25
 */
public class DateProcessValue implements ProcessValue<DateProcess> {
    @Override
    public boolean processCondition(Object value) {
        //只处理日期类型
        return value instanceof String ||
                value instanceof Date ||
                value instanceof LocalDateTime ||
                value instanceof LocalDate ||
                value instanceof LocalTime;
    }

    @Override
    public Object processLogic(Object value, DateProcess dateProcess) {
        LocalDateTime localDateTime;

        if (value instanceof String) {
            if (dateProcess.isTimeStamp()) {
                //是时间戳
                localDateTime = DateFormatUtils.getMillisToLocalDateTime(Long.valueOf((String) value));
            } else {
                //是日期字符串
                String parse = dateProcess.parse();
                if (StringUtils.isEmpty(parse)) {
                    throw new RuntimeException("the date parsing format of the String type cannot be empty");
                }

                //这里先把String解析成Date，因为LocalDateTime对"yyyy-MM-dd"等格式的DateTimeFormatter不支持
                try {
                    Date date = new SimpleDateFormat(parse).parse((String) value);
                    localDateTime = DateFormatUtils.dateToLocalDateTime(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return value;
                }
            }
        } else if (value instanceof Date) {
            localDateTime = DateFormatUtils.dateToLocalDateTime((Date) value);
        } else if (value instanceof LocalDateTime) {
            localDateTime = (LocalDateTime) value;
        } else if (value instanceof LocalDate) {
            //松散化，格式化规则对不上类型的也能格式化
            localDateTime = DateFormatUtils.localDateToLocalDateTime((LocalDate) value);
        } else {
            localDateTime = DateFormatUtils.localTimeToLocalDateTime((LocalTime) value);
        }

        String format = dateProcess.format();
        //设置format的缺省格式
        if (StringUtils.isEmpty(format)) {
            if (value instanceof LocalDate) {
                format = DateFormatUtils.DEFAULT_PATTERN_DATE;
            } else if (value instanceof LocalTime) {
                format = DateFormatUtils.DEFAULT_PATTERN_TIME;
            } else {
                format = DateFormatUtils.DEFAULT_PATTERN_DATETIME;
            }
        }

        value = DateFormatUtils.formatterLocalDateTimeToString(localDateTime, format);
        return value;
    }
}
