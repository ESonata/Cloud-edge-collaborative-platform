package com.fuful.controllers.admin;

import com.fuful.domain.ManageUser;
import com.fuful.service.admin.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by SunRuiBin on 2019/11/7.
 */
@Controller
public class AdminUserCtrl {

    //tres
    @Autowired
    private AdminUserService adminUserService;


    @RequestMapping(value = "login.do",method = GET)
    public String test(){
        return "redirect:/index.jsp";
    }


    //管理员登录
    @RequestMapping(value="/admin/login",method=POST)
    public String AdminLogin(@RequestParam("uname") String uname,@RequestParam("password") String password,HttpServletRequest request){
        System.out.println(uname+"aaa"+password);
        HttpSession session=request.getSession();
        ManageUser user = adminUserService.login(uname,password);
//        return "redirect:/admin/index.jsp";
        if(user!=null){
            session.setAttribute("manageuser", user);
            return "redirect:/admin/index.jsp";
        }
        else
        {
            return "redirect:/admin/LoginAdmin.jsp";
        }
    }

}
