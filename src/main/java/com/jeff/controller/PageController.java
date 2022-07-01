package com.jeff.controller;

import com.jeff.entity.Blog;
import com.jeff.entity.User;
import com.jeff.service.BlogService;
import com.jeff.service.TagService;
import com.jeff.utils.MarkDownUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PageController {

    @Resource
    private BlogService blogService;

    @Resource
    private TagService tagService;


    // 通过url可以跳转管理员登录界面
    @RequestMapping("/admin")
    public String login() {
        return "login";
    }

    //跳转主页
    @RequestMapping("/")
    public String index(Model m, HttpSession session) {
        //如果在登录controller中登陆成功会将user存到session中,若此处没有user,说明是游客,那么就不用显示发表文章的标签
        User user = (User) session.getAttribute("user");
        if (user == null) {
            //游客的情况
            m.addAttribute("user", null);
        }
        //管理员的情况
        m.addAttribute("user", user);
        //获取blog列表
        List<Blog> blogs = blogService.getAllBlogs();
        m.addAttribute("blogs", blogs);
        return "index";
    }

    //发布新文章
    @RequestMapping("/blog-add")
    public String blogAdd(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/index";
        }
        List<String> tags = tagService.getAllTagName();
        model.addAttribute("tags", tags);
        return "blog-add";
    }

    @RequestMapping("/readMore/{id}")
    public String readMore(@PathVariable("id") Integer id, Model model) {
        Blog blog = blogService.getBlogById(id);
        String content = blog.getContent();
        String markdownToHtml = MarkDownUtils.markdownToHtml(content);
        model.addAttribute("content", markdownToHtml);
        return "readMore";
    }

    @RequestMapping("/blog-list")
    public String blogList() {
        return "blog-list";
    }

    //关于我
    @RequestMapping("/about")
    public String about() {
        return "about";
    }

}
