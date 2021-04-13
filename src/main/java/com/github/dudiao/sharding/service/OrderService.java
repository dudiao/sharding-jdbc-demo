package com.github.dudiao.sharding.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
     */
    List<Order> getOrderByUser(long userId);

    /**
     * 分页获取用户的订单
     *
     * @param currentPage 当前页
     * @param pageSize 分页大小
     * @return 用户订单
     */
    IPage<Order> pageQuery(long currentPage, long pageSize);
}
