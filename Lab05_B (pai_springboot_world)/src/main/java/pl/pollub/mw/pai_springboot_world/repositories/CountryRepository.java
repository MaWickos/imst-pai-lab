package pl.pollub.mw.pai_springboot_world.repositories;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollub.mw.pai_springboot_world.entities.Country;

public interface CountryRepository extends JpaRepository<Country, String>{
    
    ArrayList<Country> findAllByContinent(String continent);
    ArrayList<Country> findAllByPopulationBetween(Double min, Double max);
    ArrayList<Country> findAllByContinentAndSurfaceAreaBetween(String continent, Double min, Double max);
}
