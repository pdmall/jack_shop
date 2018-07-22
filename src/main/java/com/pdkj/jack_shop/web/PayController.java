package com.pdkj.jack_shop.web;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.web
 * @author lvchong
 * @date 2018/7/22 14:14
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.util.NetUtils;
import com.pdkj.jack_shop.util.PayUtil;
import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author lvchong
 * @ClassName pay
 * @Description 类描述
 * @date 2018/7/22
 */
@Controller
@RequestMapping("pay")
public class PayController extends BaseController {
    @RequestMapping("notifyInfo")
    public Result notifyInfo(HttpServletRequest request) {
        //获取付款类型
        try {
            String reqParams = NetUtils.getStringFromInputStream(request.getInputStream());
            Map<String, String> mapData = PayUtil.xmlToMap(reqParams);
            if("SUCCESS".equals(mapData.get("return_code"))){
                String orderId = mapData.get("attach");
                System.out.println();
                System.out.println("heihei");
            }

        } catch (Exception e) {
        }
        return ResultGenerator.genSuccessResult();
    }
}
