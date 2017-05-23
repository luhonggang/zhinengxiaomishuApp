package com.shanlin.intelligent;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 类注释
 *
 * @author: hz
 * @date: 2017/4/22
 * @time: 21:32
 * @see: 链接到其他资源
 * @since: 1.0
 */
@Configuration
@Controller
@EnableWebMvc
@EnableCaching
@ServletComponentScan
@EnableFeignClients
@ComponentScan("com.shanlin")
@MapperScan("com.shanlin.intelligent.mapper")
@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(WebApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(WebApplication.class);
  }
}
