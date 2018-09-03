package com.ayl.seckil.util;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;

/**
 * @author AYL    2018/8/14 23:06
 */
public class Md5Util {
    private static final String FORM_SALT = "1a2b3c4d5e";

    private static String encode(String input) {
        return DigestUtils.md5Hex(input);
    }

    public static String formPasswordToDbPassword(String formPassword, String dbSalt) {
        formPassword = "" + dbSalt.charAt(0) + dbSalt.charAt(5) + formPassword + dbSalt.charAt(2) + dbSalt.charAt(4);
        return encode(formPassword);
    }

    public static String inputToFormPassword(String input) {
        input = "" + FORM_SALT.charAt(3) + FORM_SALT.charAt(6) + input + FORM_SALT.charAt(0) + FORM_SALT.charAt(5);
        return encode(input);
    }


    public static void main(String[] args) {
        String mongo  = "mongo";
        String formPassword = Md5Util.inputToFormPassword(mongo);
        System.out.println(formPassword);//f12861d57082db6ee4d5b4eb9eaf7bd9
        System.out.println(formPasswordToDbPassword(formPassword, "123654987mongo")); //3db27c33d52eac5cfaaa25dd9455486a
    }




}
