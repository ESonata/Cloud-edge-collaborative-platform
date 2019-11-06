package com.fuful.service;

import com.fuful.dao.UsersDAO;
import com.fuful.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersDAO usersDAO;

    public Users login(String uname,String upwd){
        Users users = usersDAO.findByUName(uname);
        return users;
    }
}
