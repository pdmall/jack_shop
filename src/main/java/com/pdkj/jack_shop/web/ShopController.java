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

/*    @PostMapping("register")
    public Result register(User user, String vercode) throws CustomException {
        userService.register(user, vercode);
        return ResultGenerator.genSuccessResult();
    }*/

    /**
     * 获得商铺列表
     * @param pager
     * @return
     * @throws CustomException
     */
    @GetMapping("getShopList")
    public Result getShopList(Pager pager) throws CustomException {
        List<Map<String, Object>> list =  shopService.getShopList(pager);
        return ResultGenerator.genSuccessResult(list);
    }

    /**
     * 添加一个商铺
     * @param shop
     * @return
     * @throws CustomException
     */
    @GetMapping("addShop")
    public Result addShop(Shop shop) throws CustomException {
         shopService.addShop(shop);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 获取商铺信息
     * @param id
     * @return
     * @throws CustomException
     */
    @GetMapping("getShop")
    public Result getShop(@RequestParam(value = "id") Long id) throws CustomException {
        return ResultGenerator.genSuccessResult(shopService.getShop(id));
    }

    /**
     * 根据商铺ID查询商铺位置
     * @param id
     * @return
     * @throws CustomException
     */
    @GetMapping("findAddressById")
    public Result findAddressById(Long id) throws CustomException {
        return ResultGenerator.genSuccessResult(shopService.findAddressById(id));
    }

    /**
     * 根据类型分类
     * @param id 传入类型ID
     * @return
     * @throws CustomException
     */
    @GetMapping("findByClassify")
    public Result findByClassify(Long id,Pager pager) throws CustomException {
        return ResultGenerator.genSuccessResult(shopService.findByClassify(id,pager));
    }

    /**
     * 搜索框查询
     * @param key 搜索框中的值
     * @param county 属于哪个城市
     * @return
     * @throws CustomException
     */
    @GetMapping("search")
    public Result searchBox(String key, String county,Pager pager) throws CustomException {
        return ResultGenerator.genSuccessResult(shopService.searchBox(key,county,pager));
    }





}
