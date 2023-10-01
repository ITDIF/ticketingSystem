package com.jie;

import com.jie.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TicketingSystemApplicationTests {
    @Autowired
    private UserService userService;
    @Test
    void sqlTest() {

        System.out.println(userService.queryUserList());
    }

}
