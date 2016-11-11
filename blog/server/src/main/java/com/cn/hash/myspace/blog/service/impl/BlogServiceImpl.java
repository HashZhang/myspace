package com.cn.hash.myspace.blog.service.impl;

import com.cn.hash.myspace.blog.common.domain.Blog;
import com.cn.hash.myspace.blog.common.domain.BlogKind;
import com.cn.hash.myspace.blog.common.domain.BlogTag;
import com.cn.hash.myspace.blog.service.BlogService;
import com.cn.hash.myspace.common.utils.ExceptionUtils;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/11/11.
 */
@Service
public class BlogServiceImpl implements BlogService {
    private final static Logger log = LoggerFactory.getLogger(BlogServiceImpl.class);

    @Autowired
    private BlogManager

    @Override
    public String addBlog(Blog blog, List<BlogKind> blogKinds, List<BlogTag> blogTags) throws Exception {
        try {
            return
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public boolean updateBlogContent(Blog blog) throws Exception {
        return false;
    }

    @Override
    public boolean updateBlogKind(String blogId, List<BlogKind> blogKinds) throws Exception {
        return false;
    }

    @Override
    public boolean updateBlogTags(String blogId, List<BlogTag> blogTags) throws Exception {
        return false;
    }

    @Override
    public boolean deleteBlog(String blogId) throws Exception {
        return false;
    }

    @Override
    public Blog getBlog(String BlogId, String ip) throws Exception {
        return null;
    }

    @Override
    public PageInfo<Blog> getAllBlog(int pageNumber) throws Exception {
        return null;
    }

    @Override
    public PageInfo<Blog> getAllBlogByKind(String KindId, int pageNumber) throws Exception {
        return null;
    }

    @Override
    public PageInfo<Blog> getAllBlogByTag(String tagId, int pageNumber) throws Exception {
        return null;
    }
}
