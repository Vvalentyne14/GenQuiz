package com.example.BackendApplication2.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/service")
public class ServiceController {

    @GetMapping("/service1")
    public String service() {
        return "service1";
    }

    @GetMapping("/service2")
    public String service2() {
        return "service2";
    }

    @GetMapping("/service3")
    public String service3() {
        return "service3";
    }

    @GetMapping("/service4")
    public String service4() {
        return "service4";
    }
}