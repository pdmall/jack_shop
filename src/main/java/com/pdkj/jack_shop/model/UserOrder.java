package com.pdkj.jack_shop.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "user_order")
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 支付平台编号
     */
    private String pay_on;

    /**
     * 商品，优惠卷,id ...
     */
    private Long item_id;

    /**
     * 0，待支付
1，待消费
2，待评论
3，完成

     */
    private Integer state;

    /**
     * 1，微信支付
     */
    private Integer pay_type;

    private Long user_id;

    /**
     * 下单时间
     */
    private Date created;

    /**
     * 购买时间
     */
    private Date buy_time;

    /**
     * 使用时间
     */
    private Date use_time;

    /**
     * 抵扣卷价格
     */
    private Long use_coupon_id;

    /**
     * jack 钱包余额
     */
    private BigDecimal wallet_money;

    /**
     * 总费用
     */
    private BigDecimal total_price;

    /**
     * 最后付款金额
     */
    private BigDecimal final_price;

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
     * 获取0，待支付
1，待消费
2，待评论
3，完成

     *
     * @return state - 0，待支付
1，待消费
2，待评论
3，完成

     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置0，待支付
1，待消费
2，待评论
3，完成

     *
     * @param state 0，待支付
1，待消费
2，待评论
3，完成

     */
    public void setState(Integer state) {
        this.state = state;
    }


    public String getPay_on() {
        return pay_on;
    }

    public void setPay_on(String pay_on) {
        this.pay_on = pay_on;
    }

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
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

    public Date getBuy_time() {
        return buy_time;
    }

    public void setBuy_time(Date buy_time) {
        this.buy_time = buy_time;
    }

    public Date getUse_time() {
        return use_time;
    }

    public void setUse_time(Date use_time) {
        this.use_time = use_time;
    }

    public Long getUse_coupon_id() {
        return use_coupon_id;
    }

    public void setUse_coupon_id(Long use_coupon_id) {
        this.use_coupon_id = use_coupon_id;
    }

    public BigDecimal getWallet_money() {
        return wallet_money;
    }

    public void setWallet_money(BigDecimal wallet_money) {
        this.wallet_money = wallet_money;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    public BigDecimal getFinal_price() {
        return final_price;
    }

    public void setFinal_price(BigDecimal final_price) {
        this.final_price = final_price;
    }
}