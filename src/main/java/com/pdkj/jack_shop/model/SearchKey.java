package com.pdkj.jack_shop.model;
/**
 * @Project: shop_seller
 * @Package com.pdkj.jack_shop.model
 * @author lvchong
 * @date 2018/8/22 11:56
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

/**
 * @author lvchong
 * @ClassName SearchKey
 * @Description 类描述
 * @date 2018/8/22
 */
public class SearchKey {
    private Long id;
    private String item;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
