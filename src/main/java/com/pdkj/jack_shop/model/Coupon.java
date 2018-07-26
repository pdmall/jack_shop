package com.pdkj.jack_shop.model;

public class Coupon {
    private Long id;
    private Integer type_of_id;
    private Double buy_price;
    private Double original_price;
    private String appointment;
    private String date_start;
    private String date_end;
    private String time_start;
    private String time_end;
    private String created;
    private Long shop_id;
    private Integer coupon_state;
    private Integer goods_range_id;
    private Integer buy_person_limit;
    private Integer stock_count;
    private Integer once_count;
    private String unavailable_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType_of_id() {
        return type_of_id;
    }

    public void setType_of_id(Integer type_of_id) {
        this.type_of_id = type_of_id;
    }

    public Double getBuy_price() {
        return buy_price;
    }

    public void setBuy_price(Double buy_price) {
        this.buy_price = buy_price;
    }

    public Double getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(Double original_price) {
        this.original_price = original_price;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Long getShop_id() {
        return shop_id;
    }

    public void setShop_id(Long shop_id) {
        this.shop_id = shop_id;
    }

    public Integer getCoupon_state() {
        return coupon_state;
    }

    public void setCoupon_state(Integer coupon_state) {
        this.coupon_state = coupon_state;
    }

    public Integer getGoods_range_id() {
        return goods_range_id;
    }

    public void setGoods_range_id(Integer goods_range_id) {
        this.goods_range_id = goods_range_id;
    }

    public Integer getBuy_person_limit() {
        return buy_person_limit;
    }

    public void setBuy_person_limit(Integer buy_person_limit) {
        this.buy_person_limit = buy_person_limit;
    }

    public Integer getStock_count() {
        return stock_count;
    }

    public void setStock_count(Integer stock_count) {
        this.stock_count = stock_count;
    }

    public Integer getOnce_count() {
        return once_count;
    }

    public void setOnce_count(Integer once_count) {
        this.once_count = once_count;
    }

    public String getUnavailable_date() {
        return unavailable_date;
    }

    public void setUnavailable_date(String unavailable_date) {
        this.unavailable_date = unavailable_date;
    }
}