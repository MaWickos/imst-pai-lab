package pl.maciejwicha.pai_jwt.repository;

import org.springframework.data.repository.CrudRepository;
import pl.maciejwicha.pai_jwt.model.UserDao;

public interface UserRepository  extends CrudRepository<UserDao, Integer> {
    UserDao findByUsername(String username);

}
