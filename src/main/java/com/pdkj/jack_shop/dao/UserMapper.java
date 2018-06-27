package com.pdkj.jack_shop.dao;

import com.pdkj.jack_shop.core.Mapper;
import com.pdkj.jack_shop.model.User;

import java.util.HashMap;
import java.util.List;

public interface UserMapper extends Mapper<User> {
    List<User> test();
}