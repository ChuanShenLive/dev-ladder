package org.chuanshen.devladder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "org.chuanshen.devladder.mapper")
public class DevLadderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevLadderServerApplication.class, args);
    }

}
