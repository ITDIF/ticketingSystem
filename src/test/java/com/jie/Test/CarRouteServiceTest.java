package com.jie.Test;

import com.jie.service.CarRouteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CarRouteServiceTest {
    @Autowired
    CarRouteService carRouteService;

    @Test
    public void query(){
        System.out.println(carRouteService.queryCarRouteBySE("新余","赣州","2023-05-20"));
    }
}
