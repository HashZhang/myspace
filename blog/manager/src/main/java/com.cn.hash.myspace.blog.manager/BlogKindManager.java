package com.cn.hash.myspace.blog.manager;

import com.cn.hash.myspace.blog.common.domain.BlogKind;
import com.cn.hash.myspace.blog.common.domain.extend.BlogKindExtend;
import com.cn.hash.myspace.blog.dao.mapper.BlogKindMapper;
import com.cn.hash.myspace.common.Constants;
import com.cn.hash.myspace.common.utils.IdGenerator;
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
public class BlogKindManager {
    @Autowired
    private BlogKindMapper blogKindMapper;

    public String addKind(BlogKind blogKind) {
        String id = IdGenerator.getID(Constants.BLOG_SYSTEM);
        blogKind.setId(id);
        blogKindMapper.insertSelective(blogKind);
        return id;
    }


    public List<BlogKind> getAllKinds() {
        List<BlogKind> blogKinds = blogKindMapper.selectAllAvailableBySuperKindId("-1");
        List<BlogKind> blogKinds1 = new LinkedList<>();
        for (BlogKind blogKind : blogKinds) {
            BlogKindExtend blogKindExtend = new BlogKindExtend(blogKind);
            blogKindExtend.setChildKinds(getBlogKinds(blogKind));
            blogKinds1.add(blogKindExtend);
        }
        return blogKinds1;
    }

    private List<BlogKind> getBlogKinds(BlogKind blogKind) {
        List<BlogKind> strings = blogKindMapper.selectAllAvailableBySuperKindId(blogKind.getId());
        if (strings == null || strings.size() == 0) {
            return null;
        }
        List<BlogKind> blogKinds = new LinkedList<>();
        for (BlogKind blogKind1 : strings) {
            BlogKindExtend blogKindExtend = new BlogKindExtend(blogKind1);
            blogKindExtend.setChildKinds(getBlogKinds(blogKindExtend));
            blogKinds.add(blogKindExtend);
        }
        return blogKinds;
    }

    public boolean updateKind(BlogKind blogKind) {
        return blogKindMapper.updateByPrimaryKey(blogKind) > 0;
    }

    public boolean deleteKind(String kindId) {
        BlogKind blogKind = blogKindMapper.selectByPrimaryKey(kindId);
        blogKind.setIsDeleted(true);
        return blogKindMapper.updateByPrimaryKey(blogKind) > 0;
    }

    public BlogKind getKind(String kindId) {
        return blogKindMapper.selectByPrimaryKey(kindId);
    }
}
