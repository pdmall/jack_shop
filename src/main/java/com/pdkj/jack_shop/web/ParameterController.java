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
@RequestMapping("parameter")
public class ParameterController extends BaseController {
    //获得显示奖励金额
    @GetMapping("getParam")
    public Result getParam() throws CustomException {
        return ResultGenerator.genSuccessResult(parameterService.getParam());
    }

}