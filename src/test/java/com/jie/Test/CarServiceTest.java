package com.jie.Test;

import com.jie.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CarTest {
    @Autowired
    CarService carService;
    @Test
    public void queryUserList(){
        System.out.println(carService.queryCarList());
    }
}
