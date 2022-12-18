package pl.maciejwicha.pai_jwt.model;

/*
    Klasa modelu DTO UserDto odpowiada za pobranie wartości od użytkownika i przekazanie ich do wartsy DAO
    w celu wstawienia do bazy danych
 */
public class UserDto {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
