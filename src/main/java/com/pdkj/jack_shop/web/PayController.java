package com.pdkj.jack_shop.web;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.web
 * @author lvchong
 * @date 2018/7/22 14:14
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.model.FlowMoney;
import com.pdkj.jack_shop.model.UserCouponRel;
import com.pdkj.jack_shop.model.UserGroupBuyRel;
import com.pdkj.jack_shop.util.NetUtils;
import com.pdkj.jack_shop.util.PayUtil;
import com.pdkj.jack_shop.util.Tools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

/**
 * @author lvchong
 * @ClassName pay
 * @Description 类描述
 * @date 2018/7/22
 */
@RestController
@RequestMapping("pay")
public class PayController extends BaseController {
    @RequestMapping("notifyInfo")
    public void notifyInfo(HttpServletRequest request, HttpServletResponse response) {
        payService.notifyInfo(request, response);
    }
    @RequestMapping("refundInfo")
    public void refundInfo(HttpServletRequest request, HttpServletResponse response) {
        payService.refundInfo(request, response);
    }

}
