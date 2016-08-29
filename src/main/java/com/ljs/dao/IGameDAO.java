package com.ljs.dao;

import com.ljs.bo.Page;
import com.ljs.model.Game;
import com.ljs.model.Order;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
public interface IGameDAO {
    List<Game> getAll();
}
