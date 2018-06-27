package com.pdkj.jack_shop.web;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.model.Shop;
import com.pdkj.jack_shop.service.ShopService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/06/26.
*/
@RestController
@RequestMapping("/shop")
public class ShopController  extends BaseController {


    @PostMapping("/add")
    public Result add(Shop shop) {
        shopService.save(shop);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/getShop")
    public Result getShop(String name) {
        List<Shop> shop = shopService.getShop(name);
        return ResultGenerator.genSuccessResult(shop);
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        shopService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Shop shop) {
        shopService.update(shop);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Shop shop = shopService.findById(id);
        return ResultGenerator.genSuccessResult(shop);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Shop> list = shopService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
