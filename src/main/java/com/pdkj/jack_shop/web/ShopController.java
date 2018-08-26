package com.pdkj.jack_shop.web;

import com.alibaba.fastjson.JSON;
import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.ParameterException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.model.*;
import com.pdkj.jack_shop.util.Ognl;
import com.pdkj.jack_shop.util.sql.Pager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResultGenerator.genSuccessResult(shopService.getShopList(pager));
    }

    /**
     * 添加一个商铺
     *
     * @param shop
     * @return
     * @throws CustomException
     */
    @PostMapping("addShop")
    public Result addShop(IsPassShop shop, String items,Long type_id) throws CustomException {
        if(Ognl.isEmpty(items))
            throw new CustomException("参数异常");
        String[] itemArr = items.trim().split(",");
        return ResultGenerator.genSuccessResult(shopService.addShop(shop,itemArr, type_id,getUser().getId()));
    }

    /**
     * 获取商铺信息
     *
     * @param id
     * @return
     * @throws CustomException
     */
    @GetMapping("getShop")
    public Result getShop(Long id) throws CustomException {
        if(Ognl.isEmpty(id))
            throw new ParameterException("参数异常");
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
        if(Ognl.isEmpty(id))
            throw new ParameterException("参数异常");
        return ResultGenerator.genSuccessResult(shopService.findAddressById(id));
    }

    /**
     * 根据类型分类
     * @param id 传入类型ID
     * @return
     * @throws CustomException
     */
    @GetMapping("findByClassify")
    public Result findByClassify(Long id, Pager pager) throws CustomException {
        if(Ognl.isEmpty(id))
            throw new ParameterException("参数异常");
        return ResultGenerator.genSuccessResult(shopService.findByClassify(id, pager));
    }

    /**
     * 搜索框查询
     *
     * @param key    搜索框中的值
     * @return
     * @throws CustomException
     */
    @GetMapping("search")
    public Result searchBox(Long key, Pager pager) throws CustomException {
        return ResultGenerator.genSuccessResult(shopService.searchBox(key,  pager));
    }

    /**
     * 根据评分排序
     *
     * @param key     搜索框值
     * @param county  地区
     * @param type_id 商铺类型
     * @param pager
     * @return
     */
    @GetMapping("shopSort")
    public Result shopSort(String key, String county, Long type_id, Pager pager) {
        return ResultGenerator.genSuccessResult(shopService.shopSort(key, type_id, county, pager));
    }
    /**
     * 按距离排序
     *
     * @param name
     * @param type_id
     * @param county
     * @param pager
     * @param latitude
     * @param longitude
     * @return
     */
    @GetMapping("shopDistanceSort")
    public Result shopDistanceSort(String name, Long type_id, String county, Pager pager, String latitude, String longitude) {
        return ResultGenerator.genSuccessResult(shopService.shopDistanceSort(name, type_id, county, pager, latitude, longitude));
    }
    /**
     * 按距离排序
     *
     * @param county
     * @param pager
     * @return
     */
    @GetMapping("shopDistanceValueSort")
    public Result shopDistanceValueSort(String name, Long type_id, String county, Pager pager, String latitude, String longitude, int distance) {
        return ResultGenerator.genSuccessResult(shopService.shopDistanceValueSort(name, type_id, county, pager, latitude, longitude, distance));
    }
    /**
     * 按时段查询
     *
     * @param mealTimeId
     * @param county
     * @param pager
     * @return
     */
    @GetMapping("shopMealTime")
    public Result shopMealTime(Long mealTimeId, String county, Pager pager) {
        return ResultGenerator.genSuccessResult(shopService.shopMealTime(mealTimeId, county, pager));
    }
    @GetMapping("getSearchKey")
    public Result getSearchKey(String name, Pager pager) {
        return ResultGenerator.genSuccessResult(shopService.getSearchKey(name, pager));
    }
    /**
     * 获得商铺返回消息
     * @return
     */
    @GetMapping("getShopPassFinish")
    public Result getShopPassFinish(Pager pager) {
        return ResultGenerator.genSuccessResult(shopService.getShopPassFinish(getUser().getId(),pager));
    }
    //查看用户上传的图片
    @GetMapping("getUserPhoto")
    public Result getUserPhoto() throws CustomException {
        return ResultGenerator.genSuccessResult(photoService.getUserPhoto(getUser().getId()));
    }
    //查看本店图片
    @GetMapping("getShopPhoto")
    public Result getShopPhoto(Long shop_id) throws CustomException {
        if(Ognl.isEmpty(shop_id))
            throw new ParameterException("参数异常");
        return ResultGenerator.genSuccessResult(photoService.getShopPhoto(shop_id));
    }
    //查看商品图片
    @GetMapping("getGoodsPhoto")
    public Result getGoodsPhoto(Long shop_id) throws CustomException {
        if(Ognl.isEmpty(shop_id))
            throw new ParameterException("参数异常");
        return ResultGenerator.genSuccessResult(photoService.getGoodsPhoto(shop_id));
    }
    //添加照片
    @PostMapping("addPhoto")
    public Result addPhoto(Photo photo) throws CustomException {
        photo.setUser_id(getUser().getId());
        photoService.addPhoto(photo);
        return ResultGenerator.genSuccessResult(photo);
    }
}
