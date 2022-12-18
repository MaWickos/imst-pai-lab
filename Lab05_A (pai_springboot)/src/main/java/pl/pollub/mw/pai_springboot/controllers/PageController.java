package pl.pollub.mw.pai_springboot.controllers;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.pollub.mw.pai_springboot.entities.Zadanie;
import pl.pollub.mw.pai_springboot.repositories.ZadanieRepository;

@Controller
public class PageController {
    
    @Autowired
    public ZadanieRepository repository;
    
    @RequestMapping("/")
    @ResponseBody
    public String mainPage(){
        return "Hello Spring from mainPage() method!";
    }
    
    @RequestMapping("/hello")
    @ResponseBody
    public String pageTwo(){
        return "Hello Sprnig Boot from pageTwo() method!";
    }
    
    @RequestMapping("/listaZadan")
    @ResponseBody
    public String listaZadan(){
        StringBuilder odp = new StringBuilder();
        //Zadanie zadanie = new Zadanie();

        // Zadanie 5.4.
        Zadanie zadanie;
        double koszt = 1000;
        boolean wykonane = false; 
        
        for(int i=1; i<=2;i++){
            zadanie = new Zadanie();
            zadanie.setNazwa("Zadanie " + i);
            zadanie.setOpis("Opis czynności do wykonania w zadaniu " + i);
            zadanie.setKoszt(koszt);
            zadanie.setWykonane(wykonane);
            
            wykonane = !wykonane;
            koszt += 200.50;
            
            repository.save(zadanie);
        }
        
        // Korzystajac z obiektu repozytorium zapisujemy zadanie do bazy
        //repository.save(zadanie);
        
        // Korzystajac z rezpoytorium pobieramy wszystkie zadania z bazy
        for(Zadanie i:repository.findAll()){
            odp.append(i).append("<br/>");
        }
        
        return odp.toString();
    }
    
    // Zadanie 5.4 B - metoda do usuwania zadań
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String deleteZadanie(@PathVariable Long id){
        
        Optional<Zadanie> zadanieOpt = repository.findById(id);
        if(!zadanieOpt.isEmpty()){
            Zadanie zadanie = zadanieOpt.get();
            repository.delete(zadanie);
        
            StringBuilder odp = new StringBuilder();
            for(Zadanie i:repository.findAll()){
                odp.append(i).append("<br/>");
            }
        
            return "<h1> Zadanie o ID = " + Long.toString(id) + " usuniete! </h1> </br>" + odp.toString();
        }
        
        return "Brak zadania o podanym ID";
    }
    
    // Zadanie 5.4 C
    @RequestMapping("/wykonane/{wykonane}")
    @ResponseBody
    public String wykonanie(@PathVariable boolean wykonane){
        ArrayList<Zadanie> arrayZadania = repository.findByWykonane(wykonane);
        
        StringBuilder odp = new StringBuilder();
        if(wykonane)
            odp.append("Zadania wykonane: </br>");
        else
            odp.append("Zadania niewykonane: </br>");
        
        for(Zadanie zadanie:arrayZadania){
            odp.append(zadanie).append("</br>");
        }
        
        return odp.toString();
    }
    
    @RequestMapping("/koszt/{max}")
    @ResponseBody
    public String kosztLess(@PathVariable double max){
        ArrayList<Zadanie> arrayZadania = repository.findByKosztLessThan(max);
        
        StringBuilder odp = new StringBuilder();
        odp.append("Zadania z kosztem wykonania < ").append(Double.toString(max)).append("</br>");
        for(Zadanie zadanie:arrayZadania){
            odp.append(zadanie).append("</br>");
        }
        return odp.toString();
    }
    
    @RequestMapping("/koszt/{min}/{max}")
    @ResponseBody
    public String kosztBeetwen(@PathVariable double min, @PathVariable double max){
        ArrayList<Zadanie> arrayZadania = repository.findByKosztBetween(min, max);
        
        StringBuilder odp = new StringBuilder();
        odp.append("Zadania z kosztem wykonania z przedziału < ")
                .append(Double.toString(min))
                .append(",")
                .append(Double.toString(max))
                .append("> </br>");
        for(Zadanie zadanie:arrayZadania){
            odp.append(zadanie).append("</br>");
        }
        return odp.toString();
    }
    
}
