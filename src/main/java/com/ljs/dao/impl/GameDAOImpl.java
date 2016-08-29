package com.ljs.dao.impl;

import com.ljs.bo.Page;
import com.ljs.dao.IGameDAO;
import com.ljs.dao.IOrderDAO;
import com.ljs.model.Game;
import com.ljs.model.Order;
import com.ljs.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
@Repository
public class GameDAOImpl implements IGameDAO {
    @Autowired
    private HibernateUtil hibernateUtil;

    @Override
    public List<Game> getAll() {
        return hibernateUtil.fetchAllByHQL("FROM Game ");
    }
}
