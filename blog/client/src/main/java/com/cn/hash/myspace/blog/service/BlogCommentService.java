package com.cn.hash.myspace.blog.service;

import com.cn.hash.myspace.blog.common.domain.BlogComment;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/11/11.
 */
public interface BlogCommentService {
    String addComment(BlogComment blogComment) throws Exception;

    boolean getCommentsByBlogId(String blogId) throws Exception;

    boolean deleteComment(String commentId) throws Exception;
}
