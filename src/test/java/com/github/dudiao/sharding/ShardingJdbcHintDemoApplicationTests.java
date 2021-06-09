package com.github.dudiao.sharding;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.dudiao.sharding.entity.Order;
import com.github.dudiao.sharding.entity.User;
import com.github.dudiao.sharding.service.OrderService;
import com.github.dudiao.sharding.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@SpringBootTest(properties = "spring.profiles.active=hint")
class ShardingJdbcHintDemoApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    /**
     * 强制路由
     */
    @Test
    public void shardingJdbcHintTest() {
        // 使用默认数据源（即不分表的数据源）
        User dudiao = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getName, "读钓"));

        // 强制路由到 user_order_1 这张表上
        try (HintManager hintManager = HintManager.getInstance()) {
            // 如果不强制路由，则这里查的是user_order_1
            hintManager.addTableShardingValue("user_order", 0);
            List<Order> orderList = orderService.getOrderByUser(dudiao.getId());

            log.info("==> \n用户：{} \n订单：{}", dudiao, orderList);
            Assert.isTrue(orderList.size() == 1 && orderList.get(0).getAmount() == 150,
                    "订单数或者订单金额不正确！");
        }

    }

}
