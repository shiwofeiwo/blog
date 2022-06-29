package com.jeff;

import com.jeff.utils.Md5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

    @Test
    void contextLoads() throws Exception {
        //生成密码
        String s = Md5Util.encodeByMd5("123456");
        System.out.println(s);
    }

}
