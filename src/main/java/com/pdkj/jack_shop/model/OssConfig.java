package com.pdkj.jack_shop.model;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.model
 * @author lvchong
 * @date 2018/8/16 10:07
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

/**
 * @author lvchong
 * @ClassName Oss
 * @Description 类描述
 * @date 2018/8/16
 */
public class OssConfig {
    private Integer id;
    private Integer name;
    private Integer key_id;
    private Integer key_secret;
    private Integer bucket_name;
    private Integer file_path;
    private Integer endpoint;
    private Integer nick_endpoint;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public Integer getKey_id() {
        return key_id;
    }

    public void setKey_id(Integer key_id) {
        this.key_id = key_id;
    }

    public Integer getKey_secret() {
        return key_secret;
    }

    public void setKey_secret(Integer key_secret) {
        this.key_secret = key_secret;
    }

    public Integer getBucket_name() {
        return bucket_name;
    }

    public void setBucket_name(Integer bucket_name) {
        this.bucket_name = bucket_name;
    }

    public Integer getFile_path() {
        return file_path;
    }

    public void setFile_path(Integer file_path) {
        this.file_path = file_path;
    }

    public Integer getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Integer endpoint) {
        this.endpoint = endpoint;
    }

    public Integer getNick_endpoint() {
        return nick_endpoint;
    }

    public void setNick_endpoint(Integer nick_endpoint) {
        this.nick_endpoint = nick_endpoint;
    }
}
