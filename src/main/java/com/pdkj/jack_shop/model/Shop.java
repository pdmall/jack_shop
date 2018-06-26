package com.pdkj.jack_shop.model;

import java.util.Date;
import javax.persistence.*;

public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "shop_address")
    private String shopAddress;

    private String city;

    private String province;

    @Column(name = "shop_phone")
    private String shopPhone;

    /**
     * 2，打洋
1，营业
0，冻结
     */
    @Column(name = "shop_state")
    private Integer shopState;

    /**
     * 开业时间
     */
    @Column(name = "buss_open")
    private Date bussOpen;

    /**
     * 歇业时间
     */
    @Column(name = "buss_close")
    private Date bussClose;

    private Date created;

    private Date updated;

    private String longitude;

    private String latitude;

    /**
     * 平均消费
     */
    @Column(name = "average_cons")
    private Long averageCons;

    /**
     * 店铺简介
     */
    private String introduce;

    /**
     * 营业执照
     */
    @Column(name = "license_img")
    private String licenseImg;

    @Column(name = "service_score")
    private Integer serviceScore;

    @Column(name = "enviro_score")
    private Integer enviroScore;

    /**
     * 评分
     */
    @Column(name = "taste_score")
    private Integer tasteScore;

    /**
     * 商家首页
     */
    @Column(name = "home_img")
    private String homeImg;

    /**
     * 店铺详情图片","号分割
     */
    @Column(name = "detail_imgs")
    private String detailImgs;

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

    /**
     * @return shop_name
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * @param shopName
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * @return shop_address
     */
    public String getShopAddress() {
        return shopAddress;
    }

    /**
     * @param shopAddress
     */
    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return shop_phone
     */
    public String getShopPhone() {
        return shopPhone;
    }

    /**
     * @param shopPhone
     */
    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    /**
     * 获取2，打洋
1，营业
0，冻结
     *
     * @return shop_state - 2，打洋
1，营业
0，冻结
     */
    public Integer getShopState() {
        return shopState;
    }

    /**
     * 设置2，打洋
1，营业
0，冻结
     *
     * @param shopState 2，打洋
1，营业
0，冻结
     */
    public void setShopState(Integer shopState) {
        this.shopState = shopState;
    }

    /**
     * 获取开业时间
     *
     * @return buss_open - 开业时间
     */
    public Date getBussOpen() {
        return bussOpen;
    }

    /**
     * 设置开业时间
     *
     * @param bussOpen 开业时间
     */
    public void setBussOpen(Date bussOpen) {
        this.bussOpen = bussOpen;
    }

    /**
     * 获取歇业时间
     *
     * @return buss_close - 歇业时间
     */
    public Date getBussClose() {
        return bussClose;
    }

    /**
     * 设置歇业时间
     *
     * @param bussClose 歇业时间
     */
    public void setBussClose(Date bussClose) {
        this.bussClose = bussClose;
    }

    /**
     * @return created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return updated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * @param updated
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * @return longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取平均消费
     *
     * @return average_cons - 平均消费
     */
    public Long getAverageCons() {
        return averageCons;
    }

    /**
     * 设置平均消费
     *
     * @param averageCons 平均消费
     */
    public void setAverageCons(Long averageCons) {
        this.averageCons = averageCons;
    }

    /**
     * 获取店铺简介
     *
     * @return introduce - 店铺简介
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * 设置店铺简介
     *
     * @param introduce 店铺简介
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    /**
     * 获取营业执照
     *
     * @return license_img - 营业执照
     */
    public String getLicenseImg() {
        return licenseImg;
    }

    /**
     * 设置营业执照
     *
     * @param licenseImg 营业执照
     */
    public void setLicenseImg(String licenseImg) {
        this.licenseImg = licenseImg;
    }

    /**
     * @return service_score
     */
    public Integer getServiceScore() {
        return serviceScore;
    }

    /**
     * @param serviceScore
     */
    public void setServiceScore(Integer serviceScore) {
        this.serviceScore = serviceScore;
    }

    /**
     * @return enviro_score
     */
    public Integer getEnviroScore() {
        return enviroScore;
    }

    /**
     * @param enviroScore
     */
    public void setEnviroScore(Integer enviroScore) {
        this.enviroScore = enviroScore;
    }

    /**
     * 获取评分
     *
     * @return taste_score - 评分
     */
    public Integer getTasteScore() {
        return tasteScore;
    }

    /**
     * 设置评分
     *
     * @param tasteScore 评分
     */
    public void setTasteScore(Integer tasteScore) {
        this.tasteScore = tasteScore;
    }

    /**
     * 获取商家首页
     *
     * @return home_img - 商家首页
     */
    public String getHomeImg() {
        return homeImg;
    }

    /**
     * 设置商家首页
     *
     * @param homeImg 商家首页
     */
    public void setHomeImg(String homeImg) {
        this.homeImg = homeImg;
    }

    /**
     * 获取店铺详情图片","号分割
     *
     * @return detail_imgs - 店铺详情图片","号分割
     */
    public String getDetailImgs() {
        return detailImgs;
    }

    /**
     * 设置店铺详情图片","号分割
     *
     * @param detailImgs 店铺详情图片","号分割
     */
    public void setDetailImgs(String detailImgs) {
        this.detailImgs = detailImgs;
    }
}