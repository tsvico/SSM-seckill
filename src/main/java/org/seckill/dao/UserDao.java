package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.User;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/11/7 9:57
 * 功能
 */
public interface UserDao {
    User checkUser(@Param("username") String username,@Param("password") String password);
}
