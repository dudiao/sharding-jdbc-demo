package com.github.dudiao.sharding.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.dudiao.sharding.entity.User;
import com.github.dudiao.sharding.mapper.UserMapper;
import com.github.dudiao.sharding.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author songyinyin
 * @date 2020/11/26 下午 11:04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
