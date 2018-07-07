package com.pdkj.jack_shop.model;

import java.math.BigDecimal;
import java.util.Date;

public class IsPassCoupon {

    private Long id;

    private String title;

    private Integer type;

    /**
     * 折扣 范围 0-99
     */
    private Integer discount;

    private BigDecimal buy_price;

    private BigDecimal final_price;

    private String appointment;

    /**
     * 优惠描述，团餐菜品介绍 ；好换行
     */
    private String describe;

    /**
     * 有效开始日期
     */
    private Date date_start;

    /**
     * 有效结束日期
     */
    private Date date_end;

    /**
     * 使用开始时间
     */
    private Date time_start;

    /**
     * 使用结束时间
     */
    private Date time_end;

    private String coupon_img;

    private Date created;

    /**
     * 商家id
     */
    private Long shop_id;

    private int coupon_state;
    private int goods_range;
    private int buy_person_limit;
    private int stock_count;
    private int once_count;
    private String unavailable_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public BigDecimal getBuy_price() {
        return buy_price;
    }

    public void setBuy_price(BigDecimal buy_price) {
        this.buy_price = buy_price;
    }

    public BigDecimal getFinal_price() {
        return final_price;
    }

    public void setFinal_price(BigDecimal final_price) {
        this.final_price = final_price;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public Date getTime_start() {
        return time_start;
    }

    public void setTime_start(Date time_start) {
        this.time_start = time_start;
    }

    public Date getTime_end() {
        return time_end;
    }

    public void setTime_end(Date time_end) {
        this.time_end = time_end;
    }

    public String getCoupon_img() {
        return coupon_img;
    }

    public void setCoupon_img(String coupon_img) {
        this.coupon_img = coupon_img;
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

    public int getCoupon_state() {
        return coupon_state;
    }

    public void setCoupon_state(int coupon_state) {
        this.coupon_state = coupon_state;
    }

    public int getGoods_range() {
        return goods_range;
    }

    public void setGoods_range(int goods_range) {
        this.goods_range = goods_range;
    }

    public int getBuy_person_limit() {
        return buy_person_limit;
    }

    public void setBuy_person_limit(int buy_person_limit) {
        this.buy_person_limit = buy_person_limit;
    }

    public int getStock_count() {
        return stock_count;
    }

    public void setStock_count(int stock_count) {
        this.stock_count = stock_count;
    }

    public int getOnce_count() {
        return once_count;
    }

    public void setOnce_count(int once_count) {
        this.once_count = once_count;
    }

    public String getUnavailable_date() {
        return unavailable_date;
    }

    public void setUnavailable_date(String unavailable_date) {
        this.unavailable_date = unavailable_date;
    }
}