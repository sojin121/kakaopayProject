package com.kakaopay.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.kakaopay.project.mapper")
public class KakakpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(KakakpayApplication.class, args);
	}
	
}
