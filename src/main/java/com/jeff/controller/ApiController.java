package com.jeff.controller;

import com.jeff.entity.Blog;
import com.jeff.entity.User;
import com.jeff.service.BlogService;
import com.jeff.service.UserService;
import com.jeff.utils.Md5Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.*;

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
            return "redirect:/";
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
                          @RequestParam("isPublished") String isPublished) throws IOException {
        //通过tag名获取tag_id存到数据库
        Integer tag_id = blogService.getTagIdByTagName(tag);

        //校验前端数据,如果用户修改前端页面value就重定向(无意义,只有管理员能发布新文章)
        Integer isPublic = 0;
        if (("公开").equals(isPublished)) isPublic = 1;
        else if (("私密").equals(isPublished)) isPublic = 0;
        else isPublic = -1;

        //获取图片文件名
        String pictureOriginalFilename = picture.getOriginalFilename();
        //设置文件存储路径
        File file = new File("D:\\blog_site\\pictures\\" + pictureOriginalFilename);
        //把文件存到知道的存储路径
        picture.transferTo(file);
        //生成虚拟路径,并将字符串保存到数据库
        String picture_address = "/blog/pictures/" + pictureOriginalFilename;

        //字节流转为字符流
        BufferedReader bf = new BufferedReader(new InputStreamReader(content.getInputStream()));
        String line = null;
        StringBuffer sb = new StringBuffer();
        //把md文件全部都到stringBuffer中
        while ((line = bf.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        bf.close();
        //stringBuffer转成String
        String str_content = sb.toString();

        //要发布的新文章的对象
        Blog blog = new Blog(null, tag_id, title, str_content, desc, picture_address, null, isPublic);

        //如果 tag_id , isPublic 为空就直接重新输入
        if (blog.getTag_id() == null || blog.getIsPublished() == -1) {
            return "redirect:/blog-add";
        }

        //tag_id,isPublic合法就执行新增文章
        blogService.addBlog(blog);
        return "redirect:/";
    }

    //删除文章
    @GetMapping(value = "/deleteBlog")
    public String deleteBlogById(@RequestParam("id") int id) {
        blogService.deleteBlogById(id);
        return "redirect:/";
    }

}
