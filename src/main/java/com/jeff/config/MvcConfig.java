package com.jeff.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String local = "file:D:\\blog_site\\pictures\\";
        String server = "file:/www/Projects/pictures/";

        registry.addResourceHandler("/blog/pictures/**")
                //.addResourceLocations(local);//本地映射路径
                .addResourceLocations(server);//linux服务器文件映射路径

    }

}
