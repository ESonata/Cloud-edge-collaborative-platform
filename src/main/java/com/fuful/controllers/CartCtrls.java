package com.fuful.controllers;

import com.fuful.domain.*;
import com.fuful.service.TicketService;
import com.fuful.service.OrderService;
import com.fuful.utils.CommonsUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by SunRuiBin on 2019/11/14.
 */
@Controller
public class .CartCtrls {


    @Autowired
    TicketService ticketService;


    @Autowired
    OrderService orderService;
    @RequestMapping(value = "/AddProductToCart",method = GET)
    public String addCart( HttpServletRequest request, HttpServletResponse response) throws IOException {

        String pid=request.getParameter("pid");
        int buyNum=Integer.parseInt(request.getParameter("buyNum"));
        String location=request.getParameter("location");
        String round=request.getParameter("round");
        Double price=Double.parseDouble(request.getParameter("price"));
        int roundID=Integer.parseInt(request.getParameter("roundID"));
        Ticket product= ticketService.findTicketInfoById(Integer.parseInt(pid));

        double subtotal=price*buyNum;


        String seat=request.getParameter("seatlog");



        CartItem item=new CartItem();
        item.setProduct(product);
        item.setBuyNum(buyNum);
        item.setSubtotal(subtotal);
        item.setLocation(location);
        item.setRound(round);
        item.setSeat(seat);
        item.setRoundID(roundID);

//			获得购物车 -判断是否存在购物车
        HttpSession session=request.getSession();

        Cart cart=(Cart) session.getAttribute("cart");

        if(cart==null)
        {
            cart=new Cart();
        }

//			将购物项放到车中

        System.out.println("TIMETIME="+location+" "+round+" "+pid);
        List<Round> roundList=ticketService.getPriceByLRT(pid,location,round);

        String round_id=String.valueOf(roundList.get(0).getId());
//
        System.out.println("round_id="+round_id);
//			如果购物车中已经存在该商品，合并
        Map<String,CartItem> cartItems=cart.getCartItems();
        round_id=round_id+request.getParameter("price");
        item.setRound_id(round_id);
        double  newsubtotal=0.0;
        if(cartItems.containsKey(round_id))
        {
            //取出原有的商品的数据进行相加
            CartItem carItem=cartItems.get(round_id);
            int oldbuyNum=carItem.getBuyNum();
            oldbuyNum+=buyNum;
            carItem.setBuyNum(oldbuyNum);
            cart.setCartItems(cartItems);
//				修改小记
            double oldsubtotal=carItem.getSubtotal();
            //新买的商品的小记
            newsubtotal=buyNum*price;
            carItem.setSubtotal(newsubtotal+oldsubtotal);

        }

//			如果没有，就直接扔进鼓舞车
        else
        {
            cart.getCartItems().put(round_id, item);
            newsubtotal=buyNum*price;;
        }


//			计算总计
        double total=cart.getTotal()+newsubtotal;
        cart.setTotal(total);
//			将车再次访问session
        session.setAttribute("cart", cart);
        return "redirect:/front/cart.jsp";
    }


    @RequestMapping(value = "/ProductSubmitOrder",method = GET)
    public String submitorder(HttpServletRequest request,HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        if(user==null)
        {
//            response.sendRedirect(request.getContextPath()+"/front/login.jsp");
            return "redirect:/front/login.jsp";
        }

        //目的：封装好一个Order对象 传递给service层


        Order order = new Order();

        //1、private String oid;//该订单的订单号
        String oid = CommonsUtils.getUUID();
        order.setOid(oid);
        session.setAttribute("oid",oid);
        //2、private Date ordertime;//下单时间
        order.setOrdertime(new java.sql.Date(new java.util.Date().getTime()));

        //3、private double total;//该订单的总金额
        //获得session中的购物车
        Cart cart = (Cart) session.getAttribute("cart");
        double total = cart.getTotal();
        order.setTotal(total);

        //4、private int state;//订单支付状态 1代表已付款 0代表未付款
        order.setState(0);

        //5、private String address;//收货地址
        order.setAddress(null);

        //6、private String name;//收货人
        order.setName(null);

        //7、private String telephone;//收货人电话
        order.setTelephone(null);

        //8、private User user;//该订单属于哪个用户
        order.setUser(user);

        order.setSend(0);
        order.setCancel(0);
        List<SeatLog> seatLogList=new ArrayList<>();

        //9、该订单中有多少订单项List<OrderItem> orderItems = new ArrayList<OrderItem>();
        //获得购物车中的购物项的集合map
        Map<String, CartItem> cartItems = cart.getCartItems();
        for(Map.Entry<String, CartItem> entry : cartItems.entrySet()){
            //取出每一个购物项
            CartItem cartItem = entry.getValue();
            //创建新的订单项
            OrderItem orderItem = new OrderItem();
            //1)private String itemid;//订单项的id
            orderItem.setItemid(CommonsUtils.getUUID());
            //2)private int count;//订单项内商品的购买数量
            orderItem.setCount(cartItem.getBuyNum());
            //3)private double subtotal;//订单项小计
            orderItem.setSubtotal(cartItem.getSubtotal());
            //4)private Product product;//订单项内部的商品
            orderItem.setProduct(cartItem.getProduct());
            //5)private Order order;//该订单项属于哪个订单
            orderItem.setOrder(order);
            orderItem.setLocation(cartItem.getLocation());
            orderItem.setRound(cartItem.getRound());
            orderItem.setSeat(cartItem.getSeat());


            String seats=cartItem.getSeat();


            String [] seatlog  =seats.split(",");
            List<String> row=new ArrayList<>();
            List<String> col=new ArrayList<>();
            int i=0;
            for(String p:seatlog){
                if(i%2==0){
                    row.add(p);
                }
                else
                {
                    col.add(p);
                }
                i++;
            }
            for(int j=0;j<row.size();j++){
                String s_row=row.get(j);
                String s_col=col.get(j);

                SeatLog seatLog=new SeatLog();
                seatLog.setRid(cartItem.getRoundID());
                seatLog.setRow(Integer.parseInt(s_row));
                seatLog.setCol(Integer.parseInt(s_col));
                seatLog.setItemid(orderItem.getItemid());
                seatLogList.add(seatLog);
//                int res=ticketService.addSeatLog(cartItem.getRoundID(),Integer.parseInt(s_row),Integer.parseInt(s_col),orderItem.getItemid());
            }

            //将该订单项添加到订单的订单项集合中
            order.getOrderItems().add(orderItem);
        }


        //order对象封装完毕
        //传递数据到service层
        int res_1=orderService.submitOrder(order);
        orderService.submitOrderItem(order.getOrderItems());



        session.setAttribute("order_seatlog",seatLogList);
        session.setAttribute("order", order);
        //页面跳转

       return "redirect:/front/order_info.jsp";
    }



    @RequestMapping(value = "/ProductDeleteCart",method = GET)
    public void ProductDeleteCart(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String pid=request.getParameter("pid");

        HttpSession session=request.getSession();
        Cart cart=(Cart)session.getAttribute("cart");
        if(cart!=null)
        {
            Map<String,CartItem> cartItems=cart.getCartItems();
            cart.setTotal(cart.getTotal()-cartItems.get(pid).getSubtotal());
            cartItems.remove(pid);
            cart.setCartItems(cartItems);
            //	需要修改总价

        }

        session.setAttribute("cart", cart);

        response.sendRedirect(request.getContextPath()+"/front/cart.jsp");
    }


    @RequestMapping(value = "/ProductClearCart",method = GET)
    public void ProductClearCart(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();

        session.removeAttribute("cart");

        response.sendRedirect(request.getContextPath()+"/front/cart.jsp");
    }
}


