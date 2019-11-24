package com.fuful.dao.admin;

import com.fuful.domain.Order;
import com.fuful.domain.OrderProductDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SunRuiBin on 2019/11/17.
 */
@Repository
public interface  AOrderDao {

    public List<Order> findAllOrder();
    public List<OrderProductDetail> findOrderInfoByOid(@Param("oid") String oid);
    public boolean DeleteOrder(@Param("oid") String oid);

}
