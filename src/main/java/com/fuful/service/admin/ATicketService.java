package com.fuful.service.admin;

import com.fuful.dao.admin.ATicketDao;
import com.fuful.domain.Book;
import com.fuful.domain.Price;
import com.fuful.domain.Ticket;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by SunRuiBin on 2019/11/17.
 */
@Service
public class ATicketService {
    @Autowired
    ATicketDao ticketDao;

    public boolean deleteTicketPrice(int tid,double price){
        boolean res=ticketDao.DeleteTicketPrice(tid,price);
        return res;
    }

    public List<Price> getPriceList(String tid){
        List<Price> list=ticketDao.findAllPriceByTid(tid);

        return list;
    }
    public int addTicketPrice(String tid,Double price){
        int res=ticketDao.AddTicketPrice(tid,price);
        return res;
    }

    public Ticket findTicketInfoById(int id) {
//		// TODO Auto-generated method stub
        Ticket p = ticketDao.getTicketInfoById(id);
//			PreparedStatement ps = conn.prepareStatement("select * from tb_book where ID=? ");
        return p;
    }





    public List<Book> getAllTicket(){
        List<Book> tickerList=ticketDao.findAllTicket();
        return tickerList;
    }
    public PageInfo<Book> getTicketList(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Book> ticketList = ticketDao.findAllTicket();
        PageInfo<Book> data = new PageInfo<>(ticketList);
        return data;
    }
    public PageInfo<Book> getSearchTicketListByBID(int pageNum, int pageSize,String word){
        PageHelper.startPage(pageNum,pageSize);
        List<Book> ticketList = ticketDao.findSearchAllTicketByBID(word);
        PageInfo<Book> data = new PageInfo<>(ticketList);
        return data;
    }
    public PageInfo<Book> getSearchTicketList(int pageNum, int pageSize,String word){
        PageHelper.startPage(pageNum,pageSize);
        List<Book> ticketList = ticketDao.findSearchAllTicket(word);
        PageInfo<Book> data = new PageInfo<>(ticketList);
        return data;
    }

    public int updateBookInfo(Book p) {
        // TODO Auto-generated method stub

        int res=ticketDao.UpdateBookInfo(p);

//        try {
//            PreparedStatement ps = conn.prepareStatement("update  tb_book set typeBID=?,typeID=?,bookName=?,introduce=?,price=?,nowPrice=?,picture=?,INTime=?,newBook=?,sale=?,author=?,publish=? where ID=?");
//
//
//
        return res;
    }
    public boolean deleteBookInfoByID(int id) {
        // TODO Auto-generated method stub
        boolean res=ticketDao.DeleteTicketInfo(id);

        return res;
    }

    public int saveBookInfo(Book p) {
        // TODO Auto-generated method stub

        int res=ticketDao.addTicketDao(p);

//        try {
//            PreparedStatement ps = conn.prepareStatement("insert into tb_book(typeBID,typeID,bookName,introduce,price,nowPrice,picture,INTime,newBook,sale,author,publish) values(?,?,?,?,?,?,?,?,?,?,?,?)");

//
       return res;

    }

}
