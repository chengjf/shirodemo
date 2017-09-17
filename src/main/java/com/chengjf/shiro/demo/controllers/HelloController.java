package com.chengjf.shiro.demo.controllers;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class HelloController {

    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model){
//        String name = "World";
//
//        Subject subject = SecurityUtils.getSubject();
//
//        PrincipalCollection principalCollection = subject.getPrincipals();
//
//        if (principalCollection != null && !principalCollection.isEmpty()) {
//            Collection<Map> principalMaps = subject.getPrincipals().byType(Map.class);
//            if (CollectionUtils.isEmpty(principalMaps)) {
//                name = subject.getPrincipal().toString();
//            }
//            else {
//                name = (String) principalMaps.iterator().next().get("username");
//            }
//        }
//
//        model.addAttribute("subject",subject);
//        model.addAttribute("name", name);

        return "hello";
    }


}
