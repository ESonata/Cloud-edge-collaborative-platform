package com.fuful.controllers.admin;

import com.fuful.dao.admin.AOrderDao;
import com.fuful.domain.Order;
import com.fuful.service.admin.AOrderService;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by SunRuiBin on 2019/11/17.
 */
@Controller
public class AOderCtrls {
    @Autowired
    AOrderService aOrderService;

    @RequestMapping(value = "/FindAllordersServlet",method = GET)
    public void FindAllordersServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int page;
        if(request.getParameter("curPage")==null)
        {
            page=1;
        }
        else
        {
            page=Integer.parseInt(request.getParameter("curPage"));
        }
        PageInfo<Order> orderList=aOrderService.findAllOrdersNoById(page,7);



        HttpSession session = request.getSession();

        session.setAttribute("spliteOrderInfoplist", orderList.getList());
        session.setAttribute("curPage", orderList.getPageNum());
        session.setAttribute("totalPage", orderList.getPages());
        session.setAttribute("totalCount", orderList.getTotal());

        session.removeAttribute("ordersearchflag");
        session.removeAttribute("currentkeyword");

        response.sendRedirect("/admin/operate-order.jsp");
    }

    @RequestMapping(value = "/FindOrderInfoByOidServlet",method = POST)
    public void findOrderInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String oid=request.getParameter("oid");


        System.out.println("找到每个订单的订单项信息oid"+oid);
        List<Map<String,Object>>  mapList=aOrderService.findOrderInfoByOid(oid);

        Gson gson=new Gson();
        String json=gson.toJson(mapList);
        System.out.println(json);



        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }


    @RequestMapping(value = "/DeleteOrderInfoServlet",method = POST)
    public void DeleteOrderInfoServlet(HttpServletResponse response,HttpServletRequest request) throws IOException {
        String oid=request.getParameter("oid");
        System.out.println("删除呃订单oid："+oid);


        boolean msg= aOrderService.deleteOrderByOid(oid);
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


}
