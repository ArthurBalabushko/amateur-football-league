package com.itacademy.service.formatter;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.itacademy.service.util.StringUtils.isNotEmpty;

@Component
public class PhoneFormat {

    public String format(String phone) {
        String result = null;
        String regexp = "(\\+?375|80) ?\\(?(25|29|33|44|17)\\)?\\s?(\\d{3})-?(\\d{2})-?(\\d{2})";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(phone);
        if (matcher.find()) {
            result = String.format("+375 (%s) %s-%s-%s", matcher.group(2), matcher.group(3), matcher.group(4), matcher.group(5));
        }
        return isNotEmpty(phone)
                ? result
                : null;
    }
}
