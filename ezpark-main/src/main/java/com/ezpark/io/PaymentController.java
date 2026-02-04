package com.ezpark.io;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @GetMapping("{id}")
    public Reciept  payment(@PathVariable("id") String id){
        // return "Success" ;
        return new Reciept("Payment successful", BigDecimal.valueOf(25));
    }
}
