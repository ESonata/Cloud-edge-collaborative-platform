package com.fuful.service.admin;

import com.fuful.dao.admin.AOrderDao;
import com.fuful.domain.Book;
import com.fuful.domain.Order;
import com.fuful.domain.OrderProductDetail;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.WebServiceRef;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SunRuiBin on 2019/11/17.
 */
@Service
public class AOrderService {
    @Autowired
    AOrderDao aOrderDao;


    public PageInfo<Order> findAllOrdersNoById(int pageNum,int pageSize) {

            PageHelper.startPage(pageNum,pageSize);
            List<Order> orders = aOrderDao.findAllOrder();
            PageInfo<Order> data = new PageInfo<>(orders);
            return data;

    }

    public List<Map<String, Object>> findOrderInfoByOid(String oid) {
        // TODO Auto-generated method stub
        List<Map<String, Object>> plist = new ArrayList();
        List<OrderProductDetail> orderProductDetails=aOrderDao.findOrderInfoByOid(oid);

        for(OrderProductDetail p:orderProductDetails){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("count", p.getCount());
            map.put("subtotal", p.getSubtotal());
            map.put("picture", p.getPicture());
            map.put("bookName", p.getBookName());
            map.put("price", p.getPrice());
            plist.add(map);
        }
        return plist;
    }

    public boolean deleteOrderByOid(String oid) {
        // TODO Auto-generated method stub

        boolean res=aOrderDao.DeleteOrder(oid);
        return res;
    }


}
