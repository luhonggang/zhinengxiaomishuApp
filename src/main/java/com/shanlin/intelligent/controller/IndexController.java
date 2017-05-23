package com.shanlin.intelligent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 类注释
 *
 * @author: hz
 * @date: 2017/4/23
 * @time: 12:05
 * @see: 链接到其他资源
 * @since: 1.0
 */
@RestController
public class IndexController {
  @GetMapping(value = "/")
  public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("index");
    return mav;
  }
}
