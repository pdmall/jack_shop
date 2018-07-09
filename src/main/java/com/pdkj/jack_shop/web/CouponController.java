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
import com.pdkj.jack_shop.model.IsPassCoupon;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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


    @GetMapping("getCouponById")
    public Result getCouponById(Long id) throws CustomException {
        return ResultGenerator.genSuccessResult(couponService.getCouponById(id));
    }

    @GetMapping("getCouponByUserId")
    public Result getCouponByUserId(String token,Integer coupon_state) throws CustomException {
        return ResultGenerator.genSuccessResult(couponService.getCouponByUserId(getUser().getId(),coupon_state));
    }

    @GetMapping("getCouponByShopId")
    public Result getCouponByShopId(Long shopId,int coupon_state) throws CustomException {
        List<Map<String, Object>> list = couponService.getCouponByShopId(shopId,coupon_state);
        return ResultGenerator.genSuccessResult(list);
    }

    @GetMapping("addCoupon")
    public Result addCoupon(IsPassCoupon coupon) throws CustomException {
        return ResultGenerator.genSuccessResult(couponService.addCoupon(coupon));

    }



}
