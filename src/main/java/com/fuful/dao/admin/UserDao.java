package com.fuful.dao.admin;

import com.fuful.domain.Users;
import com.fuful.domain.admin.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by SunRuiBin on 2019/11/7.
 */
public interface UserDao {

    public User findUser(@Param("uname") String uname,@Param("password") String password);

}
