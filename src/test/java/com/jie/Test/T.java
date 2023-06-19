package com.jie.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jie.pojo.Car;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class T {
//    private final Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);
    @Resource
    RedisTemplate redisTemplate;
    // JSON工具
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testOne() {
        redisTemplate.opsForValue().set("name", "卷心",20, TimeUnit.SECONDS);
    }
    @Test
    void testTwo() throws IOException {
        Car car = new Car(null,"KK0001","大型客车","硬座",40);
//        redisTemplate.opsForValue().set("car",car);
        //  手动序列化
        String json = mapper.writeValueAsString(car);
        redisTemplate.opsForValue().set("car", json);
        String carJson = (String) redisTemplate.opsForValue().get("car");
        // 反序列化
        Car car1 = mapper.readValue(carJson, Car.class);
        System.out.println(car1);
    }



}
