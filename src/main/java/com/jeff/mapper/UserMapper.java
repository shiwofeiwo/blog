package com.jeff.mapper;

import com.jeff.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from blog.users where username = #{username} and password = #{password}")
    User findUser(String username, String password);

}
