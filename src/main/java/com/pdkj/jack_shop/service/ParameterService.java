package com.pdkj.jack_shop.service;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.service
 * @author lvchong
 * @date 2018/7/21 9:53
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lvchong
 * @ClassName ParameterService
 * @Description 类描述
 * @date 2018/7/21
 */
@Service
public class ParameterService extends BaseService {

    public List<Map<String, Object>> getParam() {
        return parameterDao.getParam();
    }
}
