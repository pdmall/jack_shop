package com.pdkj.jack_shop.web;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.web
 * @author lvchong
 * @date 2018/7/7 15:37
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.model.Goods;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lvchong
 * @ClassName ShopGoodsController
 * @Description 类描述
 * @date 2018/7/7
 */
@RestController
@RequestMapping("goods")
public class GoodsController extends BaseController{

    @GetMapping("getGroupBuyGoods")
    public Result getGroupBuyGoods(Long coupon_id) throws CustomException {
        return ResultGenerator.genSuccessResult(goodsService.getGroupBuyGoods(coupon_id));
    }

    @GetMapping("getShopGoods")
    public Result getShopGoods(Long shop_id) throws CustomException {
        return ResultGenerator.genSuccessResult(goodsService.getShopGoods(shop_id));
    }

    @GetMapping("addGoods")
    public Result addGoods(Goods goods) throws CustomException {
        return ResultGenerator.genSuccessResult(goodsService.addGoods(goods));
    }

}
