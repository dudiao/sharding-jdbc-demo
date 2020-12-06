package com.github.dudiao.sharding;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.dudiao.sharding.entity.Order;
import com.github.dudiao.sharding.entity.User;
import com.github.dudiao.sharding.service.OrderService;
import com.github.dudiao.sharding.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@SpringBootTest(properties = "spring.profiles.active=dev")
class ShardingJdbcDemoApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @Test
    void shardingJdbcTest() {
        // 使用默认数据源（即不分表的数据源）
        User dudiao = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getName, "读钓"));

        // 使用@DS，切换到分表数据源
        List<Order> orderList = orderService.getOrderByUser(dudiao.getId());

        log.info("==> \n用户：{} \n订单：{}", dudiao, orderList);
        Assert.isTrue(orderList.size() == 1, "订单数不正确！");
    }

}
