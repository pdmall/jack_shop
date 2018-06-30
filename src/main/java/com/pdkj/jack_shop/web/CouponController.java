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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lvchong
 * @ClassName CouponController
 * @Description 类描述
 * @date 2018/6/28
 */
@RestController
@RequestMapping("coupon")
public class CouponController extends BaseController {
    @GetMapping("getCouponByShopId")
    public Result getControllerByShopId(@RequestParam(value = "shopId") Long shopId) throws CustomException {
        return ResultGenerator.genSuccessResult(couponService.getControllerByShopId(shopId));
    }

    @GetMapping("getCouponById")
    public Result getControllerById(@RequestParam(value = "id") Long id) throws CustomException {
        return ResultGenerator.genSuccessResult(couponService.getControllerById(id));
    }

    @GetMapping("getCouponByUserId")
    public Result getControllerByUserId(@RequestParam(value = "userId") Long userId) throws CustomException {
        return ResultGenerator.genSuccessResult(couponService.getControllerByUserId(userId));
    }

}
