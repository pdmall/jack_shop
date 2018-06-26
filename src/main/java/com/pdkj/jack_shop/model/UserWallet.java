package com.pdkj.jack_shop.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "user_wallet")
public class UserWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private BigDecimal money;

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
     * @return money
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * @param money
     */
    public void setMoney(BigDecimal money) {
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