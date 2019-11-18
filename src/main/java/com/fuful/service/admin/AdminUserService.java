package com.fuful.service.admin;

import com.fuful.dao.admin.AdminUserDao;
import com.fuful.domain.ManageUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by SunRuiBin on 2019/11/17.
 */
@Service
public class AdminUserService {
    @Autowired
    AdminUserDao adminUserDao;

    public ManageUser login(String uname,String password){
        ManageUser user=adminUserDao.findUserInfo(uname,password);
        return user;
    }
}
