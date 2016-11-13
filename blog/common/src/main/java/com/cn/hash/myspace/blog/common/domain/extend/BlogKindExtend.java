package com.cn.hash.myspace.blog.common.domain.extend;

import com.cn.hash.myspace.blog.common.domain.BlogKind;

import java.util.List;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/11/11.
 */
public class BlogKindExtend extends BlogKind{
    public BlogKindExtend(BlogKind blogKind) {
        this.setCreatedBy(blogKind.getCreatedBy());
        this.setCreatedTime(blogKind.getCreatedTime());
        this.setId(blogKind.getId());
        this.setIsDeleted(blogKind.getIsDeleted());
        this.setIcon(blogKind.getIcon());
        this.setIsHomed(blogKind.getIsHomed());
        this.setModifiedBy(blogKind.getModifiedBy());
        this.setModifiedTime(blogKind.getModifiedTime());
        this.setName(blogKind.getName());
        this.setSuperKindId(blogKind.getSuperKindId());
    }
    public BlogKindExtend() {
    }
    public List<BlogKind> getChildKinds() {
        return childKinds;
    }

    public void setChildKinds(List<BlogKind> childKinds) {
        this.childKinds = childKinds;
    }

    private List<BlogKind> childKinds;
}
