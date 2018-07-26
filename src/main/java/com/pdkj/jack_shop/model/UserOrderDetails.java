package com.pdkj.jack_shop.model;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.model
 * @author lvchong
 * @date 2018/7/14 9:35
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import java.util.Date;

/**
 * @author lvchong
 * @ClassName UserOrderDetails
 * @Description 类描述
 * @date 2018/7/14
 */
public class UserOrderDetails {
    private Long id;
    private Long user_order_id;
    private Long item_id;
    private Integer type_of_id;
    private Integer state;
    private Date use_time;
    private Double price;
    private Double quantity;


    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_order_id() {
        return user_order_id;
    }

    public void setUser_order_id(Long user_order_id) {
        this.user_order_id = user_order_id;
    }

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public Integer getType_of_id() {
        return type_of_id;
    }

    public void setType_of_id(Integer type_of_id) {
        this.type_of_id = type_of_id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getUse_time() {
        return use_time;
    }

    public void setUse_time(Date use_time) {
        this.use_time = use_time;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
