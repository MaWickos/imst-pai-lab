package pl.pollub.mw.pai_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"pl.pollub.mw.pai_springboot.controllers"})
@EnableAutoConfiguration
@EnableJpaRepositories({"pl.pollub.mw.pai_springboot.repositories"})
public class Main {
    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
    }

    // Zadanie 5.1
    /*
    @RequestMapping("/")
    @ResponseBody
    public String mainPage(){
        return "Hello Spring Boot!";
    }
    */
}
