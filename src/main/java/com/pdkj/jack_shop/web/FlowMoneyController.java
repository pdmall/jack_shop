package com.pdkj.jack_shop.web;

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.util.sql.Pager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by CodeGenerator on 2018/06/26.
 */
@RestController
@RequestMapping("flowMoney")
public class FlowMoneyController extends BaseController {
    //获得消费记录
    @GetMapping("getFlowMoney")
    public Result getFlowMoney(Pager pager) throws CustomException {
        return ResultGenerator.genSuccessResult(flowMoneyService.getFlowMoney(getUser().getId(),pager));
    }



}
