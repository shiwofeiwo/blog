package com.jeff;


import com.jeff.mapper.BlogMapper;
import com.jeff.service.BlogService;
import com.jeff.utils.Md5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class BlogApplicationTests {

    @Resource
    private BlogService service;

    @Test
    void contextLoads() throws Exception {
        //生成密码
        String s = Md5Utils.encodeByMd5("123456");
        System.out.println(s);
    }
    @Test
    public void test() {
        Integer hhh = service.getTagIdByTagName("hhh");
        System.out.println(hhh);
    }

}
