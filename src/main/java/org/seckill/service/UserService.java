package org.seckill.service;

import org.seckill.entity.User;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/11/7 10:01
 * 功能
 */
public interface UserService {
    User checkUser(String username,String password);
}
