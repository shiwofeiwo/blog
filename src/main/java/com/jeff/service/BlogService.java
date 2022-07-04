package com.jeff.service;

import com.jeff.entity.Blog;

import java.util.List;

public interface BlogService {

    List<Blog> getAllBlogs();

    void addBlog(Blog blog);

    Integer getTagIdByTagName(String name);

    Blog getBlogById(Integer id);

    List<Blog> getBlogListByFuzzy(String title);

}
