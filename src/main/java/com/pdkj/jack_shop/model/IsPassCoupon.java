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

    /**
     * 是否可退
1，随时退
0，不可退
     */
    private Integer is_refund;

    /**
     * 0,免预约
1,提前一小时预约
2，提前2销售预约
     */
    private Integer sub_time;

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

    /**
     * 0,随时可用
1,周末不可用
2,周六，周日不可用
3,节假日不可用
     */
    private Integer unable_date;

    private String coupon_img;

    private Date created;



    private int coupon_state;
    private Long coupon_id;

    public int getCoupon_state() {
        return coupon_state;
    }

    public void setCoupon_state(int coupon_state) {
        this.coupon_state = coupon_state;
    }

    public Long getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(Long coupon_id) {
        this.coupon_id = coupon_id;
    }

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

    public Integer getIs_refund() {
        return is_refund;
    }

    public void setIs_refund(Integer is_refund) {
        this.is_refund = is_refund;
    }

    public Integer getSub_time() {
        return sub_time;
    }

    public void setSub_time(Integer sub_time) {
        this.sub_time = sub_time;
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

    public Integer getUnable_date() {
        return unable_date;
    }

    public void setUnable_date(Integer unable_date) {
        this.unable_date = unable_date;
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

}