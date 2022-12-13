package com.payconiq.stockmanagement.controller;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api( tags = "Clients")
@RestController
@RequestMapping("/wellcome")
public class WellcomeController {


    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() throws InterruptedException {
        return ResponseEntity.ok("Hi..") ;
    }

}
