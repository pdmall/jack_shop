package com.pdkj.jack_shop.web;

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by CodeGenerator on 2018/06/26.
 */
@RestController
@RequestMapping("banner")
public class bannerController extends BaseController {
    //获得首页广告栏信息
    @GetMapping("getHomeBanner")
    public Result getHomeBanner() throws CustomException {
        List<Map<String, Object>> list =  bannerService.getHomeBanner();
        return ResultGenerator.genSuccessResult(list);
    }
    //获得首页广告栏信息
    @GetMapping("getSuperDiscount")
    public Result getSuperDiscount() throws CustomException {
        return ResultGenerator.genSuccessResult(bannerService.getSuperDiscount());
    }


}
