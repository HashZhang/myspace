package com.cn.hash.myspace.blog.manager;

import com.cn.hash.myspace.blog.common.BlogConstants;
import com.cn.hash.myspace.blog.common.domain.BlogComment;
import com.cn.hash.myspace.blog.common.domain.extend.BlogCommentExtend;
import com.cn.hash.myspace.blog.dao.mapper.BlogCommentMapper;
import com.cn.hash.myspace.common.Constants;
import com.cn.hash.myspace.common.utils.IdGenerator;
import com.cn.hash.myspace.es.ESClient;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/11/11.
 */
@Component
public class BlogCommentManager {
    @Autowired
    private BlogCommentMapper blogCommentMapper;
    @Autowired
    private ESClient esClient;

    public String addComment(BlogComment blogComment) {
        String id = IdGenerator.getID(Constants.BLOG_SYSTEM);
        blogComment.setId(id);
        blogCommentMapper.insertSelective(blogComment);
        blogComment = blogCommentMapper.selectByPrimaryKey(id);
        esClient.addOrUpdateDocument(Constants.BLOG_SYSTEM, BlogConstants.COMMENT, id, blogComment);
        return id;
    }


    public PageInfo<BlogComment> getCommentsByBlogId(String blogId, int pageNumber) {
        PageHelper.startPage(pageNumber, 10);
        Page<BlogComment> blogComments = blogCommentMapper.selectAllAvailableByBlogId(blogId);
        PageInfo<BlogComment> blogCommentPageInfo = blogComments.toPageInfo();
        List<BlogComment> blogComments1 = new LinkedList<>();
        for (BlogComment blogComment : blogCommentPageInfo.getList()) {
            BlogCommentExtend blogCommentExtend = new BlogCommentExtend(blogComment);
            blogCommentExtend.setChildBlogComment(getCommentExtends(blogComment));
            blogComments1.add(blogCommentExtend);
        }
        blogCommentPageInfo.setList(blogComments1);
        return blogCommentPageInfo;
    }

    private List<BlogComment> getCommentExtends(BlogComment blogComment) {
        List<BlogComment> blogComments = blogCommentMapper.selectAllAvailableBySuperCommentId(blogComment.getId());
        if (blogComments == null || blogComments.size() == 0) {
            return null;
        }
        List<BlogComment> blogComments1 = new LinkedList<>();
        for (BlogComment blogComment1 : blogComments) {
            BlogCommentExtend blogCommentExtend = new BlogCommentExtend(blogComment1);
            blogCommentExtend.setChildBlogComment(getCommentExtends(blogCommentExtend));
            blogComments1.add(blogCommentExtend);
        }
        return blogComments1;
    }

    public boolean deleteComment(String commentId) {
        BlogComment blogComment = blogCommentMapper.selectByPrimaryKey(commentId);
        blogComment.setIsDeleted(true);
        return blogCommentMapper.updateByPrimaryKey(blogComment) > 0;
    }
}
