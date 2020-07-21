package com.koocloud.facerecognition.showdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication(exclude={
        RedisRepositoriesAutoConfiguration.class
})
@EnableCaching //开启缓存注解
@CrossOrigin(origins = "*",maxAge = 3600) //开启跨域

public class FaceSaveFeatureApplication {

    public static void main(String[] args) {
        SpringApplication.run(FaceSaveFeatureApplication.class, args);
    }

}
