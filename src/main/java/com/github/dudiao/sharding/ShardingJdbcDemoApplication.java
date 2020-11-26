package com.github.dudiao.sharding;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * sharding jdbc 示例
 *
 * <p>项目中使用druid连接池时，需要排除原生Druid的配置类</p>
 *
 * @author songyinyin
 * @date 2020/11/26 下午 08:00
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class ShardingJdbcDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcDemoApplication.class, args);
    }

}
