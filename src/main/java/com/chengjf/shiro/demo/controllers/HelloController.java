package com.chengjf.shiro.demo.controllers;

import com.chengjf.shiro.demo.shiro.entity.SysUserEntity;
import com.chengjf.shiro.demo.shiro.service.SysUserService;
import com.chengjf.shiro.demo.shiro.util.ShiroUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    SysUserService sysUserService;

    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model) {
        String name = ShiroUtils.getUsername();
        Subject subject = ShiroUtils.getSubject();


        SysUserEntity sysUserEntity = sysUserService.queryByUserName(name);

        List<String> perms = sysUserService.queryAllPerms(sysUserEntity.getUserId());


        model.addAttribute("subject", subject);
        model.addAttribute("name", name);

        model.addAttribute("perms", perms);

        return "hello";
    }


}
