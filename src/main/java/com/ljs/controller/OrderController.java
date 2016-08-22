package com.ljs.controller;

import com.ljs.bo.Page;
import com.ljs.model.Order;
import com.ljs.service.IOrderService;
import com.ljs.service.IUserService;
import jdk.nashorn.internal.parser.DateParser;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
    public ModelAndView order(@RequestParam(value ="pageNO") int pageNO,@RequestParam(value ="pageSize") int pageSize,@RequestParam(value = "startTime",required=false) String startTime,@RequestParam(value = "endTime",required=false) String endTime,@RequestParam(value = "walletType",required=false) String walletType,@RequestParam(value = "orderId",required=false) String orderId){
        logger.info("Loading Order. Data: pageNO="+pageNO);
        ModelAndView result = new ModelAndView(USER);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long sT=0;
        long eT = System.currentTimeMillis();
        try {
            sT = sdf.parse(startTime + " 00:00:00").getTime()/1000;
            eT = sdf.parse(endTime + " 23:59:59").getTime()/1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.addObject("startTime",startTime);
        result.addObject("endTime",endTime);
        result.addObject("walletType",walletType);


        if(orderId!=null&&!orderId.trim().equals("")){
            result.addObject("orderId", orderId);
            Page<Order> page = new Page<>();
            page.setList(orderService.getByOrderId(orderId));
            result.addObject("page", page);
        }else {
            result.addObject("page", orderService.getAllOrder(walletType,sT,eT,pageNO,pageSize));
        }

        return result;
    }
}