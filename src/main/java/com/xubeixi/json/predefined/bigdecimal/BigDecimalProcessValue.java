package com.xubeixi.json.predefined.bigdecimal;

import com.xubeixi.json.process.ProcessValue;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author xubeixi
 * @date 2021-04-27 12:23
 */
public class BigDecimalProcessValue implements ProcessValue<BigDecimalProcess> {
    @Override
    public boolean processCondition(Object value) {
        return value instanceof BigDecimal;
    }

    @Override
    public Object processLogic(Object value, BigDecimalProcess bigDecimalProcess) {
        if (StringUtils.isNotEmpty(bigDecimalProcess.format())) {
            DecimalFormat decimalFormat = new DecimalFormat(bigDecimalProcess.format());
            value = decimalFormat.format(value);
        } else {
            value = ((BigDecimal) value).setScale(bigDecimalProcess.scale(), bigDecimalProcess.roundingMode()).toString();
        }
        return value;
    }
}
