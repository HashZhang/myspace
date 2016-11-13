package com.cn.hash.myspace.blog.dao.mapper;

import com.cn.hash.myspace.blog.common.domain.BlogKind;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogKindMapper {
    int deleteByPrimaryKey(String id);

    int insert(BlogKind record);

    int insertSelective(BlogKind record);

    BlogKind selectByPrimaryKey(String id);

    int countIfExistsBlogRelToKind(@Param("blogId")String blogId, @Param("kindId")String kindId);

    int bindBlogWithKind(@Param("blogId")String blogId, @Param("kindId")String kindId);

    int unbindBlogAndKind(@Param("blogId")String blogId, @Param("kindId")String kindId);

    List<BlogKind> selectAllAvailableBySuperKindId(String kindId);

    int updateByPrimaryKeySelective(BlogKind record);

    int updateByPrimaryKey(BlogKind record);

    List<String> selectAllKindIdByBlogId(String blogId);
}