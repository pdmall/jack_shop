package com.pdkj.jack_shop.web;

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by CodeGenerator on 2018/06/26.
 */
@RestController
@RequestMapping("banner")
public class bannerController extends BaseController {

    @GetMapping("getAllBanner")
    public Result getAllBanner() throws CustomException {
        List<Map<String, Object>> list =  bannerService.getAllBanner();
        return ResultGenerator.genSuccessResult(list);
    }



}
