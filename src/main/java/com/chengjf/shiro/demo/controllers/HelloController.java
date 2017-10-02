package com.chengjf.shiro.demo.controllers;

import com.chengjf.shiro.demo.utils.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;

@RestController
public class HelloController {

    @RequestMapping("/")
    public R index(HttpServletRequest request, Model model){
        String name = "World";

        Subject subject = SecurityUtils.getSubject();

        PrincipalCollection principalCollection = subject.getPrincipals();

        if (principalCollection != null && !principalCollection.isEmpty()) {
            Collection<Map> principalMaps = subject.getPrincipals().byType(Map.class);
            if (CollectionUtils.isEmpty(principalMaps)) {
                name = subject.getPrincipal().toString();
            }
            else {
                name = (String) principalMaps.iterator().next().get("username");
            }
        }

        model.addAttribute("subject",subject);
        model.addAttribute("name", name);
        R r = new R();
        r.put("name", name);
        return r;
    }


}
