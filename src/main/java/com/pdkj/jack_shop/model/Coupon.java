package com.pdkj.jack_shop.model;

import com.pdkj.jack_shop.util.DateUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Coupon {

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
    private int goods_range_id;
    private int buy_person_limit;
    private int stock_count;
    private int once_count;
    private String unavailable_date;
    private String diners_number;
    private int is_limit;

    public int getIs_limit() {
        return is_limit;
    }

    public void setIs_limit(int is_limit) {
        this.is_limit = is_limit;
    }

    public String getDiners_number() {
        return diners_number;
    }

    public void setDiners_number(String diners_number) {
        this.diners_number = diners_number;
    }

    public String getUnavailable_date() {
        return unavailable_date;
    }

    public void setUnavailable_date(String unavailable_date) {
        this.unavailable_date = unavailable_date;
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

    public void setDate_start(String date_start) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.date_start = sdf.parse(date_start);
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.date_end = sdf.parse(date_end);
    }



    public Date getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) throws ParseException {
        if(DateUtils.isLegalDate(time_start,"HH:mm")){
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            this.time_start = sdf.parse(time_start+":00");
        }
    }

    public Date getTime_end() {
        return time_end;
    }
    public void setTime_end(String time_end) throws ParseException {
        if(DateUtils.isLegalDate(time_end,"HH:mm")){
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            this.time_end = sdf.parse(time_end+":00");
        }
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

    public int getGoods_range_id() {
        return goods_range_id;
    }

    public void setGoods_range_id(int goods_range_id) {
        this.goods_range_id = goods_range_id;
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
}