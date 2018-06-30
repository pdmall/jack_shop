package com.pdkj.jack_shop.web;

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.model.Shop;
import com.pdkj.jack_shop.model.User;
import com.pdkj.jack_shop.util.sql.Pager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by CodeGenerator on 2018/06/26.
 */
@RestController
@RequestMapping("shop")
public class ShopController extends BaseController {

/*    @GetMapping("getList")
    public Result getList(String phone) throws CustomException, ClientException {
        String message = shopService.getList(phone);
        return ResultGenerator.genSuccessResult(message);
    }*/

    @PostMapping("register")
    public Result register(User user, String vercode) throws CustomException {
        userService.register(user, vercode);
        return ResultGenerator.genSuccessResult();
    }
    @GetMapping("getShopList")
    public Result getShopList(Pager page) throws CustomException {
        List<Map<String, Object>> list =  shopService.getShopList(page);
        return ResultGenerator.genSuccessResult(list);
    }
    @GetMapping("addShop")
    public Result addShop(Shop shop) throws CustomException {
         shopService.addShop(shop);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("getShop")
    public Result getShop(@RequestParam(value = "id") Long id) throws CustomException {
        return ResultGenerator.genSuccessResult(shopService.getShop(id));
    }

    @GetMapping("findAddressById")
    public Result findAddressById(@RequestParam(value = "id") Long id) throws CustomException {
        return ResultGenerator.genSuccessResult(shopService.findAddressById(id));
    }

    @GetMapping("findByClassify")
    public Result findByClassify(@RequestParam(value = "id") Long id) throws CustomException {
        return ResultGenerator.genSuccessResult(shopService.findByClassify(id));
    }

    @GetMapping("searchBox")
    public Result searchBox(@RequestParam(value = "name") String name,@RequestParam(value = "shop_address") String shop_address) throws CustomException {
        return ResultGenerator.genSuccessResult(shopService.searchBox(name,shop_address));
    }



}
