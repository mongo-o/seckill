package com.ayl.seckil.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author AYL    2018/8/15 0:04
 */
@Getter
@Setter
@ToString(callSuper = true)
public class SeckillUser {
    private Long id;
    private Long phone;
    private String nickName;
    private String password;
    private String salt;
    private String headImg;
    private Date registerDate;
    private Date lastLoginDate;
    private int login_count;
}
