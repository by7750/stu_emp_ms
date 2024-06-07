package com.ywk.sems;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ywk.sems.mapper")
public class StuEmpMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StuEmpMsApplication.class, args);
    }

}
