package pl.pollub.mw.pai_springboot.repositories;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import pl.pollub.mw.pai_springboot.entities.Zadanie;

public interface ZadanieRepository  extends CrudRepository<Zadanie, Long>{

    @Override
    public Iterable<Zadanie> findAll();

    @Override
    public <S extends Zadanie> S save(S s);

    // Zadanie 5.4 b
    @Override
    public Optional<Zadanie> findById(Long id);

    @Override
    public void deleteById(Long id);
    
    // Zadanie 5.4 c
    public ArrayList<Zadanie> findByWykonane(boolean wykonane);
    
    public ArrayList<Zadanie> findByKosztLessThan(double koszt);
    
    public ArrayList<Zadanie> findByKosztBetween(double min, double max);
    

}
