package com.pdkj.jack_shop.service;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.service
 * @author lvchong
 * @date 2018/8/16 10:36
 * @version V1.0
 */

import org.springframework.stereotype.Service;

/**
 * @author lvchong
 * @ClassName OssConfigService
 * @Description 类描述
 * @date 2018/8/16
 */
@Service
public class OssConfigService extends BaseService{

    public Object getOssConfig(Integer id) {
        return ossConfigDao.getOssConfig(id);
    }
}
