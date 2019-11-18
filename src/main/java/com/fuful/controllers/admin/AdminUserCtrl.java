package com.fuful.controllers.admin;

import com.fuful.domain.Users;
import com.fuful.domain.admin.User;
import com.fuful.service.UsersService;
import com.fuful.service.admin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by SunRuiBin on 2019/11/7.
 */
@Controller
public class AdminUserCtrl {

    //tres
    @Autowired
    private UserService userService;

    @RequestMapping(value = "login.do",method = GET)
    public String test(){
        return "redirect:/admin/LoginAdmin.jsp";
    }


    @RequestMapping(value="/admin/login",method=GET)
    public String test(@RequestParam("uname") String uname,@RequestParam("password") String password){
        System.out.println(uname+"aaa"+password);

        User user = userService.login(uname,password);


        return "redirect:/admin/index.jsp";
//        if(user!=null){
//            return "redirect:/admin/index.jsp";
//        }
//        else
//        {
//            return "redirect:/admin/LoginAdmin.jsp";
//        }
    }

}
