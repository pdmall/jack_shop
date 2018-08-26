package com.pdkj.jack_shop.web;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.web
 * @author lvchong
 * @date 2018/7/13 20:33
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.ParameterException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.util.Ognl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author lvchong
 * @ClassName AnalysisController
 * @Description 类描述
 * @date 2018/7/13
 */
@RestController
@RequestMapping("analysis")
public class AnalysisController extends BaseController {
    @GetMapping("business")
    public Result business(Long shop_id,Integer day) throws Exception {
        if(Ognl.isNotEmpty(shop_id)&& Ognl.isNotEmpty(day))
            return ResultGenerator.genSuccessResult(analysisService.business(shop_id,day));
        else
            throw new ParameterException("参数异常");
    }
    @GetMapping("trade")
    public Result trade(Long shop_id,Integer day) throws Exception {
        if(Ognl.isNotEmpty(shop_id)&& Ognl.isNotEmpty(day))
        return ResultGenerator.genSuccessResult(analysisService.trade(shop_id,day));
        else
            throw new ParameterException("参数异常");
    }
    @GetMapping("groupBuy")
    public Result groupBuy(Long shop_id,Integer day) throws Exception {
        if(Ognl.isNotEmpty(shop_id)&& Ognl.isNotEmpty(day))
        return ResultGenerator.genSuccessResult(analysisService.groupBuy(shop_id,day));
        else
            throw new ParameterException("参数异常");
    }
    @GetMapping("comment")
    public Result comment(Long shop_id,Integer day) throws Exception {
        if(Ognl.isNotEmpty(shop_id)&& Ognl.isNotEmpty(day))
        return ResultGenerator.genSuccessResult(analysisService.comment(shop_id,day));
        else
            throw new ParameterException("参数异常");
    }
    @GetMapping("customer")
    public Result customer(Long shop_id,Integer day) throws Exception {
        if(Ognl.isNotEmpty(shop_id)&& Ognl.isNotEmpty(day))
        return ResultGenerator.genSuccessResult(analysisService.customer(shop_id,day));
        else
            throw new ParameterException("参数异常");
    }
}
