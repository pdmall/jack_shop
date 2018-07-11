package com.pdkj.jack_shop.web;

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.model.GroupBuy;
import com.pdkj.jack_shop.model.IsPassGroupBuy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by CodeGenerator on 2018/06/26.
 */
@RestController
@RequestMapping("groupBuy")
public class GroupBuyController extends BaseController {

    @GetMapping("addGroupBuy")
    public Result addGroupBuy(IsPassGroupBuy groupBuy , String goods_ids) throws CustomException {
        return ResultGenerator.genSuccessResult(groupBuyService.addGroupBuy(groupBuy,goods_ids));
    }

}
