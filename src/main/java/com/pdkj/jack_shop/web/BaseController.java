package com.pdkj.jack_shop.web;

import com.pdkj.jack_shop.model.User;
import com.pdkj.jack_shop.service.ShopTypeService;
import com.pdkj.jack_shop.service.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;

public class BaseController {
    @Resource
    public UserService userService;

    @Resource
    public ShopService shopService;
    @Resource
    public LabelService labelService;
    @Resource
    public ShopTypeService shopTypeService;

    @Resource
    public CouponService couponService;

    @Resource
    public UserWalletService userWalletService;

    @Resource
    public BannerService bannerService;

    @Resource
    public UserOrderService userOrderService;

    @Resource
    public WxService wxService;

    @Resource
    public WxPayService wxPayService;

    @Resource
    public SignInService signInService;

    @Resource
    public ShopCommentService shopCommentService;
    @Resource
    public GoodsService goodsService;
    @Resource
    public GroupBuyService groupBuyService;
    @Resource
    public UserConcernService userConcernService;
    @Resource
    public PhotoService photoService;
    @Resource
    public AnalysisService analysisService;
    @Resource
    public ParameterService parameterService;
    @Resource
    public ShareOrginService shareOrginService;
    @Resource
    public FlowMoneyService flowMoneyService;
    @Resource
    public PayService payService;

    public User getUser() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return (User) requestAttributes.getAttribute("user", 0);
    }
}
