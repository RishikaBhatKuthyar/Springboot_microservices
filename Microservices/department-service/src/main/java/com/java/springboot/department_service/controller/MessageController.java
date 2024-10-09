package com.java.springboot.department_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RefreshScope
@RestController
public class MessageController {
    @Value("${spring.boot.message}")
    private String message;
@GetMapping("message/show")
    public String getMessage(){
        return message;
    }
}
