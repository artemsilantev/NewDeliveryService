package com.artemsilantev.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableAsync
@ComponentScan(basePackages = {"com.artemsilantev.web"})
public class WebAutoConfiguration {

}
