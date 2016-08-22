package com.ljs.service;

import com.ljs.bo.Page;
import com.ljs.model.Order;
import com.ljs.model.UserVO;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
public interface IOrderService {
    public Page<Order> getAllOrder(int pageNo, int pageCount);
    public Page<Order> getAllOrder(String walletType,long startTime,long endTime,int pageNo, int pageCount);
    public List<Order> getByOrderId(String orderId);
}
