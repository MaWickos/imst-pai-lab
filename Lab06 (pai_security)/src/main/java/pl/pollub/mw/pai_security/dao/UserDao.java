package pl.pollub.mw.pai_security.dao;

import org.springframework.stereotype.Repository;
import pl.pollub.mw.pai_security.entities.User;
import org.springframework.data.repository.CrudRepository;
public interface UserDao extends CrudRepository<User, Integer> {

    public User findByLogin(String login);
}
