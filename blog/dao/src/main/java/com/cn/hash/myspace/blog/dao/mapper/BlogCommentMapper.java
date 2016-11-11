package com.cn.hash.myspace.blog.dao.mapper;

import com.cn.hash.myspace.blog.common.domain.BlogComment;

import java.util.List;

public interface BlogCommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(BlogComment record);

    int insertSelective(BlogComment record);

    BlogComment selectByPrimaryKey(String id);

    List<BlogComment> selectAllAvailable();

    List<BlogComment> selectAllAvailableByBlogId(String blogId);

    List<BlogComment> selectAllAvailableBySuperCommentId(String commentId);

    int updateByPrimaryKeySelective(BlogComment record);

    int updateByPrimaryKeyWithBLOBs(BlogComment record);

    int updateByPrimaryKey(BlogComment record);
}