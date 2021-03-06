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
import com.pdkj.jack_shop.core.ParameterException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.model.Goods;
import com.pdkj.jack_shop.model.GoodsType;
import com.pdkj.jack_shop.util.Ognl;
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

    //查询团购卷中的全部商品
    @GetMapping("getGroupBuyGoods")
    public Result getGroupBuyGoods(Long coupon_id) throws CustomException {
        if(Ognl.isEmpty(coupon_id))
            throw new ParameterException("参数异常");
        return ResultGenerator.genSuccessResult(goodsService.getGroupBuyGoods(coupon_id));
    }
    //查询单个商铺的所有商品
    @GetMapping("getShopGoods")
    public Result getShopGoods(Long shop_id) throws CustomException {
        if(Ognl.isEmpty(shop_id))
            throw new ParameterException("参数异常");
        return ResultGenerator.genSuccessResult(goodsService.getShopGoods(shop_id));
    }
    //添加一个商品
    @GetMapping("addGoods")
    public Result addGoods(Goods goods) throws CustomException {
        return ResultGenerator.genSuccessResult(goodsService.addGoods(goods,getUser().getId()));
    }
    //获得所有商品的单位
    @GetMapping("getGoodsUnit")
    public Result getGoodsUnit() throws CustomException {
        return ResultGenerator.genSuccessResult(goodsService.getGoodsUnit());
    }
    //获得商铺中商品的分类
    @GetMapping("getGoodsType")
    public Result getGoodsType(Long shop_id) throws CustomException {
        if(Ognl.isEmpty(shop_id))
            throw new ParameterException("参数异常");
        return ResultGenerator.genSuccessResult(goodsService.getGoodsType(shop_id));
    }
    //添加商铺中商品的分类
    @GetMapping("addGoodsType")
    public Result addGoodsType(GoodsType goodsType) throws CustomException {
        return ResultGenerator.genSuccessResult(goodsService.addGoodsType(goodsType));
    }
    //获得热门菜系
    @GetMapping("getHotGoods")
    public Result getHotGoods(Long shop_id) throws CustomException {
        if(Ognl.isEmpty(shop_id))
            throw new ParameterException("参数异常");
        return ResultGenerator.genSuccessResult(goodsService.getHotGoods(shop_id));
    }
}
