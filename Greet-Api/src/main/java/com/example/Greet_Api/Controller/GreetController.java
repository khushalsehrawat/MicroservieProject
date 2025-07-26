package com.example.Greet_Api.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {


    @Autowired
    private Environment environment;

    private static final Logger logger= LoggerFactory.getLogger(GreetController.class);
    @GetMapping("/greet")
    public String message()
    {
        logger.info("Inside Greet Message Method...");
        String port=environment.getProperty("server.port");
        System.out.println("Running on PORT "+port);

        return "Welcome To Greet Controller!"+port;
    }

}
