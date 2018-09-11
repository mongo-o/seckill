package com.ayl.seckil.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ayl.seckil.domain.SeckillUser;
import com.ayl.seckil.service.SeckillUserService;
import com.ayl.seckil.vo.UserLoginVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author AYL    2018/9/7 15:33
 */
public class UserUtil {

    static Long PHONE = 13510000000L;
    static String FORM_PASSWORD = Md5Util.inputToFormPassword("123456");

    private static SeckillUserService seckillUserService = new SeckillUserService();

    public static void generate5000RandomUsers() {
        String salt = "dbSalt";
        for(int i = 0; i < 5000; i++) {
            SeckillUser user = new SeckillUser();
            user.setPhone(PHONE + i);
            user.setHeadImg("head_img_" + i);
            user.setNickName("nickname_" + i);
            String dbSalt = salt + i;
            user.setSalt(dbSalt);
            String dbPassword = Md5Util.formPasswordToDbPassword(FORM_PASSWORD, dbSalt);
            user.setPassword(dbPassword);
            seckillUserService.insertUser(user);
        }
    }

    public static void generateUserToken() throws Exception {
        File file = new File("D:\\git\\demo\\seckil_version2\\token.txt");
        if (file.exists()) {
            file.delete();
        }

        String urlString = "http://127.0.0.1:8080/login/do_login";
        for(int i = 0; i< 5000; i++) {
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            OutputStream out = httpURLConnection.getOutputStream();
            UserLoginVo userLoginVo = new UserLoginVo();
            Long phone = PHONE + i;
            userLoginVo.setFormPassword(FORM_PASSWORD);
            userLoginVo.setPhone(phone.toString());
            String param = JSON.toJSONString(userLoginVo);
            out.write(param.getBytes());
            out.flush();
            InputStream inputStream = httpURLConnection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int len = 0;
            while ( (len = inputStream.read(buff)) != -1) {
                byteArrayOutputStream.write(buff,0, len);
            }
            inputStream.close();
            byteArrayOutputStream.close();
            String response = new String(byteArrayOutputStream.toByteArray());
            JSONObject jsonObject = JSON.parseObject(response);
            String token = jsonObject.getString("data");
            String userToken = phone + "," + token + "\r\n";

            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.write(userToken.getBytes());
        }
    }

    public static void main(String[] args) {
        try {
            generateUserToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
