package com.jeff.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    private Integer id; //ID
    private Integer tag_id; //tag_id
    private String title; //标题
    private String content; //内容
    private String desc; //简介
    private String picture; //封面图
    private String create_time; //创建时间
    private Integer isPublished;// 1为所有人可见 0为仅管理员可见

}
