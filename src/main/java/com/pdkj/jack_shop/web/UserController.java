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

    @PostMapping("/register")
    public Result register(User user, String vercode) throws CustomException {
        userService.register(user, vercode);
        return ResultGenerator.genSuccessResult();
    }


}
