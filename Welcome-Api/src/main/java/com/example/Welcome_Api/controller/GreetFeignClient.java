package com.example.Welcome_Api.controller;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "GREET-API")
public interface GreetFeignClient {


    @GetMapping("/greet")
    public String invokeGreetApi();

}
