package com.ljs.dao.impl;

import com.ljs.bo.Page;
import com.ljs.dao.IOrderDAO;
import com.ljs.dao.IUserDAO;
import com.ljs.model.Order;
import com.ljs.model.UserVO;
import com.ljs.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
@Repository
public class OrderDAOImpl implements IOrderDAO {
    @Autowired
    private HibernateUtil hibernateUtil;

//    @SuppressWarnings("unchecked")
//    @Override
//    public List<UserVO> getAllUser(){
//        String query = "SELECT u.* FROM users u";
//        List<Object[]> userObjects = hibernateUtil.fetchAll(query);
//        List<UserVO> users = new ArrayList<UserVO>();
//        for(Object[] userObject : userObjects){
//            UserVO uvo = new UserVO();
//            long id = ((BigInteger)userObject[0]).longValue();
//            String name = (String) userObject[1];
//            Integer age = (Integer) userObject[2];
//            uvo.setId(id);
//            uvo.setName(name);
//            uvo.setAge(age);
//            users.add(uvo);
//        }
//        return users;
//    }

    @Override
    public Page<Order> getAllOrderByPagination(int pageNo, int pageSize) {
        Page<Order> page = new Page<>();
        page.setList(hibernateUtil.getListForPage("from Order where orderStatus = 1",pageNo,pageSize));
        page.setPageNO(pageNo);
        page.setPageSize(pageSize);

        //总条数
        List sumList = hibernateUtil.getHibernateTemplate().find("select count(*) from Order where orderStatus =1 ");
        page.setTotalCount(sumList==null?0: (Long) sumList.get(0));

        return page;
    }


    @Override
    public Page<Order> getAllOrderByPagination(String appKey, String action,String walletType, long startTime, long endTime, int pageNo, int pageSize) {

        if(appKey==null||appKey.equals("-")){
            appKey = "";
        }else{
            appKey = "and appKey = '"+appKey+"' ";
        }


        if(action==null||action.equals("-")){
            action = "";
        }else if(action.equals("null")){
            action = "and action is null ";
        }else{
            action = "and action = '"+action+"' ";
        }

        if(walletType==null||walletType.equals("-")){
            walletType = "";
        }else if(walletType.equals("null")){
            walletType = "and walletType is null ";
        }else{
            walletType = "and walletType = '"+walletType+"'";
        }

        Page<Order> page = new Page<>();
        int offset = (pageNo-1)*pageSize;
        page.setList(hibernateUtil.getListForPage("from Order where orderStatus = 1  "+appKey+action+" "+walletType+" and time between "+startTime + " and "+endTime,offset,pageSize));
        page.setPageNO(pageNo);
        page.setPageSize(pageSize);

        //总条数
        List sumList = hibernateUtil.getHibernateTemplate().find("select count(*) from Order where orderStatus =1 " +appKey+action+" "+ walletType+" and time between "+startTime + " and "+endTime);
        page.setTotalCount(sumList==null?0: (Long) sumList.get(0));



        //总金额
        List payList = hibernateUtil.getHibernateTemplate().find("select sum(payAmount) from Order where orderStatus =1 " +appKey+action+" "+ walletType+" and time between "+startTime + " and "+endTime);
        page.setTotalAmount(payList==null?"0":payList.get(0)+"");

        return page;
    }

    @Override
    public List<Order> getByOrderId(String orderId) {
        return (List<Order>) hibernateUtil.getHibernateTemplate().find("from Order where orderId = '"+orderId+"'");
    }

    @Override
    public void updateByOrderId(String orderId,String walletType) {
        hibernateUtil.executeUpdate("update Order set walletType ='"+walletType+"'  where orderId = '"+orderId+"'");
    }

    @Override
    public List<String> getAllAppKey() {
        return hibernateUtil.fetchAllByHQL("select appKey from Order where orderStatus =1 group by appKey");
    }

}
