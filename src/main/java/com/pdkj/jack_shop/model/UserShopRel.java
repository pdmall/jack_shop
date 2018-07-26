package com.pdkj.jack_shop.model;

import java.util.Date;
import javax.persistence.*;

public class UserShopRel {

    private Long id;

    private Long shop_id;

    private Long user_id;

    private String user_name;
    //是否是店长 0 不是 1 是
    private Integer master;
    /**
     *用户店铺角色
     */
    private Integer employee_role_id;

    private Date created;

    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShop_id() {
        return shop_id;
    }

    public void setShop_id(Long shop_id) {
        this.shop_id = shop_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getMaster() {
        return master;
    }

    public void setMaster(Integer master) {
        this.master = master;
    }

    public Integer getEmployee_role_id() {
        return employee_role_id;
    }

    public void setEmployee_role_id(Integer employee_role_id) {
        this.employee_role_id = employee_role_id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}