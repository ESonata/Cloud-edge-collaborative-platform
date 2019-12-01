package com.fuful.controllers;

import com.fuful.domain.Book;
import com.fuful.domain.Mark;
import com.fuful.domain.User;
import com.fuful.service.BookService;
import com.fuful.service.MarkService;
import com.fuful.utils.CommonsUtils;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by SunRuiBin on 2019/11/16.
 */
@Controller
public class MarkCtrls {
    @Autowired
    BookService bookService;

    @Autowired
    MarkService markService;

    @RequestMapping(value = "/MyMarkServlet",method = GET)
    public void mymarkServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();

        User user=(User)session.getAttribute("user");

        if(user==null)
        {
            response.sendRedirect("/front/login.jsp");
            return ;
        }


        int page;
        if(request.getParameter("curPage")==null)
        {
            page=1;
        }
        else
        {
            page=Integer.parseInt(request.getParameter("curPage"));
        }
        PageInfo<Mark> markList=bookService.getMyMarkList(page,7,user.getUsername());

        List<Mark> marklist=markList.getList();
        List<Mark> new_markList= new ArrayList<>();
        List<Book> bookList=new ArrayList<>();
        for(Mark p:marklist){
            Book book=bookService.findBookInfoById(p.getBid());
            p.setBookInfo(book);
            new_markList.add(p);
        }
        session.setAttribute("spliteMyMarklist",new_markList);
        session.setAttribute("curPage", markList.getPageNum());
        session.setAttribute("totalPage", markList.getPages());
        session.setAttribute("totalCount", markList.getTotal());
        session.setAttribute("bookList", bookList);

        response.sendRedirect("/front/my_mark.jsp");
    }

    @RequestMapping(value = "/DeleteMark",method = POST)
    public void deleteMark(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String mid=request.getParameter("mid");

        Map<String,Object> res=new HashMap<>();

        boolean msg=bookService.deletemark(mid);
        res.put("msg",msg);
        res.put("mid",mid);
        String message="";
        if(msg)
        {
            message="恭喜您:删除成功!";
        }
        else
        {
            message="恭喜您:删除失败!";
        }

        Gson gson=new Gson();
        String json=gson.toJson(message);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);


    }

    @RequestMapping(value="/AddMark",method = POST)
    public void addMark (HttpServletRequest request,HttpServletResponse response) throws IOException {
        String bid=request.getParameter("bid");
        String bname=request.getParameter("bname");
        String comment=request.getParameter("expressmark");
        System.out.println(bid+bname+comment);


        User user=(User)request.getSession().getAttribute("user");


        Mark markadd=new Mark();

        markadd.setMid(CommonsUtils.getUUID());
        markadd.setUid(user.getUid());
        markadd.setUname(user.getUsername());
        markadd.setBid(Integer.parseInt(bid));
        markadd.setBname(bname);
        markadd.setMarktime(new java.sql.Date(new java.util.Date().getTime()));
        markadd.setComment(comment);




        boolean msg=markService.saveMark(markadd);
        String message="";
        if(msg)
        {
            message="恭喜您:发表评论成功!";
        }
        else
        {
            message="恭喜您:发表评论失败!";
        }
        Gson gson=new Gson();
        String json=gson.toJson(message);

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);


    }
}
