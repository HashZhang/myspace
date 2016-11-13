package com.cn.hash.myspace.blog.service.impl;

import com.cn.hash.myspace.blog.common.domain.BlogComment;
import com.cn.hash.myspace.blog.manager.BlogCommentManager;
import com.cn.hash.myspace.blog.manager.BlogKindManager;
import com.cn.hash.myspace.blog.service.BlogCommentService;
import com.cn.hash.myspace.common.utils.ExceptionUtils;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/11/11.
 */
@Service
public class BlogCommentServiceImpl implements BlogCommentService {
    private final static Logger log = LoggerFactory.getLogger(BlogServiceImpl.class);

    @Autowired
    private BlogCommentManager blogCommentManager;

    @Override
    public String addComment(BlogComment blogComment) throws Exception {
        try {
            return blogCommentManager.addComment(blogComment);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public PageInfo<BlogComment> getCommentsByBlogId(String blogId, int pageNumber) throws Exception {
        try {
            return blogCommentManager.getCommentsByBlogId(blogId,pageNumber);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public boolean deleteComment(String commentId) throws Exception {
        try {
            return blogCommentManager.deleteComment(commentId);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }
}
