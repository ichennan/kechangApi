package com.nglar.kechang;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class KechangApplication {

    public static void main(String[] args) {
        log.info("KechangApplication start");
        SpringApplication.run(KechangApplication.class, args);
        log.info("KechangApplication start end");
    }

}
