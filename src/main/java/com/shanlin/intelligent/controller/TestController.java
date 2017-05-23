package com.shanlin.intelligent.controller;

import com.alibaba.fastjson.JSONObject;
import com.shanlin.intelligent.feign.TestFeign;
import com.shanlin.intelligent.model.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 类注释
 *
 * @author: luhonggang
 * @date: 2017/5/23
 * @time: 18:17
 * @see:  调用第三方服务入口
 * @since: 1.0
 */
@RestController
@RequestMapping("/user")
public class TestController {
    @Autowired
    private TestFeign feign;

    @GetMapping("/getTest")
    public ResponseDTO getAllUser(){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setSumObject(feign.selectAll());
        return  responseDTO;
    }

    @PostMapping("getUserInfoMationById")// success
    public JSONObject selectUserInfoMation(@Param("id") String id){
        JSONObject obj = new JSONObject();
        obj.put("obj",feign.selectUserById(id));
        return  obj;
    }
}
