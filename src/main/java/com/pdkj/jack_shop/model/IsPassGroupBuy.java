package com.pdkj.jack_shop.model;

public class IsPassGroupBuy {
    private Long id;
    private String title;
    private Double buy_price;
    private Double original_price;
    private String appointment;
    private String date_start;
    private String date_end;
    private String time_start;
    private String time_end;
    private String group_buy_img;
    private String created;
    private Long shop_id;
    private Integer state;
    private Integer buy_person_limit;
    private Integer stock_count;
    private Integer once_count;
    private String unavailable_date;
    private String diners_number_id;
    private Integer is_release;
    private Integer type_of_id;

    public Integer getType_of_id() {
        return type_of_id;
    }

    public void setType_of_id(Integer type_of_id) {
        this.type_of_id = type_of_id;
    }

    public Integer getIs_release() {
        return is_release;
    }

    public void setIs_release(Integer is_release) {
        this.is_release = is_release;
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

    public Double getBuy_price() {
        return buy_price;
    }

    public void setBuy_price(Double buy_price) {
        this.buy_price = buy_price;
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

    public Double getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(Double original_price) {
        this.original_price = original_price;
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

    public String getGroup_buy_img() {
        return group_buy_img;
    }

    public void setGroup_buy_img(String group_buy_img) {
        this.group_buy_img = group_buy_img;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public String getDiners_number_id() {
        return diners_number_id;
    }

    public void setDiners_number_id(String diners_number_id) {
        this.diners_number_id = diners_number_id;
    }
}