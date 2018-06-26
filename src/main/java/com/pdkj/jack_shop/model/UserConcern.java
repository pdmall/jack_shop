package com.pdkj.jack_shop.model;

import javax.persistence.*;

@Table(name = "user_concern")
public class UserConcern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id 和 商户id 二选一
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 商户id 和 用户id 二选一
     */
    @Column(name = "shop_id")
    private Long shopId;

    /**
     * 粉丝id
     */
    @Column(name = "be_user_id")
    private Long beUserId;

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
     * 获取用户id 和 商户id 二选一
     *
     * @return user_id - 用户id 和 商户id 二选一
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id 和 商户id 二选一
     *
     * @param userId 用户id 和 商户id 二选一
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取商户id 和 用户id 二选一
     *
     * @return shop_id - 商户id 和 用户id 二选一
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 设置商户id 和 用户id 二选一
     *
     * @param shopId 商户id 和 用户id 二选一
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取粉丝id
     *
     * @return be_user_id - 粉丝id
     */
    public Long getBeUserId() {
        return beUserId;
    }

    /**
     * 设置粉丝id
     *
     * @param beUserId 粉丝id
     */
    public void setBeUserId(Long beUserId) {
        this.beUserId = beUserId;
    }
}