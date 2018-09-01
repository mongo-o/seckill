package com.ayl.seckil.service;

import com.ayl.seckil.dao.SeckillUserDao;
import com.ayl.seckil.domain.SeckillUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author AYL    2018/8/15 12:53
 */
@Service
public class SeckillUserService {
    @Autowired
    private SeckillUserDao seckillUserDao;

    public SeckillUser getSeckillUserByPhone(Long phone) {
        return seckillUserDao.getUserByPhone(phone);
    }

}
