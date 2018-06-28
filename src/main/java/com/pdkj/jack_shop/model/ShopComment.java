package com.pdkj.jack_shop.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "shop_comment")
public class ShopComment {


    private Long id;

    private Long user_id;

    /**
     * 店铺id
     */
    private Long shop_id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String user_img;

    /**
     * 评论
     */
    private String comment;

    /**
     * 味道分
     */
    private Integer taste_score;

    /**
     * 环境分
     */
    private Integer enviro_score;

    /**
     * 服务分
     */
    private Integer service_score;

    private Date created;

    /**
     * 订单id
     */
    private Long order_id;

    private String shop_reply;

    /**
     * 0,不显示
1,显示
     */
    private Integer state;

    /**
     * 评论图片，都好隔开
     */
    private String comment_img;


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

    public Long getShop_id() {
        return shop_id;
    }

    public void setShop_id(Long shop_id) {
        this.shop_id = shop_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getTaste_score() {
        return taste_score;
    }

    public void setTaste_score(Integer taste_score) {
        this.taste_score = taste_score;
    }

    public Integer getEnviro_score() {
        return enviro_score;
    }

    public void setEnviro_score(Integer enviro_score) {
        this.enviro_score = enviro_score;
    }

    public Integer getService_score() {
        return service_score;
    }

    public void setService_score(Integer service_score) {
        this.service_score = service_score;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public String getShop_reply() {
        return shop_reply;
    }

    public void setShop_reply(String shop_reply) {
        this.shop_reply = shop_reply;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getComment_img() {
        return comment_img;
    }

    public void setComment_img(String comment_img) {
        this.comment_img = comment_img;
    }
}