package com.pdkj.jack_shop.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "user_concern")
public class UserConcern {
    @Id
    private Long id;

    /**
     * 商户id
     */
    private Long shop_id;

    /**
     * 粉丝id
     */
    private Long be_user_id;

    private String created;

    private Integer is_cancel;

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Integer getIs_cancel() {
        return is_cancel;
    }

    public void setIs_cancel(Integer is_cancel) {
        this.is_cancel = is_cancel;
    }

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