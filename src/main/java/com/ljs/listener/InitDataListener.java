package com.ljs.listener;

import com.ljs.bo.AppInfo;
import com.ljs.cache.GlobalCache;
import com.ljs.model.Game;
import com.ljs.service.IGameService;
import com.ljs.service.IOrderService;
import com.ljs.util.SourceUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.List;


/**
 * Created by lala on 16/8/24.
 */

@Component
public class InitDataListener implements InitializingBean, ServletContextAware {

    private static final org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger(InitDataListener.class);//record log

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IGameService gameService;

    @Override
    public void afterPropertiesSet() throws Exception {
        //在这个方法里面写 初始化的数据也可以。
        List<Game> games = gameService.getAll();

        List<String>  appKeys = orderService.getAllAppKey();
        logger.info("appkey size is "+appKeys.size());
        GlobalCache.appInfo.clear();
        for(String appKey:appKeys){
            for(Game game:games){
                if (game.getAppKey().equals(appKey)){
                    GlobalCache.appInfo.add(new AppInfo(appKey,game.getName()));
                    break;
                }
            }
        }
        logger.info("global size is "+GlobalCache.appInfo.size());

        logger.info("start parse file...");
        GlobalCache.PapaWalletOrders = SourceUtil.getPapaWalletOrders();
        logger.info(" parse file end");

    }

    @Override
    public void setServletContext(ServletContext arg0) {


    }
}