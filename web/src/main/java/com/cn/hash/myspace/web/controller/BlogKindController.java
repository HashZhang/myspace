package com.cn.hash.myspace.web.controller;

import com.cn.hash.myspace.blog.common.domain.BlogKind;
import com.cn.hash.myspace.blog.service.BlogKindService;
import com.cn.hash.myspace.common.dto.JsonResult;
import com.cn.hash.myspace.common.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/11/11.
 */
@Controller
public class BlogKindController {
    private final static Logger log = LoggerFactory.getLogger(BlogKindController.class);
    @Autowired
    private BlogKindService blogKindService;

    @ResponseBody
    @RequestMapping(value = "/manage/blog/kind/add", method = RequestMethod.POST)
    public JsonResult<Boolean> addBlogKind(@Valid @RequestBody BlogKind kind,BindingResult bindingResult) throws Exception {
        JsonResult<Boolean> result = new JsonResult<>();
        try {
            blogKindService.addKind(kind);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
        result.setData(true);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/manage/blog/kind/update", method = RequestMethod.POST)
    public JsonResult<Boolean> updateBlogKind(@Valid @RequestBody BlogKind kind,BindingResult bindingResult) throws Exception {
        JsonResult<Boolean> result = new JsonResult<>();
        try {
            blogKindService.updateKind(kind);
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
        result.setData(true);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/manage/blog/kind/delete", method = RequestMethod.POST)
    public JsonResult<Boolean> deleteBlogKind(@Valid @RequestBody BlogKind kind,BindingResult bindingResult) throws Exception {
        JsonResult<Boolean> result = new JsonResult<>();
        try {
            blogKindService.deleteKind(kind.getId());
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
        result.setData(true);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/blog/kind/getAll", method = RequestMethod.GET)
    public JsonResult<List<BlogKind>> getAllBlogKind() throws Exception {
        JsonResult<List<BlogKind>> result = new JsonResult<>();
        try {
            result.setData(blogKindService.getAllKinds());
        } catch (Exception e) {
            log.warn(ExceptionUtils.getStackTrace(e));
            throw new Exception(e);
        }
        return result;
    }
}
