package com.pdkj.jack_shop.web;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.model.ShopComment;
import com.pdkj.jack_shop.service.ShopCommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/06/26.
*/
@RestController
@RequestMapping("/shop/comment")
public class ShopCommentController {
    @Resource
    private ShopCommentService shopCommentService;

    @PostMapping("/add")
    public Result add(ShopComment shopComment) {
        shopCommentService.save(shopComment);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        shopCommentService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(ShopComment shopComment) {
        shopCommentService.update(shopComment);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        ShopComment shopComment = shopCommentService.findById(id);
        return ResultGenerator.genSuccessResult(shopComment);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<ShopComment> list = shopCommentService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
