package com.cn.hash.myspace.blog.common.domain.extend;

import com.cn.hash.myspace.blog.common.domain.BlogComment;

import java.util.List;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/11/11.
 */
public class BlogCommentExtend extends BlogComment {
    public List<BlogComment> getChildBlogComment() {
        return childBlogComment;
    }

    public void setChildBlogComment(List<BlogComment> childBlogComment) {
        this.childBlogComment = childBlogComment;
    }

    private List<BlogComment> childBlogComment;

    public BlogCommentExtend() {
    }

    public BlogCommentExtend(BlogComment blogComment) {
        this.setBlogId(blogComment.getBlogId());
        this.setContent(blogComment.getContent());
        this.setCreatedBy(blogComment.getCreatedBy());
        this.setCreatedTime(blogComment.getCreatedTime());
        this.setId(blogComment.getId());
        this.setIsDeleted(blogComment.getIsDeleted());
        this.setSuperCommentId(blogComment.getSuperCommentId());
    }
}
