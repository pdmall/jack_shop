package com.pdkj.jack_shop;

import com.pdkj.jack_shop.model.User;
import com.pdkj.jack_shop.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JackShopApplicationTests {


    @Autowired
    UserService userService;

    @Test
    public void contextLoads() {
        User u = userService.getUserByToken("11111");
        System.out.println(u.getName());
        u = userService.getUserByToken("11111");
        System.out.println(u.getName());
        u = userService.getUserByToken("11111");
        System.out.println(u.getName());
        u = userService.getUserByToken("11111");
        System.out.println(u.getName());
        u = userService.getUserByToken("11111");
        System.out.println(u.getName());
    }

}
