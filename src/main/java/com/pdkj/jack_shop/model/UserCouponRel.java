package com.pdkj.jack_shop.model;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.model
 * @author lvchong
 * @date 2018/7/24 13:40
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import java.util.Date;

/**
 * @author lvchong
 * @ClassName UserCouponRel
 * @Description 用户的代金卷
 * @date 2018/7/24
 */
public class UserCouponRel {
    private Long id;
    private Long user_id;
    private Long coupon_id;
    private Integer is_use;
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(Long coupon_id) {
        this.coupon_id = coupon_id;
    }

    public Integer getIs_use() {
        return is_use;
    }

    public void setIs_use(Integer is_use) {
        this.is_use = is_use;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
