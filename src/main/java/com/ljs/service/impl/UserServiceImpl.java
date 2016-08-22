package com.ljs.service.impl;

import com.ljs.dao.IUserDAO;
import com.ljs.model.UserVO;
import com.ljs.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
/**
 * Created by Administrator on 2016/7/28.
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserDAO dao;

    @Override
    public List<UserVO> getAllUser() {
        return dao.getAllUser();
    }
}
