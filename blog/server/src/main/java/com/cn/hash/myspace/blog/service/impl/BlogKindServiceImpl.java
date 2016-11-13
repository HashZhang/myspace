package com.cn.hash.myspace.blog.service.impl;

import com.cn.hash.myspace.blog.common.domain.BlogKind;
import com.cn.hash.myspace.blog.manager.BlogKindManager;
import com.cn.hash.myspace.blog.service.BlogKindService;
import com.cn.hash.myspace.common.utils.ExceptionUtils;
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
public class BlogKindServiceImpl implements BlogKindService {
    private final static Logger log = LoggerFactory.getLogger(BlogServiceImpl.class);

    @Autowired
    private BlogKindManager blogKindManager;

    @Override
    public String addKind(BlogKind blogKind) throws Exception {
        try {
            return blogKindManager.addKind(blogKind);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public List<BlogKind> getAllKinds() throws Exception {
        try {
            return blogKindManager.getAllKinds();
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public BlogKind getKind(String kindId) throws Exception {
        try {
            return blogKindManager.getKind(kindId);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public boolean updateKind(BlogKind blogKind) throws Exception {
        try {
            return blogKindManager.updateKind(blogKind);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }

    @Override
    public boolean deleteKind(String kindId) throws Exception {
        try {
            return blogKindManager.deleteKind(kindId);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
    }
}
