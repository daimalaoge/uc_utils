package com.ucomponent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * 2019/5/14
 * Author:代码老哥
 * NAME:
 * TODO:
 **/
@SpringBootApplication
@EnableCaching
@ServletComponentScan
public class UCUtilsApplication {
	public static void main(String[] args) {
		SpringApplication.run(UCUtilsApplication.class, args);
	}
}
