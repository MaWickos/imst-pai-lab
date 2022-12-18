package pl.maciejwicha.pai_jwt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TestController {

    @RequestMapping({"/hello"})
    public String welcomePage(){
        return "Welcome!";
    }
}
