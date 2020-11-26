package com.github.dudiao.sharding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.dudiao.sharding.entity.Order;

import java.util.List;

/**
 * @author songyinyin
 * @date 2020/11/26 下午 11:05
 */
public interface OrderService extends IService<Order> {

    /**
     * 获取用户的订单
     *
     * @param userId 用户id
     * @return 用户订单
     * @throws Exception
     */
    List<Order> getOrderByUser(long userId);
}
