package com.chengjf.shiro.demo.controllers;

import com.chengjf.shiro.demo.common.model.ErrorMessage;
import com.chengjf.shiro.demo.shiro.util.AdminException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String loginTemplate() {

        return "login";
    }
    @RequestMapping(value="/login",method= RequestMethod.POST)
    public String login(HttpServletRequest request, Map<String, Object> map,RedirectAttributes redirectAttributes){
        System.out.println("HomeController.login()");
        // FormAuthenticationFilter定义了出错异常，自定义的异常，也可以继承此类下添加
        String exception = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        System.out.println("exception=" + exception);

        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
            map.put("msg", msg);

            throw new AdminException(msg);
        }else {
            return "redirect:/";
        }


    }
}
