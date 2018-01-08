package com.maslen.services;

import com.maslen.models.BasicAuthenticateUserFactory;
import com.maslen.models.User;
import com.maslen.services.interfaces.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SpringDataUserDetailsService implements UserDetailsService {
    private LogInService logInService;

    @Autowired
    public void setLogInService(LogInService logInService) {
        this.logInService = logInService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = logInService.login(email);
        //.orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", email));
        } else {
            return BasicAuthenticateUserFactory.create(user);
        }
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
//
//
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//                authorities);
    }


}
