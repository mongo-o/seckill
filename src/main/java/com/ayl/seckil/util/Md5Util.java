package com.ayl.seckil.util;


import org.apache.commons.codec.digest.Md5Crypt;

/**
 * @author AYL    2018/8/14 23:06
 */
public class Md5Util {
    private static final String FORM_SALT = "12345678QWERTYUIOPLKJHGFDSAZXCVBNM";

    private static String encode(String input, String salt) {
        return Md5Crypt.md5Crypt(input.getBytes(), salt);
    }

    public static String formPasswordToDbPassword(String formPassword, String dbSalt) {
        return encode(formPassword, dbSalt);
    }

    public static String inputToFormPassword(String input) {
        String salt = "" + FORM_SALT.charAt(0) + FORM_SALT.charAt(1) + FORM_SALT.charAt(3) + FORM_SALT.charAt(5)
                + FORM_SALT.charAt(7) + FORM_SALT.charAt(9);

        return encode(input, salt);
    }


}
