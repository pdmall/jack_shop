package com.pdkj.jack_shop.model;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.model
 * @author lvchong
 * @date 2018/7/21 16:19
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import java.sql.Date;

/**
 * @author lvchong
 * @ClassName FlowMoney
 * @Description 类描述
 * @date 2018/7/21
 */
public class FlowMoney {
    private Long id;
    private Double value;
    private Date created;
    private Long user_id;
    private Long user_order_id;
    private Integer flow_state;

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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getUser_order_id() {
        return user_order_id;
    }

    public void setUser_order_id(Long user_order_id) {
        this.user_order_id = user_order_id;
    }

    public Integer getFlow_state() {
        return flow_state;
    }

    public void setFlow_state(Integer flow_state) {
        this.flow_state = flow_state;
    }
}
