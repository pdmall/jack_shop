package com.pdkj.jack_shop.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "user_order")
public class UserOrder {
    private Long id;
    private String pay_on;
    private Integer order_state_id;
    private Integer pay_type;
    private Long user_id;
    private Date created;
    private Date Pay_time;
    private Long coupon_id;
    private Double wallet_money;
    private Double total_price;
    private Double final_price;
    private Long shop_id;
    private Integer service_score;
    private Integer enviro_score;
    private Integer taste_score;
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPay_on() {
        return pay_on;
    }

    public void setPay_on(String pay_on) {
        this.pay_on = pay_on;
    }

    public Integer getOrder_state_id() {
        return order_state_id;
    }

    public void setOrder_state_id(Integer order_state_id) {
        this.order_state_id = order_state_id;
    }

    public Integer getPay_type() {
        return pay_type;
    }

    public void setPay_type(Integer pay_type) {
        this.pay_type = pay_type;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getPay_time() {
        return Pay_time;
    }

    public void setPay_time(Date pay_time) {
        Pay_time = pay_time;
    }

    public Long getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(Long coupon_id) {
        this.coupon_id = coupon_id;
    }

    public Double getWallet_money() {
        return wallet_money;
    }

    public void setWallet_money(Double wallet_money) {
        this.wallet_money = wallet_money;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }

    public Double getFinal_price() {
        return final_price;
    }

    public void setFinal_price(Double final_price) {
        this.final_price = final_price;
    }

    public Long getShop_id() {
        return shop_id;
    }

    public void setShop_id(Long shop_id) {
        this.shop_id = shop_id;
    }

    public Integer getService_score() {
        return service_score;
    }

    public void setService_score(Integer service_score) {
        this.service_score = service_score;
    }

    public Integer getEnviro_score() {
        return enviro_score;
    }

    public void setEnviro_score(Integer enviro_score) {
        this.enviro_score = enviro_score;
    }

    public Integer getTaste_score() {
        return taste_score;
    }

    public void setTaste_score(Integer taste_score) {
        this.taste_score = taste_score;
    }
}