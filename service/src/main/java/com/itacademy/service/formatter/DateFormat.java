package com.itacademy.service.formatter;

import com.itacademy.service.util.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Pattern;

import static com.itacademy.service.util.StringUtils.isNotEmpty;

@Component
public class DateFormat {

    private static final String PATTERN_YMD = "yyyy-MM-dd";
    private static final String PATTERN_DMY = "dd MMMM yyyy";
    private static final DateTimeFormatter FORMATTER_YMD = DateTimeFormatter.ofPattern(PATTERN_YMD);
    private static final DateTimeFormatter FORMATTER_DMY = DateTimeFormatter.ofPattern(PATTERN_DMY);

    public String format(LocalDate localDate) {
        return Objects.nonNull(localDate)
                ? localDate.format(FORMATTER_DMY)
                : StringUtils.EMPTY;
    }

    public LocalDate format(String value) {
        LocalDate result = null;
        String regexYmd = "([0-9]{4})-([0-9]{2})-([0-9]{2})";
        String regexDmy = "([0-9]{2}) ([а-я]*) ([0-9]{4})";
        if (Pattern.matches(regexYmd, value)) {
            result = LocalDate.parse(value, FORMATTER_YMD);
        } else if (Pattern.matches(regexDmy, value)) {
            result = LocalDate.parse(value, FORMATTER_DMY);
        }
        return isNotEmpty(value)
                ? result
                : null;
    }
}
