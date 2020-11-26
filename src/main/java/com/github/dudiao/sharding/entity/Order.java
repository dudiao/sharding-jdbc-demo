package com.github.dudiao.sharding.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author songyinyin
 * @date 2020/11/26 下午 10:53
 */
@Data
@TableName("user_order")
public class Order {

    @TableId("order_key")
    private Long orderKey;

    @TableField("user_id")
    private String userId;

    private float amount;

    private Date createTime;
}
