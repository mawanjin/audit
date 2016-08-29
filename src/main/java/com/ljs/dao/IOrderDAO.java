package com.ljs.dao;

import com.ljs.bo.Page;
import com.ljs.model.Order;
import com.ljs.model.UserVO;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
public interface IOrderDAO {

    public Page<Order> getAllOrderByPagination(int pageNo, int pageSize);
    public Page<Order> getAllOrderByPagination(String appKey, String action, String walletType, long startTime, long endTime, int pageNo, int pageSize);
    public List<Order> getByOrderId(String orderId);
    public void updateByOrderId(String orderId, String walletType);

    List<String> getAllAppKey();
}
