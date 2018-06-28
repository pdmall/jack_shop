package com.pdkj.jack_shop.web;

import com.pdkj.jack_shop.service.ShopTypeService;
import com.pdkj.jack_shop.service.*;

import javax.annotation.Resource;

public class BaseController {
    @Resource
    public UserService userService;

    @Resource
    public ShopService shopService;

    @Resource
    public ShopTypeService shopTypeService;

    @Resource
    public CouponService couponService;
}
