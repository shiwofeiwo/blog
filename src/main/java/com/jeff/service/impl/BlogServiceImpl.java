package com.jeff.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jeff.entity.Blog;
import com.jeff.entity.PageInfo;
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

    @Override
    public Blog getBlogById(Integer id) {
        return blogMapper.getBlogById(id);
    }

    @Override
    public List<Blog> getBlogListByFuzzy(String title) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.like("title", title);
        return blogMapper.selectList(wrapper);
    }

    @Override
    public void deleteBlogById(int id) {
        blogMapper.deleteById(id);
    }

    @Override
    public PageInfo pageInfo(int currentNum) {
        QueryWrapper<Object> wrapper = new QueryWrapper<>();
        Page<Blog> page = new Page<>(currentNum, 5);
        IPage<Blog> iPage = blogMapper.selectPage(page, null);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurrentNum(currentNum);
        pageInfo.setTotal(iPage.getTotal());
        pageInfo.setBlogs(iPage.getRecords());
        return pageInfo;
    }

}
