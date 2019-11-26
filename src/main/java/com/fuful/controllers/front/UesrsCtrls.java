package com.fuful.controllers.front;


import com.fuful.domain.Users;
import com.fuful.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
    @RequestMapping(value = "/FLoginServlet", method = POST)
    public String test(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        String checkcode = request.getParameter("checkCode");
        String checkcode_session = (String) session.getAttribute("checkcode_session");

        if (checkcode.equals(checkcode_session)) {
            Users msg = usersService.login(username, password);
            if (msg != null) {
                session.setAttribute("user", msg);
                return "redirect:/front/index.jsp";
            } else {
                return "redirect:/front/login.jsp";
            }
        } else {
            return "redirect:/front/login.jsp";
        }
    }


}
