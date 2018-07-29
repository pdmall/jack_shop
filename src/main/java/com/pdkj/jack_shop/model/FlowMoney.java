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
    private Long item_id;
    private Integer flow_state_id;
    private Integer item_id_type; //item_id 1为订单 2为卷 3套餐

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public Integer getItem_id_type() {
        return item_id_type;
    }

    public void setItem_id_type(Integer item_id_type) {
        this.item_id_type = item_id_type;
    }

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

    public Integer getFlow_state_id() {
        return flow_state_id;
    }

    public void setFlow_state_id(Integer flow_state_id) {
        this.flow_state_id = flow_state_id;
    }
}
