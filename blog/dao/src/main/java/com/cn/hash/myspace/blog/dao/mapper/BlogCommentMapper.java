package com.cn.hash.myspace.blog.dao.mapper;

import com.cn.hash.myspace.blog.common.domain.BlogComment;
import com.github.pagehelper.Page;

import java.util.List;

public interface BlogCommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(BlogComment record);

    int insertSelective(BlogComment record);

    BlogComment selectByPrimaryKey(String id);

    Page<BlogComment> selectAllAvailable();

    Page<BlogComment> selectAllAvailableByBlogId(String blogId);

    List<BlogComment> selectAllAvailableBySuperCommentId(String commentId);

    int updateByPrimaryKeySelective(BlogComment record);

    int updateByPrimaryKeyWithBLOBs(BlogComment record);

    int updateByPrimaryKey(BlogComment record);
}