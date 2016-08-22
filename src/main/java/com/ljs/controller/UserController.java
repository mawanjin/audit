package com.ljs.controller;

import com.ljs.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.jboss.logging.Logger;
@Controller
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);//record log
    private static final String USER = "user";//page name

    public UserController() {
        System.out.println("UserController()");
    }

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/", method= RequestMethod.GET)
    public ModelAndView user(){
        logger.info("Loading User. Data: ");
        ModelAndView result = new ModelAndView(USER);
        result.addObject("users", userService.getAllUser());
        return result;
    }
}