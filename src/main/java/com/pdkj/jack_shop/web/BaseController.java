package com.pdkj.jack_shop.web;

import com.pdkj.jack_shop.service.*;
import com.pdkj.jack_shop.service.impl.*;

import javax.annotation.Resource;

public class BaseController {
    @Resource
    public CouponServiceImpl couponService;
    @Resource
    public CouponItemServiceImpl couponItemService;
    @Resource
    public CouponItemUnitServiceImpl couponItemUnitService;
    @Resource
    public ShopCommentServiceImpl shopCommentService;
    @Resource
    public ShopServiceImpl shopService;
    @Resource
    public ShopTypeServiceImpl shopTypeService;
    @Resource
    public ShopTypeRelServiceImpl shopTypeRelService;
    @Resource
    public UserConcernServiceImpl userConcernService;
    @Resource
    public UserServiceImpl userService;
    @Resource
    public UserOrderServiceImpl userOrderService;
    @Resource
    public UserRoleServiceImpl userRoleService;
    @Resource
    public UserShopRelServiceImpl userShopRelService;
    @Resource
    public UserWalletServiceImpl userWalletService;
}
