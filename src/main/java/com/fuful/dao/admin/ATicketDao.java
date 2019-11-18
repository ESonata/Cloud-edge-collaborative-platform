package com.fuful.dao.admin;

import com.fuful.domain.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import sun.security.krb5.internal.Ticket;

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
    public boolean DeleteTicketInfo(@Param("id") int  id);
    public int addTicketDao(@Param("Ticket") Book ticket);
}
