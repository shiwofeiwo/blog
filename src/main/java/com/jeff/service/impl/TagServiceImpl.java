package com.jeff.service.impl;

import com.jeff.mapper.TagMapper;
import com.jeff.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Resource
    private TagMapper tagMapper;

    @Override
    public List<String> getAllTagName() {
        return tagMapper.getAllTagName();
    }

}
