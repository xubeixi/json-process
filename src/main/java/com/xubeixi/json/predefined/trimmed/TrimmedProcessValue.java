package com.xubeixi.json.predefined.trimmed;

import com.xubeixi.json.process.ProcessValue;

import java.util.regex.Pattern;

/**
 * @author xubeixi
 * @date 2021-04-27 13:06
 */
public class TrimmedProcessValue implements ProcessValue<TrimmedProcess> {
    private static final Pattern PATTERN_WHITESPACES = Pattern.compile("\\s+");
    private static final Pattern PATTERN_WHITESPACES_WITH_LINE_BREAK = Pattern.compile("\\s*\\n\\s*");
    private static final Pattern PATTERN_WHITESPACES_EXCEPT_LINE_BREAK = Pattern.compile("[\\s&&[^\\n]]+");

    @Override
    public boolean processCondition(Object value) {
        return value instanceof String;
    }

    @Override
    public Object processLogic(Object value, TrimmedProcess annotation) {
        String result = value.toString().trim();
        switch (annotation.value()) {
            case ALL_WHITESPACES:
                return PATTERN_WHITESPACES
                        .matcher(result).replaceAll("");
            case EXCEPT_LINE_BREAK:
                return PATTERN_WHITESPACES_EXCEPT_LINE_BREAK
                        .matcher(PATTERN_WHITESPACES_WITH_LINE_BREAK.matcher(result).replaceAll("\n")).replaceAll("");
            case SIMPLE:
            default:
                return result;
        }
    }
}
