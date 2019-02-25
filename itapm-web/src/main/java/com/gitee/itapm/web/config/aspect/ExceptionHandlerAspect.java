package com.gitee.itapm.web.config.aspect;

/**
 * Created by jetty on 2018/12/9.
 */

import com.gitee.itapm.utils.enums.RespCodeEnum;
import com.gitee.itapm.utils.exception.ItapmRuntimeException;
import com.gitee.itapm.utils.page.ResultBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Aspect   //定义一个切面
@Configuration
@Order(-55)
public class ExceptionHandlerAspect {


    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAspect.class);


    // 定义切点Pointcut
    @Pointcut("execution(com.gitee.itapm.utils.page.* com.gitee.itapm.web..*(..))")
    private void excudeService() {
    }

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        ResultBean<?> result;
        try {
            result = (ResultBean<?>) pjp.proceed();
            result.setCode(RespCodeEnum.SUCCESS.getCode());
            result.setMessage(RespCodeEnum.SUCCESS.getMessage());
        } catch (Throwable e) {
            result = handlerException(pjp, e);
        }
        return result;
    }

    private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        ResultBean<?> result = new ResultBean();
        LOGGER.error(pjp.getSignature() + " error ", e);
        if(e instanceof ItapmRuntimeException){
            ItapmRuntimeException atapmRuntimeException=(ItapmRuntimeException)e;
            result.setCode(atapmRuntimeException.getCode());
            result.setMessage(atapmRuntimeException.getMessage());
        }else {
            result.setCode(RespCodeEnum.FAIL.getCode());
            result.setMessage(RespCodeEnum.FAIL.getMessage());
        }
        return result;
    }
}
