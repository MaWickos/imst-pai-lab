package pl.pollub.mw.pai_security.controllers;

import java.security.Principal;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.pollub.mw.pai_security.dao.UserDao;
import pl.pollub.mw.pai_security.entities.User;
import javax.validation.Valid;

@Controller
public class UserController {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserDao userDao;
    
    @GetMapping("/login")
    public String loginPage(){
        // Zwrócenie nazwy widoku logowania - login.html
        return "login";
    }
    
    @GetMapping("/register")
    public String registerPage(Model model){
        // Dodanie do modelu nowego użytkownika
        model.addAttribute("user", new User());
        
        // Zwrócenie nazwy widoku rejestracji - register.html
        return "register";
    }
    
    @PostMapping("/register")
    public String registerPagePOST(@ModelAttribute @Valid User user, BindingResult bindingResult){
        if(userDao.findByLogin(user.getLogin()) != null){
            ObjectError isUserExistingError = new ObjectError("isUserExisting", "Użytkownik o podanym loginie istnie już w bazie. Zaloguj się!");
            bindingResult.rejectValue("login", isUserExistingError.getCode(), isUserExistingError.getDefaultMessage());
            return "register";
        }

        if(bindingResult.hasErrors())
            return "register";

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        
        // Przekierowanie do adresu url: /login
        return "redirect:/login";
    }
    
    @GetMapping("/profile")
    public String profilePage(Model model, Principal principal){
        // Dodanie do modelu aktualnie zalogowanego użytkownika;
        model.addAttribute("user", userDao.findByLogin(principal.getName()));
        
        // Zwrócenie nazwy widoku profilu użytkownika - profile.html
        return "profile";
    }
    
    @GetMapping("/users")
    public String getAllUsers(Model model){
        model.addAttribute("allUsers", userDao.findAll());
        return "users";
    }

    // Edycja danych aktualnie zalogowanego użytkownka
    @GetMapping("/edit")
    public String editCurrentUserData(Model model, Principal principal){
        model.addAttribute("user", userDao.findByLogin(principal.getName()));
        return "edit";
    }

    @PostMapping("/edit")
    public String editCurrentUser(@ModelAttribute @Valid User user, BindingResult bindingResult, Principal principal) {

        // Weryfikacja, czy nie próbuję zmienić loginu na zajęty
        if(userDao.findByLogin(user.getLogin()) != null){
            Integer newLoginUserId = userDao.findByLogin(user.getLogin()).getUserid();
            Integer currentUserId = userDao.findByLogin(principal.getName()).getUserid();
                if(!newLoginUserId.equals(currentUserId)) {
                    ObjectError loginIsTaken = new ObjectError("login", "Login zajęty");
                    bindingResult.rejectValue("login", loginIsTaken.getCode(), loginIsTaken.getDefaultMessage());
                    return "edit";
                }
        }

        // Błędy
        if(bindingResult.hasErrors()) {
            return "edit";
        }

        User currentUser = userDao.findByLogin(principal.getName());
        boolean isRequiredLogout = !currentUser.getLogin().equals(user.getLogin());
        currentUser.setName(user.getName());
        currentUser.setSurname(user.getSurname());
        currentUser.setLogin(user.getLogin());

        if (!Objects.equals(user.getPassword(), ""))
            currentUser.setPassword(passwordEncoder.encode(user.getPassword()));

        userDao.save(currentUser);

        if(isRequiredLogout)
            return "redirect:/logout";

        return "redirect:/users";
    }

    // Usuwanie użytkownika
    @GetMapping("/delete")
    public String deleteCurrentUser(Principal principal){
        userDao.delete(userDao.findByLogin(principal.getName()));
        return "redirect:/logout";
    }
}
