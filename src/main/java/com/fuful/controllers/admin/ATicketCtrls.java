package com.fuful.controllers.admin;

import com.fuful.domain.Book;
import com.fuful.domain.BookSuperType;
import com.fuful.domain.Price;
import com.fuful.domain.Ticket;
import com.fuful.service.BookService;
import com.fuful.service.admin.ATicketService;
import com.fuful.service.admin.ExcelService;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by SunRuiBin on 2019/11/17.
 */
@Controller
public class ATicketCtrls {
    @Autowired
    ATicketService AticketService;

    @Autowired
    BookService bookService;
    @RequestMapping(value = "/AdminGetInitBookInfoServlet",method = GET)
    public void AdminGetInitBookInfoServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int page;
        if(request.getParameter("curPage")==null)
        {
            page=1;
        }
        else
        {
            page=Integer.parseInt(request.getParameter("curPage"));
        }

        PageInfo<Book> ticketList =AticketService.getTicketList(page,7);

        List<Book> tickets=ticketList.getList();
        List<Book> new_ticketList=new ArrayList<>();

        for(Book p:tickets){
            String  superTypename=bookService.findSuperTypeName(p.getTypeBID());
            p.setTypeBIDName(superTypename);
            new_ticketList.add(p);
        }
        List<BookSuperType>  categoryplist=bookService.findAllSuperType();

        HttpSession session = request.getSession();
        session.removeAttribute("searchflag");
        session.removeAttribute("bookkeyword");
        session.setAttribute("spliteBookInfoplist", new_ticketList);
        session.setAttribute("curPage", ticketList.getPageNum());
        session.setAttribute("totalPage", ticketList.getPages());
        session.setAttribute("totalCount", ticketList.getTotal());
//				搜索那里初始化一下
        session.setAttribute("currentSearchType",3);
        session.setAttribute("categoryplist",categoryplist);

        response.sendRedirect("/admin/operate-ticket.jsp");

//
    }


    @RequestMapping(value = "/ShowBookInfoDetailServlet",method = GET)
    public void ShowBookInfoDetailServlet(HttpServletRequest request,HttpServletResponse response) throws IOException {

//		获得图书ID
        int id=Integer.parseInt(request.getParameter("ID"));
        HttpSession session=request.getSession();
        session.setAttribute("showdetailthisBookId", id);
//		找到该子栏目的信息


        Book  showBookInfo=(Book)bookService.findBookInfoById(id);

        System.out.println("该图书的录入时间为:"+showBookInfo.getINTime());
        System.out.println("该图书是否为新书:"+showBookInfo.getNewBook());
        System.out.println("该图书的父栏目为:"+showBookInfo.getTypeBID());
        System.out.println("该图书的子栏目为:"+showBookInfo.getTypeID());
//		根据主栏目和子栏目的ID查询他的名称

        String  showBookInfoSuperType=bookService.findSuperTypeName(showBookInfo.getTypeBID());



        if(showBookInfoSuperType==null)
        {
            showBookInfoSuperType="无";
        }

        session.setAttribute("showBookInfoSuperType", showBookInfoSuperType);//显示父栏目名称
        session.setAttribute("showBookInfo", showBookInfo);  //保存的所有图书信息

        response.sendRedirect("/admin/update-ticket.jsp");
    }

    @RequestMapping(value = "/UpdateBookInfoServlet",method = POST)
    public void UpdateBookInfoServlet(HttpServletResponse response,HttpServletRequest request) throws IOException {
        int id=Integer.parseInt(request.getParameter("ID"));
        String bookName=request.getParameter("bookName");
        int typeBID=Integer.parseInt(request.getParameter("typeBID"));
        Double price;
        try{
            price=Double.parseDouble(request.getParameter("price"));
        }
        catch(Exception e){
            price=0.0;
        }
        Double nowPrice;
        try{
            nowPrice=Double.parseDouble(request.getParameter("nowPrice"));
        }
        catch(Exception e){
            nowPrice=0.0;
        }
        String introduce=request.getParameter("introduce");
        System.out.println("Introduce"+introduce);
        String author=request.getParameter("author");
        int newBook=Integer.parseInt(request.getParameter("newBook"));
        int sale=Integer.parseInt(request.getParameter("sale"));
        String picture=request.getParameter("picture");

        String	INTime = request.getParameter("INTime");
        String message="";
        Book p=new Book();
        p.setID(id);
        p.setTypeBID(typeBID);
        p.setTypeID(0);
        p.setBookName(bookName);
        p.setIntroduce(introduce);
        p.setPrice(price);
        p.setNowPrice(nowPrice);
        p.setPicture(picture);
        p.setINTime(INTime);
        p.setNewBook(newBook);
        p.setSale(sale);
        p.setAuthor(author);
        p.setPublish("");

        int msg=AticketService.updateBookInfo(p);

        if(msg>0)
        {
            message="恭喜您:票务信息更新成功!";
        }
        else
        {
            message="请检查输入信息:票务信息更新失败!";
        }

        HttpSession session=request.getSession();
        session.setAttribute("thisBookInfoId", id);

        int getid=(int) session.getAttribute("thisBookInfoId");

        System.out.println("getid is ....."+getid);
        Gson gson=new Gson();
        String json=gson.toJson(message);

        System.out.println(json);
        response.setContentType("text/html;charset=UTF-8");


        response.getWriter().write(json);
    }

    @RequestMapping(value = "/AdminDeleteSingleBookInfoServlet",method = POST)
    public void  AdminDeleteSingleBookInfoServlet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        System.out.println("delete bookinfo want to is:"+id);


        BookService service=new BookService();
        String message="";

        boolean msg=AticketService.deleteBookInfoByID(id);
        if(msg==true)
        {
            message="删除成功!";
        }
        else
        {
            message= "删除失败,请重试!";
        }

        Gson gson=new Gson();
        String json=gson.toJson(message);


        response.setContentType("text/html;charset=UTF-8");

        response.getWriter().write(json);
    }

    @RequestMapping(value = "/AdminAddBookInfoServlet",method = POST)
    public void AdminAddBookInfoServlet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("...开始新增加图书信息");


        String bookName=request.getParameter("bookName");
        int typeBID=Integer.parseInt(request.getParameter("typeBID"));
        Double price;
        try{
            price=Double.parseDouble(request.getParameter("price"));
        }
        catch(Exception e){
            price=0.0;
        }
        Double nowPrice;
        try{
            nowPrice=Double.parseDouble(request.getParameter("nowPrice"));
        }
        catch(Exception e){
            nowPrice=0.0;
        }

        String introduce=request.getParameter("introduce");
        String author=request.getParameter("author");
        String publish=request.getParameter("publish");
        int newBook=Integer.parseInt(request.getParameter("newBook"));
        int sale=Integer.parseInt(request.getParameter("sale"));
        String picture=request.getParameter("picture");

        System.out.println("editor编辑器的内容:"+introduce);

        String	INTime = request.getParameter("INTime");
        String message="";
//		先判断数据库是否有相同数据
        BookService service=new BookService();
//        boolean judge=AticketService.findBookInfoExist(bookName);
        boolean judge=false;
        if(judge==true)
        {
            message="图书名称已经存在,请重新输入!";
        }
        else
        {

            Book p=new Book();
            p.setTypeBID(typeBID);
            p.setTypeID(0);
            p.setBookName(bookName);
            p.setIntroduce(introduce);
            p.setPrice(price);
            p.setNowPrice(nowPrice);
            p.setPicture(picture);
            p.setINTime(INTime);
            p.setNewBook(newBook);
            p.setSale(sale);
            p.setAuthor(author);
            p.setPublish(publish);

            int msg=AticketService.saveBookInfo(p);

            if(msg>0)
            {
                message="恭喜您:图书信息添加成功!";
            }
            else
            {
                message="请检查输入信息:图书信息添加失败!";
            }
        }

        Gson gson=new Gson();
        String json=gson.toJson(message);
        System.out.println(json);
        response.setContentType("text/html;charset=UTF-8");


        response.getWriter().write(json);

    }

    @RequestMapping(value = "/BookSearchByKeywordServlet",method = POST)
    public void BookSearchByKeywordServlet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String bookeyword=request.getParameter("bookkeyword");
        int  searchtype=Integer.parseInt(request.getParameter("searchtype"));

        if(searchtype==3)
        {
            System.out.println("根据图书名称搜索了");
            if(bookeyword.length()==0)
            {
                bookeyword=(String) request.getSession().getAttribute("currentkeyword");
            }
            int page=Integer.parseInt(request.getParameter("curPage"));
            System.out.println("搜索的关键字是:"+bookeyword);
            HttpSession session=request.getSession();

            PageInfo<Book> ticketList =AticketService.getSearchTicketList(page,7,bookeyword);

            List<Book> tickets=ticketList.getList();
            List<Book> new_ticketList=new ArrayList<>();

            for(Book p:tickets){
                String  superTypename=bookService.findSuperTypeName(p.getTypeBID());
                p.setTypeBIDName(superTypename);
                new_ticketList.add(p);
            }

            session.setAttribute("spliteBookInfoplist", new_ticketList);
            session.setAttribute("curPage",ticketList.getPageNum());
            session.setAttribute("totalPage", ticketList.getPages());
            session.setAttribute("totalCount", ticketList.getTotal());


            session.setAttribute("currentkeyword", bookeyword);
            session.setAttribute("currentSearchType", searchtype);
            session.setAttribute("searchflag", 1);
        }
        else if(searchtype==1)
        {

            System.out.println("根据父栏目搜索了");
            if(bookeyword.length()==0)
            {
                bookeyword=(String) request.getSession().getAttribute("currentkeyword");
            }
            int page=Integer.parseInt(request.getParameter("curPage"));
            System.out.println("搜索的关键字是:"+bookeyword);
            HttpSession session=request.getSession();



            PageInfo<Book> ticketList =AticketService.getSearchTicketListByBID(page,7,bookeyword);

            List<Book> tickets=ticketList.getList();
            List<Book> new_ticketList=new ArrayList<>();

            for(Book p:tickets){
                String  superTypename=bookService.findSuperTypeName(p.getTypeBID());
                p.setTypeBIDName(superTypename);
                new_ticketList.add(p);
            }

            session.setAttribute("spliteBookInfoplist", new_ticketList);
            session.setAttribute("curPage",ticketList.getPageNum());
            session.setAttribute("totalPage", ticketList.getPages());
            session.setAttribute("totalCount", ticketList.getTotal());




            session.setAttribute("currentkeyword", bookeyword);
            session.setAttribute("currentSearchType", searchtype);
            session.setAttribute("searchflag", 1);
        }


        System.out.println("搜索转发");




        String message="success";
        Gson gson=new Gson();
        String json=gson.toJson(message);
        System.out.println(json);
        response.setContentType("text/html;charset=UTF-8");

        response.getWriter().write(json);

    }

    @RequestMapping(value = "/ImportExcelServlet",method = POST)
    public void ImportExcelServlet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        List<Book> booklist=new ArrayList();
        if(ServletFileUpload.isMultipartContent(request))
        {
            ServletFileUpload upload=new ServletFileUpload(new DiskFileItemFactory());
            upload.setHeaderEncoding("UTF-8");
            try {
                List<FileItem>   fileitemlist=upload.parseRequest(request);

                for(FileItem fileitem:fileitemlist)
                {
                    try {
                        fileitem.write(new File("/Users/SunRuiBin/Desktop/upload/"+fileitem.getName()));
                        ExcelService service=new ExcelService();
                        booklist=service.imp(fileitem.getName());

                        BookService bookservice=new BookService();

                        for(Book p:booklist)
                        {
                            AticketService.saveBookInfo(p);
                            System.out.println("导入excel表格"+" "+p.getTypeBID()+" "+p.getBookName());
                        }


                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            } catch (FileUploadException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        else
        {

        }

        request.setAttribute("curPage", "1");

        request.getRequestDispatcher("/AdminGetInitBookInfoServlet").forward(request, response);
    }

    @RequestMapping(value = "/ExportExcelServlet",method = POST)
    public void exportExcel(HttpServletRequest request,HttpServletResponse response) throws IOException {

        List<Book>  booklist=AticketService.getAllTicket();

        ExcelService service=new ExcelService();
        Workbook workbook=service.export(true,booklist);
        response.setHeader("Content-Disposition", "attachment;filename=TicketInfoExport.xlsx");
        ServletOutputStream outputStream=response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        workbook.close();
    }

    @RequestMapping(value = "/ShowBookInfo",method = GET)
    public String ShowBookInfo(HttpServletRequest request,HttpServletResponse response){
        HttpSession session=request.getSession();
        int Id=Integer.parseInt(request.getParameter("ID"));
        Ticket TicketInfo=AticketService.findTicketInfoById(Id);
        String  showTicketInfoSuperType=bookService.findSuperTypeName(TicketInfo.getTypeBID());

        if(showTicketInfoSuperType==null)
        {
            showTicketInfoSuperType="无";
        }

        session.setAttribute("SuperName", showTicketInfoSuperType);//显示父栏目名称
        session.setAttribute("TicketInfo", TicketInfo);  //保存的所有图书信息

        List<Price> priceList=AticketService.getPriceList(request.getParameter("ID"));

        session.setAttribute("priceList",priceList);


        System.out.println("RUN="+priceList.get(0).getId());
        return "redirect:/admin/detail-ticket.jsp";
    }




    @RequestMapping(value = "/AddTicketPrice",method = POST)
    public void AddTicketPrice(HttpServletRequest request,HttpServletResponse response) throws IOException {

        Double price=Double.parseDouble(request.getParameter("price"));
        String tid=request.getParameter("tid");
        int res= AticketService.addTicketPrice(tid,price);

        String message="";

        if(price<0){
            message="fail";
        }
        else
        {
            message="success";
        }
        Gson gson=new Gson();
        String json=gson.toJson(message);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);

    }




    @RequestMapping(value = "/deletePrice",method = POST)
    public void deletePrice(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();
        int tid=Integer.parseInt(request.getParameter("tid"));
        Double price=Double.parseDouble(request.getParameter("price"));
        boolean res=AticketService.deleteTicketPrice(tid,price);
        String message="";
        Gson gson=new Gson();
        String json=gson.toJson(message);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);

    }
}
