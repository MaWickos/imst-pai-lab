package pl.maciejwicha.pai_jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.maciejwicha.pai_jwt.config.JwtTokenUtil;
import pl.maciejwicha.pai_jwt.model.JwtRequest;
import pl.maciejwicha.pai_jwt.model.JwtResponse;
import pl.maciejwicha.pai_jwt.model.UserDto;
import pl.maciejwicha.pai_jwt.repository.UserRepository;
import pl.maciejwicha.pai_jwt.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userDao;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception{
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception{
        if(userDao.findByUsername(user.getUsername()) != null){
            return ResponseEntity.ok("Username" + user.getUsername() + "is already taken!");
        } else {
            return ResponseEntity.ok(userDetailsService.save(user));
        }
    }

    private void authenticate(String username, String password) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e){
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIAL", e);
        }
    }

}
