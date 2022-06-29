package com.jeff.controller;

import com.jeff.entity.Blog;
import com.jeff.entity.User;
import com.jeff.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PageController {

    @Resource
    private BlogService blogService;

    @RequestMapping("/admin")
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    public String index(Model m, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            m.addAttribute("user", null);
        }
        m.addAttribute("user", user);
        List<Blog> blogs = blogService.getAllBlogs();
        m.addAttribute("blogs", blogs);
        return "index";
    }

    @RequestMapping("/blog-post")
    public String blogPost() {
        return "blog-post";
    }

    @RequestMapping("/blog-list")
    public String blogList() {
        return "blog-list";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

}
