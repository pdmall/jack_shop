package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/7/21 10:34
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import java.sql.Date;

/**
 * @author lvchong
 * @ClassName ShareOrgin
 * @Description 类描述
 * @date 2018/7/21
 */
public class ShareOrgin {
    private Long id;
    private Long level1;
    private Long level2;
    private Long level3;
    private Date created;
    private Integer parameter_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLevel1() {
        return level1;
    }

    public void setLevel1(Long level1) {
        this.level1 = level1;
    }

    public Long getLevel2() {
        return level2;
    }

    public void setLevel2(Long level2) {
        this.level2 = level2;
    }

    public Long getLevel3() {
        return level3;
    }

    public void setLevel3(Long level3) {
        this.level3 = level3;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getParameter_id() {
        return parameter_id;
    }

    public void setParameter_id(Integer parameter_id) {
        this.parameter_id = parameter_id;
    }
}