package com.pdkj.jack_shop.model;
/**
 * @Project: shop_seller
 * @Package com.pdkj.jack_shop.model
 * @author lvchong
 * @date 2018/8/21 14:15
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

/**
 * @author lvchong
 * @ClassName shopWallet
 * @Description 类描述
 * @date 2018/8/21
 */
public class ShopWallet {
    private Long id;

    private Long shop_id;

    private Double money;

    private Integer wallet_state;

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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getWallet_state() {
        return wallet_state;
    }

    public void setWallet_state(Integer wallet_state) {
        this.wallet_state = wallet_state;
    }
}
