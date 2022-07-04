package com.jeff.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jeff.entity.Blog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {

    @Select("select * from blog.blogs")
    List<Blog> getAllBlogs();

    @Insert("insert into blogs (tag_id, title, content, `desc`, picture, create_time, is_published) " +
            "values (#{tag_id},#{title},#{content},#{desc},#{picture},NOW(),#{isPublished})")
    void addBlog(Blog blog);

    @Select("select * from blogs where id = #{id}")
    Blog getBlogById(Integer id);


}
