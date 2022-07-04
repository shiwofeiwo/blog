package com.jeff.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blogs")
public class Blog {

    private Integer id; //ID
    private Integer tag_id; //tag_id
    private String title; //标题
    private String content; //内容
    @TableField("`desc`")
    private String desc; //简介
    private String picture; //封面图
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime; //创建时间
    @TableField("is_published")
    private Integer isPublished;// 1为所有人可见 0为仅管理员可见

}
