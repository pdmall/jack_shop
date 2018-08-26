package com.pdkj.jack_shop.web;

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.ParameterException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.util.Ognl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by CodeGenerator on 2018/06/26.
 */
@RestController
@RequestMapping("shopComment")
public class ShopCommentController extends BaseController {
    //获得商铺全部评论
    @GetMapping("getCommentList")
    public Result getCommentList(Long shopId) throws CustomException {
        if(Ognl.isEmpty(shopId))
            throw new ParameterException("参数异常");
        return ResultGenerator.genSuccessResult(shopCommentService.getCommentList(shopId));
    }
    //获得评论回复
    @GetMapping("getCommentReply")
    public Result getCommentReply(Long order_id) throws CustomException {
        if(Ognl.isEmpty(order_id))
            throw new ParameterException("参数异常");
        return ResultGenerator.genSuccessResult(shopCommentService.getCommentReply(order_id));
    }
    //添加评论
    @GetMapping("addCommentReply")
    public Result addCommentReply(Long order_id) throws CustomException {
        if(Ognl.isEmpty(order_id))
            throw new ParameterException("参数异常");
        return ResultGenerator.genSuccessResult(shopCommentService.getCommentReply(order_id));
    }

}
