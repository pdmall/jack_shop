package com.pdkj.jack_shop.web;

import com.aliyuncs.exceptions.ClientException;
import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.model.User;
import com.pdkj.jack_shop.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by CodeGenerator on 2018/06/26.
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @GetMapping("getVerCode")
    public Result getVerCode(String phone) throws CustomException, ClientException {
        String message = userService.getVerCode(phone);
        return ResultGenerator.genSuccessResult(message);
    }

    @PostMapping("register")
    public Result register(User user, String verCode) throws CustomException {
        return ResultGenerator.genSuccessResult(userService.register(user, verCode));
    }

/*    @GetMapping("getUser")
    public Result getUser(@RequestParam Long id) throws CustomException {
        return ResultGenerator.genSuccessResult(userService.getUser(id));
    }*/

    @GetMapping("getUserInfo")
    public Result getUserInfo(String token) throws CustomException {
        return ResultGenerator.genSuccessResult(getUser());
    }

    @GetMapping("getLevel2ByLevel3")
    public Result getLevel2ByLevel3(String token) throws CustomException {

        return ResultGenerator.genSuccessResult(userService.getLevel2ByLevel3(token));
    }

    @GetMapping("getLevel1ByLevel3")
    public Result getLevel1ByLevel3(String token) throws CustomException {
        return ResultGenerator.genSuccessResult(userService.getLevel1ByLevel3(token));
    }

    @GetMapping("delImg")
    public Result delImg(String img_url) throws CustomException {
        userService.delImg(img_url);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("getMyShopList")
    public Result getMyShopList(String token) throws CustomException {
        return ResultGenerator.genSuccessResult(shopService.getMyShopList(getUser().getId()));
    }


}
