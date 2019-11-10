package com.fuful.service.admin;

import com.fuful.dao.UsersDAO;
import com.fuful.dao.admin.UserDao;
import com.fuful.domain.Users;
import com.fuful.domain.admin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by SunRuiBin on 2019/11/7.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User login(String uname, String upwd){
        User users = userDao.findUser(uname,upwd);
        return users;
    }
}
