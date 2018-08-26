package com.pdkj.jack_shop.model;
/**
 * @Project: shop_seller
 * @Package com.pdkj.jack_shop.model
 * @author lvchong
 * @date 2018/8/21 16:09
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import java.util.Date;

/**
 * @author lvchong
 * @ClassName ShopFlowMoney
 * @Description 类描述
 * @date 2018/8/21
 */
public class ShopFlowMoney {
    private Long id;
    private Double value;
    private Date created;
    private Long shop_id;
    private Long item_id;
    private Integer flow_state_id;
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getShop_id() {
        return shop_id;
    }

    public void setShop_id(Long shop_id) {
        this.shop_id = shop_id;
    }

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public Integer getFlow_state_id() {
        return flow_state_id;
    }

    public void setFlow_state_id(Integer flow_state_id) {
        this.flow_state_id = flow_state_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
