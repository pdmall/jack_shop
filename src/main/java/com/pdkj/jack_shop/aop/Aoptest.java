package com.pdkj.jack_shop.aop;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.aop
 * @author lvchong
 * @date 2018/8/14 10:05
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;

/**
 * @author lvchong
 * @ClassName Aoptest
 * @Description 类描述
 * @date 2018/8/14
 */
public class Aoptest {
    @Before("test1()")
    public void testAop2(){
        System.out.println("这是程序执行前的函数");
    }

/*    @After("test1()")
    public void testAop1(){
        System.out.println("这是程序执行之后的函数");
    }

    @Around("test1()")
    public Object testAop6(ProceedingJoinPoint pro)throws Throwable{
        System.out.println("程序执行之前的函数");
        Object proccees=pro.proceed();//切点方法执行
        System.out.println("程序执行结束后的函数");
       return proccees;
    }
    */
}
