package com.pdkj.jack_shop.model;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.model
 * @author lvchong
 * @date 2018/6/29 14:46
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

/**
 * @author lvchong
 * @ClassName banner
 * @Description 类描述
 * @date 2018/6/29
 */
public class banner {
    private String id;
    private String img_url;
    private String type;
    private String value;
    private String is_available;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIs_available() {
        return is_available;
    }

    public void setIs_available(String is_available) {
        this.is_available = is_available;
    }
}
