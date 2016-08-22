package com.ljs.dao.impl;

import com.ljs.dao.IUserDAO;
import com.ljs.model.UserVO;
import com.ljs.util.HibernateUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
@Repository
public class UserDAOImpl implements IUserDAO {
    @Autowired
    private HibernateUtil hibernateUtil;

    @SuppressWarnings("unchecked")
    @Override
    public List<UserVO> getAllUser(){
        String query = "SELECT u.* FROM users u";
        List<Object[]> userObjects = hibernateUtil.fetchAll(query);
        List<UserVO> users = new ArrayList<UserVO>();
        for(Object[] userObject : userObjects){
            UserVO uvo = new UserVO();
            long id = ((BigInteger)userObject[0]).longValue();
            String name = (String) userObject[1];
            Integer age = (Integer) userObject[2];
            uvo.setId(id);
            uvo.setName(name);
            uvo.setAge(age);
            users.add(uvo);
        }
        return users;
    }
}
