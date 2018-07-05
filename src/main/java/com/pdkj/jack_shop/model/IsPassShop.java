package com.pdkj.jack_shop.model;

import com.pdkj.jack_shop.util.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IsPassShop {
    private Long id;

    private String shop_name;

    private String shop_address;

    private String city;

    private String province;

    private String shop_phone;

    /**
     *  2，打洋
     *  1，营业
     *  0，冻结
     */
    private Integer shop_state;

    /**
     * 开业时间
     */
    private Date buss_open;

    /**
     * 歇业时间
     */
    private Date buss_close;

    private Date created;

    private Date updated;

    private String longitude;

    private String latitude;

    private String county;

    private String food_safety_permit_img;
    private Long shop_id;

    public Long getShop_id() {
        return shop_id;
    }

    public void setShop_id(Long shop_id) {
        this.shop_id = shop_id;
    }

    public String getFood_safety_permit_img() {
        return food_safety_permit_img;
    }

    public void setFood_safety_permit_img(String food_safety_permit_img) {
        this.food_safety_permit_img = food_safety_permit_img;
    }
    /**
     * 平均消费
     */
    private Long average_cons;

    /**
     * 店铺简介
     */
    private String introduce;

    /**
     * 营业执照
     */
    private String license_img;

    private Integer service_score;

    private Integer enviro_score;

    private String street;

    /**
     * 评分
     */
    private Integer taste_score;

    /**
     * 商家首页
     */
    private String home_img;

    /**
     * 店铺详情图片","号分割
     */
    private String detail_imgs;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getShop_phone() {
        return shop_phone;
    }

    public void setShop_phone(String shop_phone) {
        this.shop_phone = shop_phone;
    }

    public Integer getShop_state() {
        return shop_state;
    }

    public void setShop_state(Integer shop_state) {
        this.shop_state = shop_state;
    }

    public Date getBuss_open() {
        return buss_open;
    }

    public void setBuss_open(String buss_open) throws ParseException {
       if(DateUtils.isLegalDate(buss_open,"HH:mm")){
           SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
           this.buss_open = sdf.parse(buss_open+":00");
       }
    }

    public Date getBuss_close() {
        return buss_close;
    }

    public void setBuss_close(String buss_close) throws ParseException {
        if(DateUtils.isLegalDate(buss_close,"HH:mm")){
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            this.buss_close = sdf.parse(buss_close+":00");
        }
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Long getAverage_cons() {
        return average_cons;
    }

    public void setAverage_cons(Long average_cons) {
        this.average_cons = average_cons;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getLicense_img() {
        return license_img;
    }

    public void setLicense_img(String license_img) {
        this.license_img = license_img;
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

    public String getHome_img() {
        return home_img;
    }

    public void setHome_img(String home_img) {
        this.home_img = home_img;
    }

    public String getDetail_imgs() {
        return detail_imgs;
    }

    public void setDetail_imgs(String detail_imgs) {
        this.detail_imgs = detail_imgs;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}