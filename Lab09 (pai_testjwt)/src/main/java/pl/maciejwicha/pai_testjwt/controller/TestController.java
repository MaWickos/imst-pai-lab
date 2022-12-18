package pl.maciejwicha.pai_testjwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping({"/hello"})
    public String welcomePage(){
        return "Welcome!";
    }
}
