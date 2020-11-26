package com.github.dudiao.sharding.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    @DS(DataSourceConfiguration.SHARDING_DATA_SOURCE_NAME)
    public List<Order> getOrderByUser(long userId) {
        return baseMapper.selectList(Wrappers.<Order>lambdaQuery()
                .eq(Order::getUserId, userId));
    }

}
