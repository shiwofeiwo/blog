package com.jeff.controller;

import com.jeff.entity.User;
import com.jeff.service.UserService;
import com.jeff.utils.Md5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api")
public class LoginController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session) throws Exception {
        User user = userService.findUser(username, Md5Util.encodeByMd5(password));
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/index";
        }
        return "redirect:/admin";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) throws Exception {
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
