package com.pdkj.jack_shop.web;

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.dao.UserDao;
import com.pdkj.jack_shop.model.UserOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by CodeGenerator on 2018/06/26.
 */
@RestController
@RequestMapping("userOrder")
public class UserOrderController extends BaseController {

    @GetMapping("addOrder")
    public Result addOrder(@RequestParam(value = "shop_id") Long shop_id,@RequestParam(value = "user_id") Long user_id) throws CustomException {
        Long id  =  userOrderService.addOrder(shop_id,user_id);
        return ResultGenerator.genSuccessResult(id);
    }

    @GetMapping("updateOrder")
    public Result updateOrder(UserOrder userOrder) throws CustomException {
        Long id  =  userOrderService.updateOrder(userOrder);
        return ResultGenerator.genSuccessResult(id);
    }



}
