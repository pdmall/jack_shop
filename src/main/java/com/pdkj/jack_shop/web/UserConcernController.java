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
@RequestMapping("userConcern")
public class UserConcernController extends BaseController {
    @GetMapping("getUserConcernList")
    public Result getUserConcernList(Long shop_id) throws CustomException {
        return ResultGenerator.genSuccessResult(userConcernService.getUserConcernList(shop_id));
    }
}
