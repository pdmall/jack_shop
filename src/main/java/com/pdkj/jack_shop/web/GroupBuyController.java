package com.pdkj.jack_shop.web;

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.model.IsPassGroupBuy;
import com.pdkj.jack_shop.util.sql.Pager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by CodeGenerator on 2018/06/26.
 */
@RestController
@RequestMapping("groupBuy")
public class GroupBuyController extends BaseController {
    //添加优惠券
    @GetMapping("addGroupBuy")
    public Result addGroupBuy(IsPassGroupBuy groupBuy , String goods_ids) throws CustomException {
        return ResultGenerator.genSuccessResult(groupBuyService.addGroupBuy(groupBuy,goods_ids));
    }
    //审核套餐接口
    @GetMapping("getLog")
    public Result getLog(Long id) throws CustomException {
        return ResultGenerator.genSuccessResult(groupBuyService.getLog(id));
    }
    //获得商铺的审核通过的套餐
    @GetMapping("getGroupBuyByShopId")
    public Result getGroupBuyByShopId(Long shop_id , Integer state,Pager pager) throws CustomException {
        return ResultGenerator.genSuccessResult(groupBuyService.getGroupBuyByShopId(shop_id,state,pager));
    }
    //查询全部需要审核的商铺或者审核未通过的商铺
    @GetMapping("getIsPassGroupBuyList")
    public Result getIsPassGroupBuyList(Long shop_id) {
        return ResultGenerator.genSuccessResult(groupBuyService.getIsPassGroupBuyList(shop_id));
    }
    //查询用户的团购卷
    @GetMapping("getGroupBuyByUserId")
    public Result getGroupBuyByUserId(Pager pager) {
        return ResultGenerator.genSuccessResult(groupBuyService.getGroupBuyByUserId(getUser().getId(),pager));
    }

}
