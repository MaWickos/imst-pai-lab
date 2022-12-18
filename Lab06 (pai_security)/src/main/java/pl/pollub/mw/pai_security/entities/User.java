package pl.pollub.mw.pai_security.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="Users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userid;

    @Size(min = 1, message = "Imię nie może być puste!")
    private String name;

    @Size(min = 1, message = "Nazwisko nie może być puste!")
    private String surname;

    @Pattern(regexp = "^[a-z0-9_.]{4,32}$", message = "Dozwolone znaki a-z, 0-9 oraz _ i .")
    private String login;

    @Size(min = 1, max = 64, message = "Hasło nie może byc puste!")
    private String password;
    
    public User(){
        
    }
    
    public User(String name, String surname, String login, String password){
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
