package com.shanlin.intelligent.conf;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * 类注释
 *
 * @author: hz
 * @date: 2017/4/23
 * @time: 10:53
 * @see: 链接到其他资源
 * @since: 1.0
 */
@Configuration
public class DefaultConfiguration {

  @Bean
  public UrlBasedViewResolver setupViewResolver() {
    UrlBasedViewResolver resolver = new UrlBasedViewResolver();
    resolver.setPrefix("/WEB-INF/jsp/");
    resolver.setSuffix(".jsp");
    resolver.setCache(true);
    resolver.setViewClass(JstlView.class);
    return resolver;
  }
}