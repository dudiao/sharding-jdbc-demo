package com.github.dudiao.sharding.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis plus 配置类
 *
 * @author songyinyin
 * @date 2020/11/26 下午 08:05
 */
@MapperScan("com.github.dudiao.sharding.mapper")
@Configuration
public class MybatisPlusConfig {

}
