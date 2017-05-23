package com.shanlin.intelligent.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 类注释
 *
 * @author: hz
 * @date: 2017/4/23
 * @time: 11:31
 * @see: 链接到其他资源
 * @since: 1.0
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

  /**
   * spring-boot配置外部静态资源的方法
   *
   * @param registry
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
  }
}
