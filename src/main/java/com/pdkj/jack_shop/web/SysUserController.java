package com.pdkj.jack_shop.web;

import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sysUser")
public class SysUserController extends BaseController{

    @PostMapping("login")
    public Result login(String username,String password){
        sysUserService.login(username,password);
        return ResultGenerator.genSuccessResult("aaa");
    }

}
