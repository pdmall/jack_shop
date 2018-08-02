package com.pdkj.jack_shop.web;

import com.aliyuncs.exceptions.ClientException;
import com.pdkj.jack_shop.configurer.AliYunSMS;
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
    //获得注册验证码
    @GetMapping("getVerCode")
    public Result getVerCode(String phone) throws CustomException, ClientException {
        return ResultGenerator.genSuccessResult(userService.getVerCode(phone,AliYunSMS.reginAndLogin));
    }
    //获得成为店员的验证码
    @GetMapping("getAddEmployeeVerCode")
    public Result getAddEmployeeVerCode(String phone) throws CustomException, ClientException {
        return ResultGenerator.genSuccessResult(userService.getVerCode(phone,AliYunSMS.addEmployee));
    }
    //登录或者注册接口
    @PostMapping("register")
    public Result register(User user, String verCode) throws Exception {
        return ResultGenerator.genSuccessResult(userService.register(user, verCode));
    }

/*    @GetMapping("getUser")
    public Result getUser(@RequestParam Long id) throws CustomException {
        return ResultGenerator.genSuccessResult(userService.getUser(id));
    }*/
    //获得用户信息
    @GetMapping("getUserInfo")
    public Result getUserInfo() throws CustomException {
        return ResultGenerator.genSuccessResult(getUser());
    }

    //删除图片
    @PostMapping("delImg")
    public Result delImg(String img_url) throws CustomException {
        userService.delImg(img_url);
        return ResultGenerator.genSuccessResult();
    }
    //在商铺端获的我的所有商铺
    @GetMapping("getMyShopList")
    public Result getMyShopList() throws CustomException {
        return ResultGenerator.genSuccessResult(shopService.getMyShopList(getUser().getId()));
    }
    //修改用户信息
    @GetMapping("updateUserInfo")
    public Result updateUserInfo(User user) throws CustomException {
        return ResultGenerator.genSuccessResult(userService.updateUserInfo(user,getUser().getId()));
    }
    //获得角色
    @GetMapping("getRole")
    public Result getRole() throws CustomException {
        return ResultGenerator.genSuccessResult(userService.getRole());
    }
    //获得二维码
    @GetMapping("getQRCode")
    public Result getQRCode() throws CustomException {
        return ResultGenerator.genSuccessResult(userService.getQRCode(getUser().getId()));
    }
    //验证卷的是否可用
    @PostMapping("verifyCoupons")
    public Result verifyCoupons(String user_order_id ,Integer count , String user_order_details_id ) throws CustomException {
        return ResultGenerator.genSuccessResult(userService.verifyOrderDetails(getUser().getId(),user_order_id,count,user_order_details_id));
    }
    
    //商家确认消费
    @PostMapping("confirm")
    public Result confirm(Long user_order_id ,Integer count , String user_order_details_id , Double sum) throws CustomException {
        userService.getConfirm(user_order_id ,count , user_order_details_id,sum);
        return ResultGenerator.genSuccessResult("完成");
    }



}
