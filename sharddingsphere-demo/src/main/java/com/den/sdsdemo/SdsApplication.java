package com.den.sdsdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author fatKarin
 */
@SpringBootApplication//(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.den.sdsdemo.mapper")
public class SdsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SdsApplication.class, args);
    }
}
