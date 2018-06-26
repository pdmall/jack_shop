package com.pdkj.jack_shop.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_shop_rel")
public class UserShopRel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "user_id")
    private Long userId;

    /**
     * 用户店铺角色
     */
    @Column(name = "user_role")
    private Integer userRole;

    private Date created;

    private Date updated;

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
     * @return shop_id
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * @param shopId
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
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
     * 获取用户店铺角色
     *
     * @return user_role - 用户店铺角色
     */
    public Integer getUserRole() {
        return userRole;
    }

    /**
     * 设置用户店铺角色
     *
     * @param userRole 用户店铺角色
     */
    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
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
}