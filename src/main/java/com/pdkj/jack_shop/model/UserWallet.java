package com.pdkj.jack_shop.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "user_wallet")
public class UserWallet {

    @Id
    private Long id;

    private Long user_id;

    private Double money;

    /**
     * 1,正常
0，冻结
     */
    private Integer state;

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

    /**
     * @return money
     */
    public Double getMoney() {
        return money;
    }

    /**
     * @param money
     */
    public void setMoney(Double money) {
        this.money = money;
    }

    /**
     * 获取1,正常
0，冻结
     *
     * @return state - 1,正常
0，冻结
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置1,正常
0，冻结
     *
     * @param state 1,正常
0，冻结
     */
    public void setState(Integer state) {
        this.state = state;
    }
}