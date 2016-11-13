package com.cn.hash.myspace.web.aspect;

import com.cn.hash.myspace.common.dto.JsonResult;
import com.cn.hash.myspace.common.utils.StrUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

/**
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/11/12.
 */
@Service
@Aspect
public class DomainValidator {
    private final static Logger log = LoggerFactory.getLogger(DomainValidator.class);

    @Around("execution(* com.cn.hash.myspace.web.controller.*.*(..)")
    public Object cachePcompSoftwareHasRightList(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        for (int i = 0; i < args.length; i++) {
            if(args[i] instanceof BindingResult){
                if(((BindingResult) args[i]).hasErrors()){
                    JsonResult jsonResult = new JsonResult();
                    jsonResult.setMessage(((BindingResult) args[i]).getAllErrors().iterator().next().getDefaultMessage());
                    return jsonResult;
                }
            }
        }
        return pjp.proceed();
    }
}
