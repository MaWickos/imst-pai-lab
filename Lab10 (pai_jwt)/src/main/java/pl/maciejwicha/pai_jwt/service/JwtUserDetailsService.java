package pl.maciejwicha.pai_jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.maciejwicha.pai_jwt.model.UserDao;
import pl.maciejwicha.pai_jwt.model.UserDto;
import pl.maciejwicha.pai_jwt.repository.UserRepository;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserRepository userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDao user = userDao.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        } else {
            return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
        }
    }

    public UserDao save(UserDto user){
        UserDao newUser = new UserDao();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userDao.save(newUser);
    }
}
