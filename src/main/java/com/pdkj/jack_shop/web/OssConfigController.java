package com.pdkj.jack_shop.web;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.web
 * @author lvchong
 * @date 2018/8/16 10:20
 * @version V1.0
 */

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lvchong
 * @ClassName OssConfigController
 * @Description 类描述
 * @date 2018/8/16
 */
@RestController
@RequestMapping("ossConfig")
public class OssConfigController extends BaseController {
    //获得oss配置
    @GetMapping("getOssConfig")
    public Result getOssConfig(Integer id) throws CustomException {
        return ResultGenerator.genSuccessResult(ossConfigService.getOssConfig(id));
    }

}
