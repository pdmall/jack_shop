package com.pdkj.jack_shop.service;
import com.pdkj.jack_shop.model.User;
import com.pdkj.jack_shop.core.Service;

import java.util.HashMap;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/06/26.
 */
public interface UserService extends Service<User> {

    User getUserByToken(String token);
}
