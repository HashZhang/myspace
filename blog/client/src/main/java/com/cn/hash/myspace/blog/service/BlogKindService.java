package com.cn.hash.myspace.blog.service;

import com.cn.hash.myspace.blog.common.domain.BlogKind;

import java.util.List;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/11/11.
 */
public interface BlogKindService {
    String addKind(BlogKind blogKind) throws Exception;

    List<BlogKind> getAllKinds() throws Exception;

    BlogKind getKind(String kindId) throws Exception;

    boolean updateKind(BlogKind blogKind) throws Exception;

    boolean deleteKind(String kindId) throws Exception;
}
