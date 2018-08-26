package com.pdkj.jack_shop.web;

import com.aliyuncs.exceptions.ClientException;
import com.pdkj.jack_shop.configurer.AliYunSMS;
import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.ParameterException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.model.User;
import com.pdkj.jack_shop.service.UserService;
import com.pdkj.jack_shop.util.Ognl;
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
        if(Ognl.isEmpty(phone))
            throw new ParameterException("电话号码为空");
        return ResultGenerator.genSuccessResult(userService.getVerCode(phone,AliYunSMS.reginAndLogin));
    }
    //获得成为店员的验证码
    @GetMapping("getAddEmployeeVerCode")
    public Result getAddEmployeeVerCode(String phone) throws CustomException, ClientException {
        if(Ognl.isEmpty(phone) || phone.trim().length()<11)
            throw new ParameterException("电话号码不正确");
        return ResultGenerator.genSuccessResult(userService.getVerCode(phone,AliYunSMS.addEmployee));
    }
    //修改支付密码的验证码
    @GetMapping("getUpdatePasswordVerCode")
    public Result getUpdatePasswordVerCode(String phone) throws CustomException, ClientException {
        if(Ognl.isEmpty(phone))
            throw new ParameterException("电话号码为空");
        User user = getUser();
        if(!user.getPhone().equals(phone))
            throw new ParameterException("电话号码不正确");
        return ResultGenerator.genSuccessResult(userService.getVerCode(phone,AliYunSMS.updatePayPassword));
    }
    //登录或者注册接口
    @PostMapping("register")
    public Result register(User user, String verCode,String phoneF) throws Exception {
        if(Ognl.isEmpty(user.getPhone()) || Ognl.isEmpty(verCode))
            throw new ParameterException("参数异常");
        User user1 = userService.register(user, verCode);
        if(Ognl.isNotEmpty(phoneF)){
            shareOrginService.addShareOrgin(user1.getId(),phoneF);
        }
        user1.setId(null);
        return ResultGenerator.genSuccessResult(user1);
    }
    //比对验证码是否准确
    @PostMapping("verifVerCode")
    public Result verifVerCode(String verCode,String phone) throws Exception {
        if(Ognl.isEmpty(phone) || Ognl.isEmpty(verCode))
            throw new ParameterException("参数异常");
        return ResultGenerator.genSuccessResult(userService.verifVerCode(phone, verCode));
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
        if(Ognl.isEmpty(img_url))
            throw new ParameterException("参数异常");
        userService.delImg(img_url);
        return ResultGenerator.genSuccessResult();
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
        if(Ognl.isEmpty(user_order_id)||Ognl.isEmpty(count)||Ognl.isEmpty(user_order_details_id))
            throw new ParameterException("参数异常");
        return ResultGenerator.genSuccessResult(userService.verifyOrderDetails(getUser().getId(),user_order_id,count,user_order_details_id));
    }
    //商家确认消费
    @PostMapping("confirm")
    public Result confirm(Long user_order_id ,Integer count , String user_order_details_id , Double sum) throws CustomException {
        if(Ognl.isEmpty(user_order_id)||Ognl.isEmpty(count)||Ognl.isEmpty(sum))
            throw new ParameterException("参数异常");
        userService.getConfirm(user_order_id ,count , user_order_details_id,sum);
        return ResultGenerator.genSuccessResult("完成");
    }
    //设置或者修改支付密码
    @PostMapping("updatePayPassword")
    public Result updatePayPassword(String payPassword) throws CustomException {
        if(Ognl.isEmpty(payPassword))
            throw new ParameterException("密码为空");
        return ResultGenerator.genSuccessResult(userService.updatePayPassword(getUser().getId(),payPassword));
    }


}
