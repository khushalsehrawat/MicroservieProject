package com.example.Welcome_Api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WelcomeController {


    @Autowired
    private RestTemplate restTemplate;

    /*@GetMapping("/welcome")
    public String welcome()
    {
        String welMess = "  Welocme To APIsss!!1!1!";
        String greet = restTemplate.getForObject("http://localhost:9090/greet", String.class);
        return greet + welMess;

    }
*/

    @Autowired
    private GreetFeignClient greetFeignClient;

    @GetMapping("/welcome")
    public String welcome()
    {
        String welMess = "  Welocme To APIsss!!1!1!";
        String greet = greetFeignClient.invokeGreetApi();
        return greet + welMess;

    }

}
