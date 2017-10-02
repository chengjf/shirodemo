package com.chengjf.shiro.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jeff on 2017/10/2.
 */
@Controller
public class IndexController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginTemplate() {
        return "login";
    }
}
