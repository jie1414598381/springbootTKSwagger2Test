package com.springboot.btest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Predicate;

@RestController
@RequestMapping("/btest")
public class BtestController {
    Predicate<Long> integerPredicate = (amount) -> amount!=null;
    @RequestMapping("/addMath")
    public String addMath(Long amount1,Long amount2){
        return String.valueOf((integerPredicate.test(amount1)?amount1:0) + (integerPredicate.test(amount2)?amount2:0));
    }

}
