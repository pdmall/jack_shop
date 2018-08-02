package com.pdkj.jack_shop.web;

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.model.UserConcern;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by CodeGenerator on 2018/06/26.
 */
@RestController
@RequestMapping("userConcern")
public class UserConcernController extends BaseController {
    //获得商铺关注列表
    @GetMapping("getShopConcernList")
    public Result getUserConcernList(Long shop_id) throws CustomException {
        return ResultGenerator.genSuccessResult(userConcernService.getUserConcernList(shop_id));
    }
    //获得用户关注列表
    @GetMapping("getUserConcernList")
    public Result getShopConcernList() throws CustomException {
        return ResultGenerator.genSuccessResult(userConcernService.getShopConcernList(getUser().getId()));
    }
    //用户关注
    @PostMapping("concern")
    public Result concern(UserConcern userConcern){
        return ResultGenerator.genSuccessResult(userConcernService.concern(getUser().getId(),userConcern));
    }
    //取消用户关注
    @PostMapping("noConcern")
    public Result noConcern(Long shop_id){
        return ResultGenerator.genSuccessResult(userConcernService.noConcern(getUser().getId() ,shop_id));
    }
    //判断是否是关注商铺
    @PostMapping("isConcern")
    public Result isConcern(Long shop_id){
        return ResultGenerator.genSuccessResult(userConcernService.isConcern(getUser().getId() ,shop_id));
    }
}
