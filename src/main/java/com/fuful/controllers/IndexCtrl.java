package com.fuful.controllers;

import com.fuful.domain.*;
import com.fuful.service.TicketService;
import com.fuful.service.FavService;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.omg.CORBA.TIMEOUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
public class IndexCtrl {

    @Autowired
    TicketService ticketService;
    @Autowired
    FavService favService;

    @RequestMapping(value = "/frontindex",method = GET)
    public String hello(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//	准备热门商品
        Map<String,Object> res=new HashMap<>();
        List<Book> hotbooklist= ticketService.getHot();
        String path=request.getContextPath();

        System.out.println("HOTDASDA"+hotbooklist);
//		准备最新商品

        List<Book>  newbooklist= ticketService.getNew();
//		准备打折商品
        List<Book>  salebooklist= ticketService.getDiscount();
//		zhun
//		准备主栏目
        List<BookSuperType> frontindexSuperType= ticketService.findAllSuperType();
//        response.setContentType("text/html;charset=UTF-8");
//        request.setAttribute("hotbooklist", hotbooklist);
//        request.setAttribute("newbooklist", newbooklist);
//        request.setAttribute("salebooklist", salebooklist);
//        request.setAttribute("frontindexSuperType", frontindexSuperType);
//        request.getRequestDispatcher("/front/index.jsp").forward(request, response);
//        res.put("data",hotbooklist);

        HttpSession session=request.getSession();
        session.setAttribute("hotbooklist",hotbooklist);
        session.setAttribute("newbooklist",newbooklist);
        session.setAttribute("salebooklist",salebooklist);
        session.setAttribute("categoryList",frontindexSuperType);
        return "redirect:/front/index.jsp";
    }


    @RequestMapping(value = "/GetInitIndexSuperType",method = POST)
    public void getInitSuper(HttpServletRequest request,HttpServletResponse response) throws IOException {


        List<BookSuperType> IndexSuperTypeList = ticketService.findAllSuperType();
        Gson gson = new Gson();

        System.out.println("..");
        String json=gson.toJson(IndexSuperTypeList);

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }

//    @RequestMapping(value = "/ProductListServlet",method = GET)
//    public String getProductList(@RequestParam("cid") int cid,@RequestParam("currentPage") String pageNum, HttpServletResponse response, HttpServletRequest request){
//        System.out.println("点击主栏目时显示的商品数据");
//        System.out.println(cid);
//        Map<String,Object> res=new HashMap<>();
//        PageInfo<Book> list=bookService.getProductList(Integer.parseInt(pageNum),12,cid);
//        HttpSession session = request.getSession();
//        session.setAttribute("SpliteBookList", list.getList());
//
////        System.out.println("前端图书列表分页显示currentPage："+sp.getCurPage());
//////        System.out.println("前端图书列表分页显示totalPage："+sp.getTotalpage());
//        System.out.println("前端图书列表分页显示totalCount："+list.getPageNum()+" "+list.getPages()+" "+list.getTotal());
//        session.setAttribute("totalPage", list.getPages());
//        session
//                .setAttribute("totalCount", list.getTotal());
//        session.setAttribute("currentPage", list.getPageNum());
//        session.setAttribute("cid", cid);
//        //定义一个记录历史商品信息的集合
//        List<Book> historyProductList = new ArrayList<Book>();
//
////        获得客户端携带名字叫pids的cookie
//        Cookie[] cookies = request.getCookies();
//        if(cookies!=null){
//            for(Cookie cookie:cookies){
//                if("pids".equals(cookie.getName())){
//                    String pids = cookie.getValue();//3-2-1
//                    String[] split = pids.split("-");
//                    for(String pid : split){
//                        Book pro = bookService.findBookInfoById(Integer.parseInt(pid));
//                        historyProductList.add(pro);
//                    }
//                }
//            }
//        }
//
//        //将历史记录的集合放到域中
//        request.setAttribute("historyProductList", historyProductList);
//
//        res.put("data",list);
////        return res;
//        return "redirect:/front/product_list.jsp";
//    }


    @RequestMapping(value = "/ProductListServlet",method = GET)
    public String getProductList(@RequestParam("cid") int cid,@RequestParam("currentPage") String pageNum, HttpServletResponse response, HttpServletRequest request){
        System.out.println("点击主栏目时显示的商品数据");
        System.out.println(cid);
        Map<String,Object> res=new HashMap<>();
        PageInfo<Ticket> list= ticketService.getProductList(Integer.parseInt(pageNum),12,cid);
        HttpSession session = request.getSession();
        session.setAttribute("SpliteBookList", list.getList());

//        System.out.println("前端图书列表分页显示currentPage："+sp.getCurPage());
////        System.out.println("前端图书列表分页显示totalPage："+sp.getTotalpage());
        System.out.println("前端图书列表分页显示totalCount："+list.getPageNum()+" "+list.getPages()+" "+list.getTotal());
        session.setAttribute("totalPage", list.getPages());
        session.setAttribute("totalCount", list.getTotal());
        session.setAttribute("currentPage", list.getPageNum());
        session.setAttribute("cid", cid);
        session.removeAttribute("subtypeFlag");
        //定义一个记录历史商品信息的集合
        List<Ticket> historyProductList = new ArrayList<Ticket>();

////        获得客户端携带名字叫pids的cookie
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if("pids".equals(cookie.getName())){
                    String pids = cookie.getValue();//3-2-1
                    String[] split = pids.split("-");
                    for(String pid : split){
                        Ticket pro = ticketService.findTicketInfoById(Integer.parseInt(pid));
                        historyProductList.add(pro);
                    }
                }
            }
        }


        session.removeAttribute("li_id");
        session.removeAttribute("showmoreInfo" );

        //将历史记录的集合放到域中
        session.setAttribute("historyProductList", historyProductList);



        List<TownInfo> townInfoList=ticketService.getTownInfoList();
        System.out.println(townInfoList.get(0).getTown());
        session.setAttribute("townInfoList",townInfoList);
        return "redirect:/front/product_list.jsp";
    }








//    @RequestMapping(value = "/ProductInfoServlet",method = GET)
//    public String getProductInfo(HttpServletRequest request,HttpServletResponse response,@RequestParam("pid") String pid,@RequestParam("cid") int cid,@RequestParam("pageNum") String pageNum,@RequestParam("typeID") int typeID){

    @RequestMapping(value = "/MarkLinkProduct",method = GET)
    public String MarkLinkProduct(HttpServletRequest request,HttpServletResponse response){
        String pid=request.getParameter("pid");
        int cid = 0;
        try{
            cid=Integer.parseInt(request.getParameter("cid"));
        }
        catch(Exception e){
            System.out.println("字符串转换为整型失败");
        }
        HttpSession session=request.getSession();
        Ticket Bookinfo= ticketService.findTicketInfoById(Integer.parseInt(pid));
        ticketService.addTicketInfoHit(Bookinfo.getHit()+1,Integer.parseInt(pid));
        String  superTypename= ticketService.findSuperTypeName(cid);
        session.setAttribute("pid", pid);
        session.setAttribute("superTypename", superTypename);
        session.setAttribute("product", Bookinfo);
        session.setAttribute("cid", cid);
        return "redirect:/front/product_info.jsp";
    }


    @RequestMapping(value = "/ProductInfoServlet",method = GET)
    public void getProductInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String pid=request.getParameter("pid");

        List<City> cityList=ticketService.getPlaceByTid(pid);
        List<Price> priceList=ticketService.getPriceByTid(pid);
        int currentPage=Integer.parseInt(request.getParameter("currentPage"));
//		获得主栏目ID
        int cid = 0;
        try{
            cid=Integer.parseInt(request.getParameter("cid"));
        }
        catch(Exception e){
            System.out.println("字符串转换为整型失败");
        }
        HttpSession session=request.getSession();

        Ticket Ticketinfo= ticketService.findTicketInfoById(Integer.parseInt(pid));
        System.out.println(Ticketinfo.getHit()+1);
//		图书的浏览次数加1
        ticketService.addTicketInfoHit(Ticketinfo.getHit()+1,Integer.parseInt(pid));

//		获取父栏目名称
        String  superTypename= ticketService.findSuperTypeName(cid);
        System.out.println(superTypename);

        session.setAttribute("pid", pid);
        session.setAttribute("superTypename", superTypename);
        session.setAttribute("product", Ticketinfo);
        session.setAttribute("currentPage", currentPage);
        session.setAttribute("cid", cid);
        session.setAttribute("cityList",cityList);
        session.setAttribute("priceList",priceList);




        if(cityList.size()!=0){
            List<Round> roundList=ticketService.getRoundByPlace(pid,cityList.get(0).getCity());
            session.setAttribute("initRoundList",roundList);
        }


        //获得客户端携带cookie---获得名字是pids的cookie

        String pids = pid;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie cookie : cookies){
                if("pids".equals(cookie.getName())){
                    pids = cookie.getValue();
                    //1-3-2 本次访问商品pid是8----->8-1-3-2
                    //1-3-2 本次访问商品pid是3----->3-1-2
                    //1-3-2 本次访问商品pid是2----->2-1-3
                    //将pids拆成一个数组
                    String[] split = pids.split("-");//{3,1,2}
                    List<String> asList = Arrays.asList(split);//[3,1,2]
                    LinkedList<String> list = new LinkedList<String>(asList);//[3,1,2]
                    //判断集合中是否存在当前pid
                    if(list.contains(pid)){
                        //包含当前查看商品的pid    //防止多次加入同一个商品
                        list.remove(pid);
                        list.addFirst(pid);
                    }else{
                        //不包含当前查看商品的pid 直接将该pid放到头上
                        list.addFirst(pid);

                    }
                    //将[3,1,2]转成3-1-2字符串
                    StringBuffer sb = new StringBuffer();
                    for(int i=0;i<list.size()&&i<7;i++){
                        sb.append(list.get(i));
                        sb.append("-");//3-1-2-
                    }
                    //去掉3-1-2-后的-
                    pids = sb.substring(0, sb.length()-1);
                }
            }
        }


        System.out.println("PIDS="+pids);
        Cookie cookie_pids = new Cookie("pids",pids);
        response.addCookie(cookie_pids);


//		获得初始化的评论信息

//        FSpliteMarkList sp=new FSpliteMarkList();

        int markcommentcurPage;
        if(request.getParameter("markcommentcurPage")==null)
        {
            markcommentcurPage=1;
        }
        else
        {
            markcommentcurPage=Integer.parseInt(request.getParameter("markcommentcurPage"));
        }

        System.out.println("markcommentcurPage"+markcommentcurPage);



        PageInfo<Mark> markList= ticketService.getMarkList(markcommentcurPage,7,Integer.parseInt(pid));
        System.out.println("markList======"+markList.getList());
//		评论的分页
        session.setAttribute("markcommentlist", markList.getList());
        session.setAttribute("markcommentcurPage", markList.getPageNum());
        session.setAttribute("markcommenttotalPage", markList.getPages());
        session.setAttribute("markcommenttotaltotalCount", markList.getTotal());


        System.out.println("MARKLIST:"+markList);




//		获得本用户的收藏或者未收藏
        User user=(User)session.getAttribute("user");
        if(user!=null)
        {
            BookLove islove= favService.queryState(user.getUid(),pid);
            if(islove!=null){
                if(islove.getState()==1){
                    session.setAttribute("islove", 1);
                }
                else
                {
                    session.setAttribute("islove", 0);

                }
            }
            else
            {
                session.setAttribute("islove", 0);
            }
        }
        response.sendRedirect("/front/product_info.jsp");
    }

    @RequestMapping(value = "/getRound",method = POST)
    public void getRound(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String tid=request.getParameter("tid");
        String city=request.getParameter("city");
        List<Round> roundList=ticketService.getRoundByPlace(tid,city);

        HttpSession session=request.getSession();
        session.setAttribute("city",city);
        Gson gson=new Gson();
        String json=gson.toJson(roundList);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);


    }

    @RequestMapping(value = "/ProductSubTypeListServlet",method = GET)
    public void ProductSubTypeListServlet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        String towninfo=request.getParameter("ID");
        String LiId=request.getParameter("liId");

        String moreInfoFlag=request.getParameter("moreInfoFLag");

        String currentPageStr = request.getParameter("currentPage");
        if(currentPageStr==null) currentPageStr="1";
        int currentPage = Integer.parseInt(currentPageStr);

        HttpSession session = request.getSession();

        session.removeAttribute("showmoreInfo");

        if(moreInfoFlag!=null){
            session.setAttribute("showmoreInfo","1");
        }
        else
        {
            System.out.println("ShowMoreInfo"+" 0");
            session.setAttribute("showmoreInfo","0");
        }

        PageInfo<City> tidList =ticketService.getTicketListByTown(currentPage,12,towninfo);

        List<Ticket>  ticketList=new ArrayList<>();

        for(City p:tidList.getList()){
            Ticket ticket=new Ticket();
            ticket=ticketService.findTicketInfoById(p.getTid());
            ticket.setPlace(p.getCity());
            ticketList.add(ticket);
        }

        System.out.println("TICKETLIST="+ticketList.size());

        session.setAttribute("subtypeFlag", towninfo);
//
        session.setAttribute("SpliteBookList", ticketList);
        request.setAttribute("currentPage", tidList.getPageNum());
        request.setAttribute("totalPage", tidList.getPages());
        request.setAttribute("totalCount",tidList.getTotal());
//



        //定义一个记录历史商品信息的集合
        List<Ticket> historyProductList = new ArrayList<Ticket>();

//        //获得客户端携带名字叫pids的cookie
//        Cookie[] cookies = request.getCookies();
//        if(cookies!=null){
//            for(Cookie cookie:cookies){
//                if("pids".equals(cookie.getName())){
//                    String pids = cookie.getValue();//3-2-1
//                    String[] split = pids.split("-");
//                    for(String pid : split){
//                        Book pro = service.findBookInfoById(Integer.parseInt(pid));
//                        historyProductList.add(pro);
//                    }
//                }
//            }
//        }

        //将历史记录的集合放到域中
        request.setAttribute("historyProductList", historyProductList);

        session.setAttribute("li_id",LiId);
        request.getRequestDispatcher("/front/product_list.jsp").forward(request, response);
    }


    @RequestMapping(value = "/SelectSeatsServlet",method = GET)
    public String SelectSeatsServlet(HttpServletRequest request,HttpServletResponse response){
        String tid=request.getParameter("pid");
        String location=request.getParameter("location");
        String round=request.getParameter("round");
        String price=request.getParameter("price");
        List<Round> roundList=ticketService.getPriceByLRT(tid,location,round);

        List<SeatLog> seatLogList=ticketService.getSeatLog(roundList.get(0).getId());
        Ticket ticketName=ticketService.findTicketInfoById(Integer.parseInt(roundList.get(0).getTid()));
        HttpSession session=request.getSession();


        User user= (User) session.getAttribute("user");
        if(user==null){
            return "redirect:/front/login.jsp";
        }

        session.setAttribute("round",roundList.get(0));
        session.setAttribute("row",roundList.get(0).getRow());
        session.setAttribute("col",roundList.get(0).getCol());
        session.setAttribute("seatLogList",seatLogList);
        session.setAttribute("price",price);
        session.setAttribute("ticketName",ticketName.getTicketName());

        return "redirect:/front/selectSeats.jsp";
    }

    @RequestMapping(value = "/GetSeatLogServlet",method = POST)
    public void GetSeatLogServlet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String rid=request.getParameter("rid");

        List<SeatLog> seatLogList=ticketService.getSeatLog(Integer.parseInt(rid));
        Gson gson=new Gson();
        String json=gson.toJson(seatLogList);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }
}
