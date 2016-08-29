package com.ljs.controller;

import com.ljs.bo.Page;
import com.ljs.bo.PapaWallet;
import com.ljs.cache.GlobalCache;
import com.ljs.model.Order;
import com.ljs.service.IOrderService;
import com.ljs.util.SourceUtil;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class OrderController {
    private static final Logger logger = Logger.getLogger(OrderController.class);//record log
    private static final String USER = "order";//page name

    public OrderController() {
        System.out.println("UserController()");
    }

    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "/order", method= RequestMethod.GET)
    public ModelAndView order(@RequestParam(value ="pageNO",defaultValue = "1") int pageNO,@RequestParam(value ="pageSize",defaultValue = "20") int pageSize,@RequestParam(value = "startTime",required=false) String startTime,@RequestParam(value = "endTime",required=false) String endTime,@RequestParam(value = "walletType",required=false) String walletType,@RequestParam(value = "action",required=false) String action,@RequestParam(value = "orderId",required=false) String orderId,@RequestParam(value = "appkey",required=false,defaultValue = "-") String appkey){
        logger.info("Loading Order. Data: pageNO="+pageNO);
        ModelAndView result = new ModelAndView(USER);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long sT=0;
        long eT = System.currentTimeMillis()/1000;
        try {
            sT = sdf.parse(startTime + " 00:00:00").getTime()/1000;

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            eT = (sdf.parse(endTime + " 23:59:59").getTime())/1000;
        } catch (Exception e) {
            e.printStackTrace();
        }

        result.addObject("appinfo", GlobalCache.appInfo);
        result.addObject("startTime",startTime);
        result.addObject("endTime",endTime);
        result.addObject("walletType",walletType);
        result.addObject("action",action);
        result.addObject("appkey",appkey);

        if(orderId!=null&&!orderId.trim().equals("")){
            result.addObject("orderId", orderId);
            Page<Order> page = new Page<Order>();
            page.setList(orderService.getByOrderId(orderId));
            result.addObject("page", page);
        }else {
            Page<Order> rs = orderService.getAllOrder(appkey,action,walletType,sT,eT,pageNO,pageSize);
            result.addObject("page", rs);
        }

        return result;
    }


    @RequestMapping(value = "/order/fill_up", method= RequestMethod.GET)
    public ModelAndView orderFillup(){
        logger.info("method orderFillup() called.");
        ModelAndView result = new ModelAndView("fillup");
        List<PapaWallet> papaWallets = GlobalCache.PapaWalletOrders;
        for (PapaWallet papaWallet:papaWallets)orderService.updateByOrderId(papaWallet.getOrderId(),papaWallet.getWalletType());
        return result;
    }


}