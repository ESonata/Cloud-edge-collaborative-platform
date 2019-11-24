package com.fuful.dao;

import com.fuful.domain.Order;
import com.fuful.domain.OrderProductDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SunRuiBin on 2019/11/14.
 */
@Repository
public interface OrderDao {

    public int addOrder(@Param("Order") Order p);
   public List<Order> findAllOrders(@Param("uid") String uid);
   public List<OrderProductDetail> findOrderItemByOid(@Param("oid") String oid);
   public int UpdateOrderState(@Param("oid") String oid);
}
