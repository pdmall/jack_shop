package com.pdkj.jack_shop.web;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.web
 * @author lvchong
 * @date 2018/6/28 17:18
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.model.Coupon;
import com.pdkj.jack_shop.model.GroupBuy;
import com.pdkj.jack_shop.model.Goods;
import com.pdkj.jack_shop.util.sql.Pager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author lvchong
 * @ClassName CouponController
 * @Description 类描述
 * @date 2018/6/28
 */
@RestController
@RequestMapping("coupon")
public class CouponController extends BaseController {
    //获得代金卷详情
    @GetMapping("getCouponById")
    public Result getCouponById(Long id) throws CustomException {
        return ResultGenerator.genSuccessResult(couponService.getCouponById(id));
    }
    //获得商铺中的代金卷
    @GetMapping("getCouponByShopId")
    public Result getUsersCouponByShopId(Long shop_id,Integer coupon_state,Pager pager) throws CustomException {
        return ResultGenerator.genSuccessResult(couponService.getCouponByShopId(shop_id,coupon_state,pager));
    }
    //获得用户购买的代金卷
    @GetMapping("getCouponByUserId")
    public Result getUsersCouponByUserId(Pager pager) throws CustomException {
        return ResultGenerator.genSuccessResult(couponService.getCouponByUserId(getUser().getId(),pager));
    }
    //添加一个代金卷
    @PostMapping("addCoupon")
    public Result addCoupon(Coupon coupon) throws CustomException {
        return ResultGenerator.genSuccessResult(couponService.addCoupon(coupon));
    }



}
