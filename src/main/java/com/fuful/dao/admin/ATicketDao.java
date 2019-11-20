package com.fuful.dao.admin;

import com.fuful.domain.Book;
import com.fuful.domain.City;
import com.fuful.domain.Price;
import com.fuful.domain.Ticket;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SunRuiBin on 2019/11/17.
 */
@Repository
public interface ATicketDao {
    public List<Book> findAllTicket();
    public List<Book> findSearchAllTicket(@Param("keyword") String keyword);
    public List<Book> findSearchAllTicketByBID(@Param("keyword") String keyword);
    public int UpdateBookInfo(@Param("Ticket") Book p);
    public int AddTicketPrice(@Param("tid") String tid,@Param("price") Double price);
    public int AddTicketPlace(@Param("tid") String tid,@Param("city") String place);
    public boolean DeleteTicketInfo(@Param("id") int  id);
    public boolean DeleteTicketPrice(@Param("tid") int  id,@Param("price") Double price);
    public boolean DeleteTicketPlace(@Param("tid") String  id,@Param("place") String place);
    public int addTicketDao(@Param("Ticket") Book ticket);
    public Ticket getTicketInfoById(@Param("id") int id);
    public List<Price> findAllPriceByTid(@Param("tid") String id);
    public List<City> findAllPlaceByTid(@Param("tid") String id);
}
