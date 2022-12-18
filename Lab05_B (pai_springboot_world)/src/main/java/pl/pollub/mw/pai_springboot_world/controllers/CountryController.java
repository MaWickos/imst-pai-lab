package pl.pollub.mw.pai_springboot_world.controllers;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.pollub.mw.pai_springboot_world.entities.Country;
import pl.pollub.mw.pai_springboot_world.repositories.CountryRepository;

@Controller
public class CountryController {
    
    private final CountryRepository repository;
    
    @Autowired
    public CountryController(CountryRepository repository){
        this.repository = repository;
    }
    
    @RequestMapping("/country/continent/{continent}")
    @ResponseBody
    public String countriesByContinent(@PathVariable String continent){
        ArrayList<Country> countries = repository.findAllByContinent(continent);
        return buildResponse(countries);
    }
    
    @RequestMapping("/country/population/{min}/{max}")
    @ResponseBody
    public String countriesByPopulation(@PathVariable double min, @PathVariable double max){
        ArrayList<Country> countries = repository.findAllByPopulationBetween(min, max);
        return buildResponse(countries);
    }
    
    @RequestMapping("/country/continent/{continent}/surface/{min}/{max}")
    @ResponseBody
    public String countriesByContinentAndPopulation(
            @PathVariable String continent,
            @PathVariable Double min,
            @PathVariable Double max) {

        ArrayList<Country> countries = repository.findAllByContinentAndSurfaceAreaBetween(continent, min, max);
        return buildResponse(countries);
    }
    
    private String buildResponse(ArrayList<Country> countries){
        StringBuilder stringBuilder = new StringBuilder();
        for (Country country : countries) {
            stringBuilder
                    .append(country.toString())
                    .append("</br>");
        }
        
        return stringBuilder.toString();
    }
   
}
