package com.ljs.service.impl;

import com.ljs.bo.Page;
import com.ljs.dao.IGameDAO;
import com.ljs.dao.IOrderDAO;
import com.ljs.model.Game;
import com.ljs.model.Order;
import com.ljs.service.IGameService;
import com.ljs.service.IOrderService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
@Service
@Transactional
public class GameServiceImpl implements IGameService {
    private static final Logger logger = Logger.getLogger(GameServiceImpl.class);//record log

    @Autowired
    IGameDAO dao;

    @Override
    public List<Game> getAll() {
        return dao.getAll();
    }

}
