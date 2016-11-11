package com.cn.hash.myspace.blog.service;

import com.cn.hash.myspace.blog.common.domain.Blog;
import com.cn.hash.myspace.blog.common.domain.BlogKind;
import com.cn.hash.myspace.blog.common.domain.BlogTag;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/11/11.
 */
public interface BlogService {
    String addBlog(Blog blog, List<BlogKind> blogKinds, List<BlogTag> blogTags) throws Exception;

    boolean updateBlogContent(Blog blog) throws Exception;

    boolean updateBlogKind(String blogId, List<BlogKind> blogKinds) throws Exception;

    boolean updateBlogTags(String blogId, List<BlogTag> blogTags) throws Exception;

    boolean deleteBlog(String blogId) throws Exception;

    Blog getBlog(String BlogId, String ip) throws Exception;

    PageInfo<Blog> getAllBlog(int pageNumber) throws Exception;

    PageInfo<Blog> getAllBlogByKind(String KindId, int pageNumber) throws Exception;

    PageInfo<Blog> getAllBlogByTag(String tagId, int pageNumber) throws Exception;
}
