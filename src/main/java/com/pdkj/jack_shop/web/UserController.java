package com.pdkj.jack_shop.web;
import com.aliyuncs.exceptions.ClientException;
import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.model.User;
import com.pdkj.jack_shop.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
* Created by CodeGenerator on 2018/06/26.
*/
@RestController
@RequestMapping("user")
public class UserController extends BaseController  {


    @GetMapping("getVerCode")
    public Result getVerCode(String phone) {
        try{
            boolean ok = userService.getVerCode(phone);
            if(ok){
                return ResultGenerator.genSuccessResult("成功");
            }else{
                return ResultGenerator.genFailResult("号码已存在");
            }
        }catch (Exception e){
            return ResultGenerator.genFailResult(e.getMessage());
        }
    }

    @PostMapping("/register")
    public Result register(User user,String vercode) throws CustomException {
        userService.register(user,vercode);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @GetMapping("/getToken")
    public Result getToken(String token) {
        User user = userService.getUserByToken(token);
        return ResultGenerator.genSuccessResult(user);
    }
}
