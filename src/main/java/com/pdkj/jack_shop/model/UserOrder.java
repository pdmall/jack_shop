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
    @Column(name = "pay_on")
    private String payOn;

    /**
     * 商品，优惠卷,id ...
     */
    @Column(name = "item_id")
    private Long itemId;

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
    @Column(name = "pay_type")
    private Integer payType;

    @Column(name = "user_id")
    private Long userId;

    /**
     * 下单时间
     */
    private Date created;

    /**
     * 购买时间
     */
    @Column(name = "buy_time")
    private Date buyTime;

    /**
     * 使用时间
     */
    @Column(name = "use_time")
    private Date useTime;

    /**
     * 抵扣卷价格
     */
    @Column(name = "use_coupon_id")
    private Long useCouponId;

    /**
     * jack 钱包余额
     */
    @Column(name = "wallet_money")
    private BigDecimal walletMoney;

    /**
     * 总费用
     */
    @Column(name = "total_price")
    private BigDecimal totalPrice;

    /**
     * 最后付款金额
     */
    @Column(name = "final_price")
    private BigDecimal finalPrice;

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
     * 获取支付平台编号
     *
     * @return pay_on - 支付平台编号
     */
    public String getPayOn() {
        return payOn;
    }

    /**
     * 设置支付平台编号
     *
     * @param payOn 支付平台编号
     */
    public void setPayOn(String payOn) {
        this.payOn = payOn;
    }

    /**
     * 获取商品，优惠卷,id ...
     *
     * @return item_id - 商品，优惠卷,id ...
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * 设置商品，优惠卷,id ...
     *
     * @param itemId 商品，优惠卷,id ...
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
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

    /**
     * 获取1，微信支付
     *
     * @return pay_type - 1，微信支付
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * 设置1，微信支付
     *
     * @param payType 1，微信支付
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取下单时间
     *
     * @return created - 下单时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置下单时间
     *
     * @param created 下单时间
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * 获取购买时间
     *
     * @return buy_time - 购买时间
     */
    public Date getBuyTime() {
        return buyTime;
    }

    /**
     * 设置购买时间
     *
     * @param buyTime 购买时间
     */
    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    /**
     * 获取使用时间
     *
     * @return use_time - 使用时间
     */
    public Date getUseTime() {
        return useTime;
    }

    /**
     * 设置使用时间
     *
     * @param useTime 使用时间
     */
    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    /**
     * 获取抵扣卷价格
     *
     * @return use_coupon_id - 抵扣卷价格
     */
    public Long getUseCouponId() {
        return useCouponId;
    }

    /**
     * 设置抵扣卷价格
     *
     * @param useCouponId 抵扣卷价格
     */
    public void setUseCouponId(Long useCouponId) {
        this.useCouponId = useCouponId;
    }

    /**
     * 获取jack 钱包余额
     *
     * @return wallet_money - jack 钱包余额
     */
    public BigDecimal getWalletMoney() {
        return walletMoney;
    }

    /**
     * 设置jack 钱包余额
     *
     * @param walletMoney jack 钱包余额
     */
    public void setWalletMoney(BigDecimal walletMoney) {
        this.walletMoney = walletMoney;
    }

    /**
     * 获取总费用
     *
     * @return total_price - 总费用
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * 设置总费用
     *
     * @param totalPrice 总费用
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * 获取最后付款金额
     *
     * @return final_price - 最后付款金额
     */
    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    /**
     * 设置最后付款金额
     *
     * @param finalPrice 最后付款金额
     */
    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }
}