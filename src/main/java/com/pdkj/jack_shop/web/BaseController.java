package com.pdkj.jack_shop.web;

import com.pdkj.jack_shop.service.*;

import javax.annotation.Resource;

public class BaseController {
    @Resource
    public CouponService couponService;
    @Resource
    public CouponItemService couponItemService;
    @Resource
    public CouponItemUnitService couponItemUnitService;
    @Resource
    public ShopCommentService shopCommentService;
    @Resource
    public ShopService shopService;
    @Resource
    public ShopTypeService shopTypeService;
    @Resource
    public ShopTypeRelService shopTypeRelService;
    @Resource
    public UserConcernService userConcernService;
    @Resource
    public UserService userService;
    @Resource
    public UserOrderService userOrderService;
    @Resource
    public UserRoleService userRoleService;
    @Resource
    public UserShopRelService userShopRelService;
    @Resource
    public UserWalletService userWalletService;
}
