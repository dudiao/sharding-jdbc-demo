关于分表+多数据源的介绍，我写了两篇文章，可以参考着看下：

- 踩坑sharding jdbc，集成多数据源：https://mp.weixin.qq.com/s/LOcjg0lrV9ZbELBVWlTR2g
- 你要的分表+多数据源 Demo 源码来了：https://mp.weixin.qq.com/s/A6xpLHfKXtcCdBlQ3vhtlg

# 如何运行本项目？

## 1. 初始化数据库
运行`sharding.sql`，会创建两个数据库：`no_sharding`和`order_sharding`
```

├── no_shrding
│   ├── user           用户表
│── order_sharding
│   ├── user_order_0   用户订单表
│   ├── user_order_1   用户订单表
   
```

## 2. 修改数据库信息
在项目中resources目录下的`application.yml`、`application-sharding.yml`和`application-hint.yml`，将数据源信息修改为自己的。

## 3. 运行测试类
测试类中，分别激活了sharding和hint的配置文件。

整个示例比较简单：用户的订单表进行了分表，先根据用户名称查询用户id，再去查询该用户的订单。麻雀虽小五脏俱全。