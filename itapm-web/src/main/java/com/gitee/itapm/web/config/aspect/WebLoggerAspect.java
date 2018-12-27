package com.gitee.itapm.web.config.aspect;

/**
 * Created by jetty on 2018/12/9.
 */
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
@Aspect   //定义一个切面
@Configuration
@Order(-99)
public class WebLoggerAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebLoggerAspect.class);

    // 定义切点Pointcut
    @Pointcut("execution(com.gitee.itapm.utils.page.* com.gitee.itapm.web..*(..))")
    private void excudeService() {
    }

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = JSON.toJSONString(request.getParameterMap());
        long startTime=System.currentTimeMillis();
        logger.info("请求开始, url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString);
        Object result = pjp.proceed();
        logger.info("请求结束，返回数据{} 响应时长{}ms" ,JSON.toJSONString(result),System.currentTimeMillis()-startTime);
        return result;
    }



    @Pointcut("execution(java.lang.String com.gitee.itapm.web..*(..))")
    private void page() {
    }

    @Around("page()")
    public Object doAround2(ProceedingJoinPoint pjp) throws Throwable {

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString =JSON.toJSONString( request.getParameterMap());
        long startTime=System.currentTimeMillis();
        logger.info("请求开始, url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString);
        Object result = pjp.proceed();
        logger.info("请求结束 响应时长{}ms" ,System.currentTimeMillis()-startTime);
        return result;
    }
}
