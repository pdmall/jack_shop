package com.pdkj.jack_shop.model;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.model
 * @author lvchong
 * @date 2018/7/3 17:18
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import javax.persistence.Table;
import java.util.Date;

/**
 * @author lvchong
 * @ClassName signIn
 * @Description 类描述
 * @date 2018/7/3
 */
@Table(name = "sing_in")
public class SignIn {
    private Long id;
    private Long user_id;
    private Date created;
    private String reward;

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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }
}
