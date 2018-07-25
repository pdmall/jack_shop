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
import com.pdkj.jack_shop.util.sql.Pager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lvchong
 * @ClassName CouponController
 * @Description 类描述
 * @date 2018/6/28
 */
@RestController
@RequestMapping("shareOrgin")
public class ShareOrginController extends BaseController {
    //获得我的二级
    @GetMapping("getMyLevel2")
    public Result getMyLevel2(Pager pager) throws CustomException {
        return ResultGenerator.genSuccessResult(shareOrginService.getMyLevel2(getUser().getId(),pager));
    }
    //获得我的一级
        @GetMapping("getMyLevel1")
    public Result getMyLevel1(Pager pager) throws CustomException {
        return ResultGenerator.genSuccessResult(shareOrginService.getMyLevel1(getUser().getId(),pager));
    }
    //获得我的二级的奖励值
    @GetMapping("getMyLevel2Money")
    public Result getMyLevel2Money() throws CustomException {
        return ResultGenerator.genSuccessResult(shareOrginService.getMyLevel2Money(getUser().getId()));
    }
    //获得我的一级奖励值
    @GetMapping("getMyLevel1Money")
    public Result getMyLevel1Money() throws CustomException {
        return ResultGenerator.genSuccessResult(shareOrginService.getMyLevel1Money(getUser().getId()));
    }
    //获得我的全部邀请信息
    @GetMapping("getMyAll")
    public Result getMyAll() throws CustomException {
        return ResultGenerator.genSuccessResult(shareOrginService.getMyAll(getUser().getId()));
    }
}
