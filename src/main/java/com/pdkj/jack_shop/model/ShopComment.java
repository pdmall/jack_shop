package com.pdkj.jack_shop.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "shop_comment")
public class ShopComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    /**
     * 店铺id
     */
    @Column(name = "shop_id")
    private Long shopId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    @Column(name = "user_img")
    private String userImg;

    /**
     * 评论
     */
    private String comment;

    /**
     * 味道分
     */
    @Column(name = "taste_score")
    private Integer tasteScore;

    /**
     * 环境分
     */
    @Column(name = "enviro_score")
    private Integer enviroScore;

    /**
     * 服务分
     */
    @Column(name = "service_score")
    private Integer serviceScore;

    private Date created;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "shop_reply")
    private String shopReply;

    /**
     * 0,不显示
1,显示
     */
    private Integer state;

    /**
     * 评论图片，都好隔开
     */
    @Column(name = "comment_img")
    private String commentImg;

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
     * 获取店铺id
     *
     * @return shop_id - 店铺id
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 设置店铺id
     *
     * @param shopId 店铺id
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取用户头像
     *
     * @return user_img - 用户头像
     */
    public String getUserImg() {
        return userImg;
    }

    /**
     * 设置用户头像
     *
     * @param userImg 用户头像
     */
    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    /**
     * 获取评论
     *
     * @return comment - 评论
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置评论
     *
     * @param comment 评论
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 获取味道分
     *
     * @return taste_score - 味道分
     */
    public Integer getTasteScore() {
        return tasteScore;
    }

    /**
     * 设置味道分
     *
     * @param tasteScore 味道分
     */
    public void setTasteScore(Integer tasteScore) {
        this.tasteScore = tasteScore;
    }

    /**
     * 获取环境分
     *
     * @return enviro_score - 环境分
     */
    public Integer getEnviroScore() {
        return enviroScore;
    }

    /**
     * 设置环境分
     *
     * @param enviroScore 环境分
     */
    public void setEnviroScore(Integer enviroScore) {
        this.enviroScore = enviroScore;
    }

    /**
     * 获取服务分
     *
     * @return service_score - 服务分
     */
    public Integer getServiceScore() {
        return serviceScore;
    }

    /**
     * 设置服务分
     *
     * @param serviceScore 服务分
     */
    public void setServiceScore(Integer serviceScore) {
        this.serviceScore = serviceScore;
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
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * @return shop_reply
     */
    public String getShopReply() {
        return shopReply;
    }

    /**
     * @param shopReply
     */
    public void setShopReply(String shopReply) {
        this.shopReply = shopReply;
    }

    /**
     * 获取0,不显示
1,显示
     *
     * @return state - 0,不显示
1,显示
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置0,不显示
1,显示
     *
     * @param state 0,不显示
1,显示
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取评论图片，都好隔开
     *
     * @return comment_img - 评论图片，都好隔开
     */
    public String getCommentImg() {
        return commentImg;
    }

    /**
     * 设置评论图片，都好隔开
     *
     * @param commentImg 评论图片，都好隔开
     */
    public void setCommentImg(String commentImg) {
        this.commentImg = commentImg;
    }
}