package com.pdkj.jack_shop.web;

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.dao.UserDao;
import com.pdkj.jack_shop.model.UserOrder;
import com.pdkj.jack_shop.model.UserOrderDetails;
import com.pdkj.jack_shop.util.sql.Pager;
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
    public Result addOrder(UserOrder userOrder,UserOrderDetails userOrderDetails) throws CustomException {
        return ResultGenerator.genSuccessResult(userOrderService.addOrder(userOrder,userOrderDetails,getUser().getId()));
    }

    @GetMapping("updateOrder")
    public Result updateOrder(UserOrder userOrder) throws CustomException {
        return ResultGenerator.genSuccessResult(userOrderService.updateOrder(userOrder));
    }
    @GetMapping("getUserOrder")
    public Result getUserOrder(Long user_id , Pager pager){
        return ResultGenerator.genSuccessResult(userOrderService.getUserOrder(user_id,pager));
    }
    @GetMapping("getShopOrder")
    public Result getShopOrder(Long shop_id , Pager pager){
        return ResultGenerator.genSuccessResult(userOrderService.getShopOrder(shop_id,pager));
    }

}
