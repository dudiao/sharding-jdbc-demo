package com.github.dudiao.sharding.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.dudiao.sharding.config.DataSourceConfiguration;
import com.github.dudiao.sharding.entity.Order;
import com.github.dudiao.sharding.mapper.OrderMapper;
import com.github.dudiao.sharding.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author songyinyin
 * @date 2020/11/26 下午 11:07
 */
@Slf4j
@Service
@DS(DataSourceConfiguration.SHARDING_DATA_SOURCE_NAME)
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public List<Order> getOrderByUser(long userId) {
        return baseMapper.selectList(Wrappers.<Order>lambdaQuery()
                .eq(Order::getUserId, userId));
    }

    /**
     * 分页获取用户的订单
     *
     * @param currentPage 当前页
     * @param pageSize    分页大小
     * @return 用户订单
     */
    @Override
    public IPage<Order> pageQuery(long currentPage, long pageSize) {
        Page<Order> page = baseMapper.selectPage(new Page<>(currentPage, pageSize), null);
        return page;
    }
}
