package com.pdkj.jack_shop.aop;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.aop
 * @author lvchong
 * @date 2018/8/13 18:11
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lvchong
 * @ClassName ParameterAop
 * @Description 参数验证
 * @date 2018/8/13
 */
@Aspect
@Component
public class ParameterAop {
/*    @Pointcut(value = "execution(* com.pdkj.jack_shop.web.*.*(..))")
    public void test1(){}

    @Before("test1()")
    public void testAop2(){
        System.out.println("这是程序执行前的函数");
    }*/
}
