package pl.pollub.mw.pai_spring.controllers;

import pl.pollub.mw.pai_spring.beans.Pracownik;
import pl.pollub.mw.pai_spring.dao.PracownikDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PracownikController {
    @Autowired
    PracownikDAO pracownikDao;          // Wstrzykniecie DAO z pliku XML;
    
    /*
        Wynikiem dzialanai metody jest przekazanie danych w modelu do strony widku addForm.jsp,
        ktora wyswietla formularz wprowadzania danych, a "command" jest zastrzezonym atrybutem zadania,
        umozliwiajacym wyswietlenie danych obiektu pracownika w formularzu.
    */
    @RequestMapping("/addForm")
    public String showForm(Model model){
        model.addAttribute("command", new Pracownik());
        return "addForm";               // Przekierowanie do addForm.jsp
    }
    
    /* 
        Metoda obsluguje zapis pracownika do BD.
        @ModelAttribute umozliwia pobranie danych z zadania do obiektu pracownika.
        Jawnie wskazano RequestMethod.POST (domyslan wartosc to GET)
    */
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("pracownik") Pracownik pracownik){
        pracownikDao.save(pracownik);
        return "redirect:/viewAll";     // Przekierowanie do endpointa o URL /viewAll
    }
    
    /* 
        Metoda pobiera liste pracownikow z BD i umieszcza ja w modelu 
    */
    @RequestMapping("/viewAll")
    public String viewAll(Model model){
        List<Pracownik> pracownikList = pracownikDao.getAll();
        model.addAttribute("listaPracownikow", pracownikList);
        return "viewAll";               // Przejście do widoku viewAll.jsp
    }
    
    /*
        Zadanie 4.4
        Metoda do usuwania pracownika o zadanym ID z BD
        ID przekazywane w postaci parametru w adresie URL
    */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        if(pracownikDao.getPracownikById(id) != null){
            pracownikDao.delete(id);
            return "redirect:/viewAll";
        } else {
            return "errorUserNotExisting";
        }
    }
    
    /*
        Zadane 4.5
        Metoda do edycji pracownika o wskazanym ID i przekierowania do formularza edycji,
        w formularzu odczyt danych i wstrzyknięcie ich do formularza
    */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable int id, Model model){
        Pracownik pracownik = pracownikDao.getPracownikById(id);
        if(pracownik != null){
            model.addAttribute("command", pracownik);
            return "editForm";
        } else {
            return "errorUserNotExisting";
        }
        
    }
    
    @RequestMapping(value="/edit/editsave", method = RequestMethod.POST)
    public String editsave(@ModelAttribute("command") Pracownik pracownik){
        pracownikDao.update(pracownik);
        return "redirect:/viewAll";
    }
    
    /*
        Zadanie 4.6
        Obsługa błędów i wyjątków
        Ustawienie danych o błędzie w modelu, ustawienie nazwy widoku do przekierowania
        Totalna kontrolna nad błędem lub wyjątkiem
    */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }
}
