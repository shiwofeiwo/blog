package com.jeff.service.impl;

import com.jeff.entity.Blog;
import com.jeff.mapper.BlogMapper;
import com.jeff.mapper.TagMapper;
import com.jeff.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    @Resource
    private TagMapper tagMapper;

    @Override
    public List<Blog> getAllBlogs() {
        return blogMapper.getAllBlogs();
    }

    @Override
    public void addBlog(Blog blog) {
        blogMapper.addBlog(blog);
    }

    @Override
    public Integer getTagIdByTagName(String name) {
        return tagMapper.getTagIdByName(name);
    }

}
