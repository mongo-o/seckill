package com.ayl.seckil.util;


import org.springframework.util.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author AYL    2018/9/3 11:44
 */
public class ValidatorUtil {

    public static boolean isMobilePhone(String value) {
        String regex = "1[3|5|8|7]\\d{9,9}";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public static void main(String[] args) {
        String mobilephone = "13548484848";
        System.out.println(isMobilePhone(mobilephone));
    }
}
