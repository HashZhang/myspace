package com.cn.hash.myspace.blog.dao.mapper;

import com.cn.hash.myspace.blog.common.domain.BlogTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogTagMapper {
    int deleteByPrimaryKey(String id);

    int insert(BlogTag record);

    int insertSelective(BlogTag record);

    BlogTag selectByPrimaryKey(String id);

    int countIfExistsBlogRelToTag(@Param("blogId")String blogId, @Param("tagId")String tagId);

    int bindBlogWithTag(@Param("blogId")String blogId, @Param("tagId")String tagId);

    int unbindBlogAndTag(@Param("blogId")String blogId, @Param("tagId")String tagId);
    
    int updateByPrimaryKeySelective(BlogTag record);

    int updateByPrimaryKey(BlogTag record);

    List<String> selectAllTagIdByBlogId(String tagId);
}