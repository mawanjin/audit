package com.ljs.service.impl;

import com.ljs.bo.Page;
import com.ljs.bo.PapaWallet;
import com.ljs.cache.GlobalCache;
import com.ljs.dao.IOrderDAO;
import com.ljs.dao.IUserDAO;
import com.ljs.model.Order;
import com.ljs.model.UserVO;
import com.ljs.service.IOrderService;
import com.ljs.service.IUserService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
@Service("orderService")
@Transactional
public class OrderServiceImpl implements IOrderService {
    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);//record log

    @Autowired
    IOrderDAO dao;

    @Override
    public Page<Order> getAllOrder(int pageNo, int pageCount) {
        return dao.getAllOrderByPagination(pageNo,pageCount);
    }

    @Override
    public Page<Order> getAllOrder(String appkey,String action, String walletType, long startTime, long endTime, int pageNo, int pageCount) {
        Page<Order> page = dao.getAllOrderByPagination(appkey,action,walletType,startTime,endTime,pageNo,pageCount);
        if(page.getList()!=null){

            for(Order order:page.getList()){
                if(order.getWalletType()!=null){
                    if(order.getWalletType().equals("coupon_red_envelope")){

                        for(PapaWallet papaWallet : GlobalCache.PapaWalletOrders){
                            if(papaWallet.getOrderId().equals(order.getOrderId()+"-1")&&papaWallet.getWalletType().equals("coupon")){
                                order.set_coupon((papaWallet.getAmount()/100)+"");
                                page.setTotalCouponAmount((Double.parseDouble(page.getTotalCouponAmount())+papaWallet.getAmount()/100)+"");
                                break;
                            }
                        }

                    }else if(order.getWalletType().equals("coupon_wallet")){

                        for(PapaWallet papaWallet : GlobalCache.PapaWalletOrders){
                            if(papaWallet.getOrderId().equals(order.getOrderId()+"-1")&&papaWallet.getWalletType().equals("coupon")){
                                order.set_coupon((papaWallet.getAmount()/100)+"");
                                page.setTotalCouponAmount((Double.parseDouble(page.getTotalCouponAmount())+papaWallet.getAmount()/100)+"");
                                break;
                            }
                        }

                    }
                }

            }
        }
        return page;
    }

    @Override
    public List<Order> getByOrderId(String orderId) {
        return dao.getByOrderId(orderId);
    }

    @Override
    public void updateByOrderId(String orderId,String walletType) {
        logger.info("method orderFillup() called.orderId="+orderId);
            List<Order> orders = dao.getByOrderId(orderId.split("-")[0]);
            for (Order o:orders){
                if(o.getWalletType()!=null&&!o.getWalletType().equals("")){
                    System.out.println("warn---orderid="+o.getOrderId()+";getWalletType="+o.getWalletType());
                }else {
                    dao.updateByOrderId(o.getOrderId(),walletType);
                }
            }
    }

    @Override
    public List<String> getAllAppKey() {
        logger.info("method getAllAppKey() called");

        return dao.getAllAppKey();

    }

}
