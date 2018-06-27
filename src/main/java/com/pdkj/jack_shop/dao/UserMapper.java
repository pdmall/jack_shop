package com.pdkj.jack_shop.dao;

import com.pdkj.jack_shop.core.Mapper;
import com.pdkj.jack_shop.model.User;

public interface UserMapper extends Mapper<User> {
    User getUserByToken(String token);
}