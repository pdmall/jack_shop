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

    /**
     * 根据评分排序
     * @param key 搜索框值
     * @param county 地区
     * @param type_id 商铺类型
     * @param pager
     * @return
     */
    @GetMapping("shopSort")
    public Result shopSort(String key ,String county ,Long type_id, Pager pager){
        return ResultGenerator.genSuccessResult(shopService.shopSort(key,type_id,county,pager));
    }

    /**
     * 按距离排序
     * @param name
     * @param type_id
     * @param county
     * @param pager
     * @param latitude
     * @param longitude
     * @return
     */
    @GetMapping("shopDistanceSort")
    public Result shopDistanceSort(String name,Long type_id, String county, Pager pager,String latitude,String longitude){
        return ResultGenerator.genSuccessResult(shopService.shopDistanceSort( name, type_id,  county,  pager, latitude, longitude));
    }
/*
    *//**
     * 按距离排序
     * @param name
     * @param type_id
     * @param county
     * @param pager
     * @param latitude
     * @param longitude
     * @return
     *//*
    @GetMapping("shopDistanceValueSort")
    public Result shopDistanceValueSort(String name,Long type_id, String county, Pager pager,String latitude,String longitude,int distance){
        return ResultGenerator.genSuccessResult(shopService.shopDistanceValueSort( name, type_id,  county,  pager, latitude, longitude,distance));
    }*/



}
