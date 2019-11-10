package com.fuful.controllers.front;


import com.fuful.domain.Users;
import com.fuful.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class UesrsCtrls {
    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "test.do",method = GET)
    public String test(){
        Users users = usersService.login("xiaomin","123456");
        System.out.println(users.getUid());
        return "redirect:/index.jsp";
    }


}
