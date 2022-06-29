package com.jeff.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/index")
    public String index() {
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
