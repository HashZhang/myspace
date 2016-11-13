package com.cn.hash.myspace.blog.manager;

import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import com.cn.hash.myspace.blog.common.BlogConstants;
import com.cn.hash.myspace.blog.common.domain.Blog;
import com.cn.hash.myspace.blog.common.domain.BlogKind;
import com.cn.hash.myspace.blog.common.domain.BlogTag;
import com.cn.hash.myspace.blog.dao.mapper.BlogKindMapper;
import com.cn.hash.myspace.blog.dao.mapper.BlogMapper;
import com.cn.hash.myspace.blog.dao.mapper.BlogTagMapper;
import com.cn.hash.myspace.common.Constants;
import com.cn.hash.myspace.common.utils.IdGenerator;
import com.cn.hash.myspace.es.ESClient;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/11/11.
 */
@Component
public class BlogManager {
    private ConcurrentHashMap<String, ConcurrentHashSet<String>> ipVisitMap = new ConcurrentHashMap<>();
    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private BlogKindMapper blogKindMapper;
    @Autowired
    private BlogTagMapper blogTagMapper;
    @Autowired
    private ESClient esClient;

    public void saveVisitCount() {
        try {
            reentrantReadWriteLock.writeLock().lock();
            for (String blogId : ipVisitMap.keySet()) {
                Blog blog = blogMapper.selectByPrimaryKey(blogId);
                blog.setCount(blog.getCount() + ipVisitMap.get(blogId).size());
            }
            ipVisitMap = new ConcurrentHashMap<>();
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }

    public String addBlog(Blog blog, List<String> blogKindIds, List<BlogTag> blogTags) {
        String id = IdGenerator.getID(Constants.BLOG_SYSTEM);
        blog.setId(id);
        blogMapper.insertSelective(blog);
        blog = blogMapper.selectByPrimaryKey(id);
        for (String blogKind : blogKindIds) {
            BlogKind blogKind1 = blogKindMapper.selectByPrimaryKey(blogKind);
            if (blogKindMapper.countIfExistsBlogRelToKind(id, blogKind1.getId()) == 0) {
                blogKindMapper.bindBlogWithKind(id, blogKind1.getId());
            }
        }
        for (BlogTag blogTag : blogTags) {
            BlogTag blogTag1 = blogTagMapper.selectByName(blogTag.getName());
            if (blogTag1 == null) {
                String tagId = IdGenerator.getID(Constants.BLOG_SYSTEM);
                blogTag.setId(tagId);
                blogTagMapper.insertSelective(blogTag);
                blogTag = blogTagMapper.selectByPrimaryKey(tagId);
            } else {
                blogTag = blogTag1;
            }
            blogTagMapper.bindBlogWithTag(id, blogTag.getId());
        }
        esClient.addOrUpdateDocument(Constants.BLOG_SYSTEM, BlogConstants.BLOG, id, blog);
        return id;
    }


    public boolean updateBlogContent(Blog blog) {
        boolean b = blogMapper.updateByPrimaryKeyWithBLOBs(blog) > 0;
        if (b) {
            esClient.addOrUpdateDocument(Constants.BLOG_SYSTEM, BlogConstants.BLOG, blog.getId(), blog);
        }
        return b;
    }

    public boolean updateBlogKind(String blogId, List<String> blogKindIds) {
        List<String> kindIds = blogKindMapper.selectAllKindIdByBlogId(blogId);
        for (String kindId : kindIds) {
            blogKindMapper.unbindBlogAndKind(blogId, kindId);
        }
        for (String blogKindId : blogKindIds) {
            blogKindMapper.bindBlogWithKind(blogId, blogKindId);
        }
        return true;
    }

    public boolean updateBlogTags(String blogId, List<BlogTag> blogTags) {
        List<String> tagIds = blogTagMapper.selectAllTagIdByBlogId(blogId);
        for (String tagId : tagIds) {
            blogTagMapper.unbindBlogAndTag(blogId, tagId);
        }
        for (BlogTag blogTag : blogTags) {
            BlogTag blogTag1 = blogTagMapper.selectByName(blogTag.getName());
            if (blogTag1 == null) {
                String tagId = IdGenerator.getID(Constants.BLOG_SYSTEM);
                blogTag.setId(tagId);
                blogTagMapper.insertSelective(blogTag);
                blogTag = blogTagMapper.selectByPrimaryKey(tagId);
            } else {
                blogTag = blogTag1;
            }
            blogTagMapper.bindBlogWithTag(blogId, blogTag.getId());
        }
        return true;
    }

    public boolean deleteBlog(String blogId) {
        Blog blog = blogMapper.selectByPrimaryKey(blogId);
        blog.setIsDeleted(true);
        boolean b = blogMapper.updateByPrimaryKey(blog) > 0;
        if (b) {
            esClient.addOrUpdateDocument(Constants.BLOG_SYSTEM, BlogConstants.BLOG, blog.getId(), blog);
        }
        return b;
    }

    public Blog getBlog(String blogId, String ip) {
        try {
            reentrantReadWriteLock.readLock().lock();
            if (ipVisitMap.get(blogId) == null) {
                ConcurrentHashSet concurrentHashSet = new ConcurrentHashSet();
                ipVisitMap.put(blogId, concurrentHashSet);
            }
            ipVisitMap.get(blogId).add(ip);
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
        return blogMapper.selectByPrimaryKey(blogId);
    }

    public PageInfo<Blog> getAllBlog(int pageNumber) {
        PageHelper.startPage(pageNumber, 10);
        return blogMapper.selectAllAvailable().toPageInfo();
    }

    public PageInfo<Blog> getAllBlogByKind(String kindId, int pageNumber) {
        PageHelper.startPage(pageNumber, 10);
        return blogMapper.selectAllAvailableByKindId(kindId).toPageInfo();
    }

    public PageInfo<Blog> getAllBlogByTag(String tagId, int pageNumber) {
        PageHelper.startPage(pageNumber, 10);
        return blogMapper.selectAllAvailableByTagId(tagId).toPageInfo();
    }
}
