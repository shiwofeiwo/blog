package com.jeff.controller;

import com.jeff.entity.Blog;
import com.jeff.entity.PageInfo;
import com.jeff.entity.User;
import com.jeff.service.BlogService;
import com.jeff.service.TagService;
import com.jeff.utils.MarkDownUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String index(Model m,
                        HttpSession session,
                        @RequestParam(value = "pn", required = false, defaultValue = "1") Integer pn) {
        //如果在登录controller中登陆成功会将user存到session中,若此处没有user,说明是游客,那么就不用显示发表文章的标签
        User user = (User) session.getAttribute("user");
        if (user == null) {
            //游客的情况
            m.addAttribute("user", null);
        }
        //管理员的情况
        m.addAttribute("user", user);

        //获取blog列表,确定页数
        PageInfo pageInfo = blogService.pageInfo(pn);

        int tmpPage = Math.toIntExact(pageInfo.getTotal() / 5);
        int tmp = Math.toIntExact(pageInfo.getTotal() % 5);
        int res = tmpPage;

        if (tmp != 0) res = tmpPage + 1;
        //第一种情况
        if (pn > res)
            pageInfo = blogService.pageInfo(res);
        //第二种情况
        if (pn <= 1)
            pageInfo = blogService.pageInfo(1);
        m.addAttribute("pageInfo", pageInfo);
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

    //全文
    @RequestMapping("/readMore/{id}")
    public String readMore(@PathVariable("id") Integer id, Model model) {
        Blog blog = blogService.getBlogById(id);
        String content = blog.getContent();
        String markdownToHtml = MarkDownUtils.markdownToHtml(content);
        model.addAttribute("content", markdownToHtml);
        return "readMore";
    }

    //模糊查询
    @RequestMapping(value = "/searchByTitle", method = RequestMethod.POST)
    public String fuzzyQuery(@RequestParam("input-blog") String title,
                             Model model) {
        List<Blog> blogByFuzzyList = blogService.getBlogListByFuzzy(title);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setBlogs(blogByFuzzyList);
        model.addAttribute("pageInfo", pageInfo);
        return "/index";
    }

    //关于我
    @RequestMapping("/about")
    public String about() {
        return "about";
    }

}
