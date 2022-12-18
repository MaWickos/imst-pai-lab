package pl.pollub.mw.pai_springboot_world;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@ComponentScan({"pl.pollub.mw.pai_springboot_world.controllers"})
@EnableJpaRepositories({"pl.pollub.mw.pai_springboot_world.repositories"})
public class Main {
    
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
