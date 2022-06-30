package com.jeff.controller;

import com.jeff.entity.Blog;
import com.jeff.entity.User;
import com.jeff.service.BlogService;
import com.jeff.service.UserService;
import com.jeff.utils.Md5Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api")
public class ApiController {

    @Resource
    private UserService userService;

    @Resource
    private BlogService blogService;

    //登录功能
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session) throws Exception {
        //把密码加密,再去数据库进行校验
        User user = userService.findUser(username, Md5Utils.encodeByMd5(password));
        //如果user不为空,那么将user存到session中,表示是管理员登录,index需要显示发表文章的标签
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/index";
        }
        //密码不对直接重定向到登陆界面
        return "redirect:/admin";
    }

    //登出功能
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        //登出要移除session中的user,不然此次会话一直是管理员身份
        session.removeAttribute("user");
        return "redirect:/admin";
    }

    //发布新文章功能
    @RequestMapping(value = "/blog-add", method = RequestMethod.POST)
    public String addBlog(@RequestParam("tag") String tag,
                          @RequestParam("title") String title,
                          @RequestParam("content") MultipartFile content,
                          @RequestParam("desc") String desc,
                          @RequestParam("picture") MultipartFile picture,
                          @RequestParam("isPublished") String isPublished) {
        Integer tag_id = blogService.getTagIdByTagName(tag);

        //校验前端数据,如果用户修改前端页面value就重定向(无意义,只有管理员能发布新文章)
        Integer isPublic = 0;
        if (("公开").equals(isPublished)) isPublic = 1;
        else if (("私密").equals(isPublished)) isPublic = 0;
        else isPublic = -1;

        System.out.println(content);
        System.out.println(picture);

        Blog blog = new Blog(null, tag_id, title, "content", desc, "picture", null, isPublic);
        //如果 tag_id , isPublic 为空就直接重新输入
        if (blog.getTag_id() == null || blog.getIsPublished() == -1) {
            return "redirect:/blog-add";
        }
        //tag_id,isPublic不为空就执行新增文章
        blogService.addBlog(blog);
        return "redirect:/index";
    }

}
