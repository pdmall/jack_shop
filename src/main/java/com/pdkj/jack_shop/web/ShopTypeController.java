package com.pdkj.jack_shop.web;

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.dao.ShopDao;
import com.pdkj.jack_shop.model.Shop;
import com.pdkj.jack_shop.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by CodeGenerator on 2018/06/26.
 */
@RestController
@RequestMapping("/shopType")
public class ShopTypeController extends BaseController {

    @GetMapping("/getAllShopType")
    public Result getAllShopType() throws CustomException {
        List<Map<String, Object>> list =  shopTypeService.getAllShopType();
        return ResultGenerator.genSuccessResult(list);
    }



}
