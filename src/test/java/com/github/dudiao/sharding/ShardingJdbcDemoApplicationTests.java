package com.github.dudiao.sharding;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.dudiao.sharding.entity.Order;
import com.github.dudiao.sharding.entity.User;
import com.github.dudiao.sharding.service.OrderService;
import com.github.dudiao.sharding.service.UserService;
import com.github.dudiao.sharding.utils.StopWatch;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Slf4j
@SpringBootTest(properties = "spring.profiles.active=dev")
class ShardingJdbcDemoApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @Test
    public void shardingJdbcTest() {
        // 使用默认数据源（即不分表的数据源）
        User dudiao = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getName, "读钓"));

        // 使用@DS，切换到分表数据源
        List<Order> orderList = orderService.getOrderByUser(dudiao.getId());

        log.info("==> \n用户：{} \n订单：{}", dudiao, orderList);
        Assert.isTrue(orderList.size() == 1, "订单数不正确！");
    }

    @Test
    public void shardingJdbcQueryTest() {
        // 使用@DS，分页查询
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("query");
        IPage<Order> page = orderService.pageQuery(9, 10);
        stopWatch.stop();

        stopWatch.start("query1");
        IPage<Order> page1 = orderService.pageQuery(3, 10);
        stopWatch.stop();

        stopWatch.start("query2");
        IPage<Order> page2 = orderService.pageQuery(11, 10);
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }

    /**
     * 造一些测试数据
     */
    @Test
    public void initData() {
        Faker faker = new Faker(Locale.CHINA);
        int userNum = 10000;
        for (int j = 0; j < userNum; j++) {
            User user = new User();
            user.setName(faker.name().fullName());
            user.setAge(faker.number().numberBetween(18, 60));
            user.setEmail(faker.internet().emailAddress());
            userService.save(user);

            Date to = new Date();
            Date from = new Date(LocalDateTime.now().minus(10, ChronoUnit.YEARS).getSecond() * 1000);

            int orderNum = 500;
            List<Order> orderList = new ArrayList<>(orderNum);
            for (int i = 0; i < orderNum; i++) {
                Order order = new Order();
                order.setUserId(user.getId());
                order.setAmount(faker.number().randomDouble(2, 10, 20000));
                order.setCreateTime(faker.date().between(from, to));
                orderList.add(order);
            }
            orderService.saveBatch(orderList);
            orderList.clear();
        }
    }

}
