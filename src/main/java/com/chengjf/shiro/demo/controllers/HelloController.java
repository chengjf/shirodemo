package com.chengjf.shiro.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
