package com.pdkj.jack_shop.model;

import javax.persistence.*;

@Table(name = "coupon_item")
public class CouponItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coupon_id")
    private Long couponId;

    /**
     * 商品名备注
     */
    @Column(name = "item_name")
    private String itemName;

    /**
     * 价格备注
     */
    @Column(name = "item_price")
    private String itemPrice;

    /**
     * 数量
     */
    @Column(name = "item_num")
    private Integer itemNum;

    /**
     * 查询 unit 表
     */
    @Column(name = "item_unit")
    private String itemUnit;

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
     * @return coupon_id
     */
    public Long getCouponId() {
        return couponId;
    }

    /**
     * @param couponId
     */
    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    /**
     * 获取商品名备注
     *
     * @return item_name - 商品名备注
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 设置商品名备注
     *
     * @param itemName 商品名备注
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 获取价格备注
     *
     * @return item_price - 价格备注
     */
    public String getItemPrice() {
        return itemPrice;
    }

    /**
     * 设置价格备注
     *
     * @param itemPrice 价格备注
     */
    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    /**
     * 获取数量
     *
     * @return item_num - 数量
     */
    public Integer getItemNum() {
        return itemNum;
    }

    /**
     * 设置数量
     *
     * @param itemNum 数量
     */
    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    /**
     * 获取查询 unit 表
     *
     * @return item_unit - 查询 unit 表
     */
    public String getItemUnit() {
        return itemUnit;
    }

    /**
     * 设置查询 unit 表
     *
     * @param itemUnit 查询 unit 表
     */
    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }
}