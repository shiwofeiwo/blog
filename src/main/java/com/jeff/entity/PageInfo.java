package com.jeff.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo {

    private long total; //一共有多少条

    private int currentNum;  //当前页码

    private List<Blog> blogs;  //当前页的blogList

}
