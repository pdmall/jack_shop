package com.pdkj.jack_shop.web;

import com.pdkj.jack_shop.model.User;
import com.pdkj.jack_shop.service.ShopTypeService;
import com.pdkj.jack_shop.service.*;
import org.springframework.http.HttpRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class BaseController {
    @Resource
    public UserService userService;

    @Resource
    public ShopService shopService;

    @Resource
    public ShopTypeService shopTypeService;

    @Resource
    public CouponService couponService;

    @Resource
    public BannerService bannerService;

    @Resource
    public UserOrderService userOrderService;



    public User getUser(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return (User) requestAttributes.getAttribute("user", 0);
    }
}
