package com.pdkj.jack_shop.core;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.core
 * @author lvchong
 * @date 2018/8/20 9:56
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

/**
 * @author lvchong
 * @ClassName app_constant
 * @Description 类描述
 * @date 2018/8/20
 */
public enum  app_constant {
    ONE_LEVEL_SHARING(1),//一级分享id
    TWO_LEVEL_SHARING(2),//二级分享id
    THREE_LEVEL_SHARING(3),//三级分享id
    GOLD_REBATE(4),//一级开黄金会员对二级回扣奖励id
    DIAMOND_REBATE(5),//一级开钻石会员对二级回扣奖励id
    GOLD_LEVEL_REBATE(6),//一级开黄金会员对三级级回扣奖励id
    DIAMOND_LEVEL_REBATE(7),//一级开钻石会员对三级级回扣奖励id
    DIVIDENDS(8),//红利id
    PDKJ_ID(666666),//公司id
    ;//服务器中参数id


    private final int code;

    app_constant(int code) {
        this.code = code;
    }

    public int app_constant() {
        return code;
    }
}
