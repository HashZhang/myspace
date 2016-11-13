package com.cn.hash.myspace.blog.dao.mapper;

import com.cn.hash.myspace.blog.common.domain.Blog;
import com.github.pagehelper.Page;

import java.util.List;

public interface BlogMapper {
    int deleteByPrimaryKey(String id);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(String id);
    
    Page<Blog> selectAllAvailable();

    Page<Blog> selectAllAvailableByKindId(String kindId);

    Page<Blog> selectAllAvailableByTagId(String tagId);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKeyWithBLOBs(Blog record);

    int updateByPrimaryKey(Blog record);
}