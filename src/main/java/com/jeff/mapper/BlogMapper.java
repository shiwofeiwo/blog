package com.jeff.mapper;

import com.jeff.entity.Blog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BlogMapper {

    @Select("select * from blog.blogs")
    List<Blog> getAllBlogs();

    @Insert("insert into blogs (tag_id, title, content, `desc`, picture, create_time, isPublished) " +
            "values (#{tag_id},#{title},#{content},#{desc},#{picture},NOW(),#{isPublished})")
    void addBlog(Blog blog);

}
