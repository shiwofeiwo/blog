package com.jeff.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TagMapper {

    @Select("select tag_id from blog.tags where tag_name = #{tag_name}")
    Integer getTagIdByName(@Param("tag_name") String name);

    @Select("select tag_name from tags")
    List<String> getAllTagName();

}
