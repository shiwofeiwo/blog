package com.jeff;


import com.jeff.entity.Blog;
import com.jeff.mapper.BlogMapper;
import com.jeff.service.BlogService;
import com.jeff.utils.Md5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Resource
    private BlogService service;

    @Resource
    private BlogMapper blogMapper;

    @Test
    void contextLoads() throws Exception {
        //生成加密后的密码
        String s = Md5Utils.encodeByMd5("12627432cba");
        System.out.println(s);
    }
    @Test
    public void test() {
        Integer hhh = service.getTagIdByTagName("hhh");
        System.out.println(hhh);
    }

    @Test
    public void test02() {
        List<Blog> blogs = service.getAllBlogs();
        for (Blog blog : blogs) {
            System.out.println(blog.getCreateTime());
        }
    }

    @Test
    public void test03() {

    }



}
