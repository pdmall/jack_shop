package com.pdkj.jack_shop.model;

import javax.persistence.*;

@Table(name = "user_concern")
public class UserConcern {
    @Id
    private Long id;

    /**
     * 用户id 和 商户id 二选一
     */
    private Long user_id;

    /**
     * 商户id 和 用户id 二选一
     */
    private Long shop_id;

    /**
     * 粉丝id
     */
    private Long be_user_id;

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


    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getShop_id() {
        return shop_id;
    }

    public void setShop_id(Long shop_id) {
        this.shop_id = shop_id;
    }

    public Long getBe_user_id() {
        return be_user_id;
    }

    public void setBe_user_id(Long be_user_id) {
        this.be_user_id = be_user_id;
    }
}