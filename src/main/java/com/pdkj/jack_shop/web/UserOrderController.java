package com.pdkj.jack_shop.web;

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.ParameterException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.dao.UserDao;
import com.pdkj.jack_shop.model.UserOrder;
import com.pdkj.jack_shop.model.UserOrderDetails;
import com.pdkj.jack_shop.util.Ognl;
import com.pdkj.jack_shop.util.sql.Pager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by CodeGenerator on 2018/06/26.
 */
@RestController
@RequestMapping("userOrder")
public class UserOrderController extends BaseController {

    /**
     * @Title: 添加订单
     * @Description: 方法描述
     * @author lvchong
     * @params * @param null
     * @date 2018-07-23
     * @throw YnCorpSysException
     */

    @PostMapping("addOrder")
    public Result addOrder(UserOrder userOrder,UserOrderDetails userOrderDetails) throws CustomException {
        return ResultGenerator.genSuccessResult(userOrderService.addOrder(userOrder,userOrderDetails,getUser().getId()));
    }

    /**
     * @Title: 修改订单
     * @Description: 修改订单信息
     * @author lvchong
     * @params * @param null
     * @date 2018-07-23
     * @throw YnCorpSysException
     */

    @PostMapping("updateOrder")
    public Result updateOrder(UserOrder userOrder) throws CustomException {
        return ResultGenerator.genSuccessResult(userOrderService.updateOrder(userOrder));
    }

    /**
     * @Title: 获得用户订单信息
     * @Description: 方法描述
     * @author lvchong
     * @params * @param null
     * @date 2018-07-23
     * @throw YnCorpSysException
     */

    @GetMapping("getUserOrderList")
    public Result getUserOrderList(Integer order_state_id,Pager pager){
        if(Ognl.isEmpty(order_state_id))
            throw new ParameterException("参数异常");
        return ResultGenerator.genSuccessResult(userOrderService.getUserOrderList(getUser().getId(),order_state_id,pager));
    }

    /**
     * @Title: 获得商铺订单
     * @Description: 方法描述
     * @author lvchong
     * @params * @param null
     * @date 2018-07-23
     * @throw YnCorpSysException
     */

    @GetMapping("getShopOrder")
    public Result getShopOrder(Long shop_id , Pager pager){
        if(Ognl.isEmpty(shop_id))
            throw new ParameterException("参数异常");
        return ResultGenerator.genSuccessResult(userOrderService.getShopOrder(shop_id,pager));
    }

    //获得二维码 和 状态
    @GetMapping("getQRState")
    public Result getQRState(Long order_id){
        if(Ognl.isEmpty(order_id))
            throw new ParameterException("参数异常");
        return ResultGenerator.genSuccessResult(userOrderService.getQRState(order_id));
    }

    //获得订单信息
    @GetMapping("getOrderInfo")
    public Result getOrderInfo(String order_id){
        if(Ognl.isEmpty(order_id))
            throw new ParameterException("参数异常");
        return ResultGenerator.genSuccessResult(userOrderService.getOrderInfo(order_id));
    }


    
}

