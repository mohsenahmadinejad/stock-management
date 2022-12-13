package com.payconiq.stockmanagement;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Api( tags = "Clients")
@RestController
@RequestMapping("/wellcome")
public class WellcomeController {


    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() throws InterruptedException {
        return ResponseEntity.ok("Hi..") ;
    }

}
