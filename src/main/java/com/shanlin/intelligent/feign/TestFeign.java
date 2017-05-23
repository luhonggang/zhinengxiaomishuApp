package com.shanlin.intelligent.feign;

import com.alibaba.fastjson.JSONObject;
import com.shanlin.intelligent.model.ResponseDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by dell on 2017/5/12.
 */

@FeignClient(url = "${test.ThridUserUrl}", name = "TestServerController")
public interface TestFeign {

    @Transactional(propagation = Propagation.NEVER)
    @RequestMapping(value = "/user/getUser11", method = RequestMethod.GET)
    //BaseResponse getUserResult();
     ResponseDTO selectAll();

    @Transactional(propagation = Propagation.NEVER)//ok
    @RequestMapping(value="/user/getUserByIdForTest",method = RequestMethod.POST)
    JSONObject selectUserById(@RequestParam("id") String id);



}
