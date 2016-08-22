package com.ljs.service.impl;

import com.ljs.bo.Page;
import com.ljs.dao.IOrderDAO;
import com.ljs.dao.IUserDAO;
import com.ljs.model.Order;
import com.ljs.model.UserVO;
import com.ljs.service.IOrderService;
import com.ljs.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
    @Autowired
    IOrderDAO dao;

    @Override
    public Page<Order> getAllOrder(int pageNo, int pageCount) {
        return dao.getAllOrderByPagination(pageNo,pageCount);
    }

    @Override
    public Page<Order> getAllOrder(String walletType, long startTime, long endTime, int pageNo, int pageCount) {
        return dao.getAllOrderByPagination(walletType,startTime,endTime,pageNo,pageCount);
    }

    @Override
    public List<Order> getByOrderId(String orderId) {
        return dao.getByOrderId(orderId);
    }

}
