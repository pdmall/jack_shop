package com.pdkj.jack_shop.web;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.web
 * @author lvchong
 * @date 2018/7/2 10:03
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lvchong
 * @ClassName UserWalletController
 * @Description 类描述
 * @date 2018/7/2
 */
@RestController
@RequestMapping("userWallet")
public class UserWalletController extends BaseController {
    //获得用户的钱包
    @GetMapping("getWallet")
    public Result getWallet() throws CustomException {
        return ResultGenerator.genSuccessResult(userWalletService.getWallet(getUser().getId()));
    }

}
