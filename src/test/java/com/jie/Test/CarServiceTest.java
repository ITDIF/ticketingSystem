package com.jie.Test;

import com.jie.pojo.Car;
import com.jie.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CarServiceTest {
    @Autowired
    CarService carService;
    @Test
    public void queryUserList(){
        System.out.println(carService.queryCarList());
    }
    @Test
    public void queryCarById(){
        System.out.println(carService.queryCarById(1));
    }
    @Test
    public void addCar(){
        Car car = new Car(null,"2","2","2",2);
        System.out.println(carService.addCar(car));
    }
    @Test
    public void deleteCarById(){
        System.out.println(carService.deleteCarById(2));
    }
}
