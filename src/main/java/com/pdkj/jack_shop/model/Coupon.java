package com.pdkj.jack_shop.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 卷名称
     */
    private String title;

    /**
     * 1,团餐卷
2,待金卷
3,折扣卷
     */
    private Integer type;

    /**
     * 折扣 范围 0-99
     */
    private Integer discount;

    /**
     * 购买价
     */
    @Column(name = "buy_price")
    private BigDecimal buyPrice;

    /**
     * 抵扣价格
     */
    @Column(name = "final_price")
    private BigDecimal finalPrice;

    /**
     * 是否可退
1，随时退
0，不可退
     */
    @Column(name = "is_refund")
    private Integer isRefund;

    /**
     * 0,免预约
1,提前一小时预约
2，提前2销售预约
     */
    @Column(name = "sub_time")
    private Integer subTime;

    /**
     * 优惠描述，团餐菜品介绍 ；好换行
     */
    private String describe;

    /**
     * 有效开始日期
     */
    @Column(name = "date_start")
    private Date dateStart;

    /**
     * 有效结束日期
     */
    @Column(name = "date_end")
    private Date dateEnd;

    /**
     * 使用开始时间
     */
    @Column(name = "time_start")
    private Date timeStart;

    /**
     * 使用结束时间
     */
    @Column(name = "time_end")
    private Date timeEnd;

    /**
     * 0,随时可用
1,周末不可用
2,周六，周日不可用
3,节假日不可用
     */
    @Column(name = "unable_date")
    private Integer unableDate;

    @Column(name = "coupon_img")
    private String couponImg;

    private Date created;

    /**
     * 商家id
     */
    @Column(name = "shop_id")
    private Long shopId;

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
     * 获取卷名称
     *
     * @return title - 卷名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置卷名称
     *
     * @param title 卷名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取1,团餐卷
2,待金卷
3,折扣卷
     *
     * @return type - 1,团餐卷
2,待金卷
3,折扣卷
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1,团餐卷
2,待金卷
3,折扣卷
     *
     * @param type 1,团餐卷
2,待金卷
3,折扣卷
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取折扣 范围 0-99
     *
     * @return discount - 折扣 范围 0-99
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     * 设置折扣 范围 0-99
     *
     * @param discount 折扣 范围 0-99
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    /**
     * 获取购买价
     *
     * @return buy_price - 购买价
     */
    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    /**
     * 设置购买价
     *
     * @param buyPrice 购买价
     */
    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    /**
     * 获取抵扣价格
     *
     * @return final_price - 抵扣价格
     */
    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    /**
     * 设置抵扣价格
     *
     * @param finalPrice 抵扣价格
     */
    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    /**
     * 获取是否可退
1，随时退
0，不可退
     *
     * @return is_refund - 是否可退
1，随时退
0，不可退
     */
    public Integer getIsRefund() {
        return isRefund;
    }

    /**
     * 设置是否可退
1，随时退
0，不可退
     *
     * @param isRefund 是否可退
1，随时退
0，不可退
     */
    public void setIsRefund(Integer isRefund) {
        this.isRefund = isRefund;
    }

    /**
     * 获取0,免预约
1,提前一小时预约
2，提前2销售预约
     *
     * @return sub_time - 0,免预约
1,提前一小时预约
2，提前2销售预约
     */
    public Integer getSubTime() {
        return subTime;
    }

    /**
     * 设置0,免预约
1,提前一小时预约
2，提前2销售预约
     *
     * @param subTime 0,免预约
1,提前一小时预约
2，提前2销售预约
     */
    public void setSubTime(Integer subTime) {
        this.subTime = subTime;
    }

    /**
     * 获取优惠描述，团餐菜品介绍 ；好换行
     *
     * @return describe - 优惠描述，团餐菜品介绍 ；好换行
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 设置优惠描述，团餐菜品介绍 ；好换行
     *
     * @param describe 优惠描述，团餐菜品介绍 ；好换行
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * 获取有效开始日期
     *
     * @return date_start - 有效开始日期
     */
    public Date getDateStart() {
        return dateStart;
    }

    /**
     * 设置有效开始日期
     *
     * @param dateStart 有效开始日期
     */
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    /**
     * 获取有效结束日期
     *
     * @return date_end - 有效结束日期
     */
    public Date getDateEnd() {
        return dateEnd;
    }

    /**
     * 设置有效结束日期
     *
     * @param dateEnd 有效结束日期
     */
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    /**
     * 获取使用开始时间
     *
     * @return time_start - 使用开始时间
     */
    public Date getTimeStart() {
        return timeStart;
    }

    /**
     * 设置使用开始时间
     *
     * @param timeStart 使用开始时间
     */
    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    /**
     * 获取使用结束时间
     *
     * @return time_end - 使用结束时间
     */
    public Date getTimeEnd() {
        return timeEnd;
    }

    /**
     * 设置使用结束时间
     *
     * @param timeEnd 使用结束时间
     */
    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    /**
     * 获取0,随时可用
1,周末不可用
2,周六，周日不可用
3,节假日不可用
     *
     * @return unable_date - 0,随时可用
1,周末不可用
2,周六，周日不可用
3,节假日不可用
     */
    public Integer getUnableDate() {
        return unableDate;
    }

    /**
     * 设置0,随时可用
1,周末不可用
2,周六，周日不可用
3,节假日不可用
     *
     * @param unableDate 0,随时可用
1,周末不可用
2,周六，周日不可用
3,节假日不可用
     */
    public void setUnableDate(Integer unableDate) {
        this.unableDate = unableDate;
    }

    /**
     * @return coupon_img
     */
    public String getCouponImg() {
        return couponImg;
    }

    /**
     * @param couponImg
     */
    public void setCouponImg(String couponImg) {
        this.couponImg = couponImg;
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
     * 获取商家id
     *
     * @return shop_id - 商家id
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 设置商家id
     *
     * @param shopId 商家id
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}