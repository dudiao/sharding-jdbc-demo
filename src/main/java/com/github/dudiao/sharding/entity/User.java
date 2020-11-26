package com.github.dudiao.sharding.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author songyinyin
 * @date 2020/11/26 下午 08:07
 */
@Data
public class User {

    @TableId
    private Long id;

    private String name;

    private Integer age;

    private String email;
}
