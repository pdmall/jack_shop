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
@RequestMapping("appConstant")
public class AppConstantController extends BaseController {
    //获得显示奖励金额
    @GetMapping("getAppConstant")
    public Result getAppConstant() throws CustomException {
        return ResultGenerator.genSuccessResult(appConstantService.getAppConstant());
    }
}
