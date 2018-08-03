package com.pdkj.jack_shop.interceptor;

import com.alibaba.fastjson.JSON;
import com.pdkj.jack_shop.configurer.WebMvcConfigurer;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultCode;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.model.User;
import com.pdkj.jack_shop.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UserSecurityInterceptor extends InterceptorRegistry implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);

    //存放所有不需要拦截的接口
    private static List<String> pageList = new ArrayList<>();
    private UserService userService;

    static {
        pageList.add("login");
        pageList.add("index");
        pageList.add("register");
        pageList.add("getVerCode");
        pageList.add("notifyInfo");
    }


    /**
     * 接口验证规则：
     * 从header中获取到appkey、timestamp、signature
     * 通过appkey获取用户信息（包括secret）
     * 判断SHA1(appkey_secret_timestamp)和signatrue是否一致，不一致则提示签名不正确，
     * 如果一致，则提取用户的权限，如果权限里面的地址包含了当前的地址，则允许通过，否则提示NO_AUTH
     * 为了安全考虑，强烈建议不要再客户端显示secret，一般通过登录，让服务器返回
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        //这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859
        //response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = request.getRequestURL().toString();
        inject(request);

        User user = null;
        //接口签名认证拦截器，该签名认证比较简单，实际项目中可以使用Json Web Token或其他更好的方式替代。
        if (!contains(pageList, url)) {
            //验证签名
            if (true) { //测试用
                String token = request.getParameter("token");
                System.out.println(token);
                user = userService.getUserByToken(token);
                request.setAttribute("user", user);
            } else {

                boolean pass = validateSign(request);
                if (pass) {
                    String token = request.getParameter("token");
                    user = userService.getUserByToken(token);
                    if (null != user) {
                        if (user.getState() == 1) {
                            request.setAttribute("user", user);
                        } else {
                            response.getWriter().print(JSON.toJSON(ResultGenerator.genFailResult("用户被冻结")));
                            return false;
                        }
                    } else {
                        response.getWriter().print(JSON.toJSON(ResultGenerator.genFailResult("无效token",ResultCode.UNAUTHORIZED)));
                        return false;
                    }
                } else {
                    logger.warn("签名认证失败，请求接口：{}，请求IP：{}，请求参数：{}", request.getRequestURI(), getIpAddress(request), JSON.toJSONString(request.getParameterMap()));
                    Result result = new Result();
                    result.setCode(ResultCode.UNAUTHORIZED).setMessage("签名认证失败");
                    responseResult(response, result);
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 是否包含指定字符串，模糊匹配
     *
     * @param list
     * @param value
     * @return
     */
    private boolean contains(List<String> list, String value) {
        if (null != list && list.size() > 0) {
            for (String str : list) {
                if (value.indexOf(str) > 0) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /**
     * 一个简单的签名认证，规则：
     * 1. 将请求参数按ascii码排序
     * 2. 拼接为a=value&b=value...这样的字符串（不包含sign）
     * 3. 混合密钥（secret）进行md5获得签名，与请求的签名进行比较
     */
    private boolean validateSign(HttpServletRequest request) {
        String requestSign = request.getHeader("sign");//获得请求签名，如sign=19e907700db7ad91318424a97c54ed57
        if (StringUtils.isEmpty(requestSign)) {
            return false;
        }
        List<String> keys = new ArrayList(request.getParameterMap().keySet());
        Collections.sort(keys);//排序
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key).append("=").append(request.getParameter(key)).append("&");//拼接字符串
        }
        String linkString = sb.toString();
        linkString = StringUtils.substring(linkString, 0, linkString.length() - 1);//去除最后一个'&'
        String APP_SECRET = "pdkj";//密钥，自己修改
        String sign = DigestUtils.sha1Hex(linkString + APP_SECRET);//混合密钥md5
        return StringUtils.equals(sign, requestSign);//比较
    }

    private void inject(HttpServletRequest request) {
        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        if (null == userService) {
            userService = factory.getBean(UserService.class);
        }
    }

    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }


    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 如果是多级代理，那么取第一个ip为客户端ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(0, ip.indexOf(",")).trim();
        }

        return ip;
    }

}
