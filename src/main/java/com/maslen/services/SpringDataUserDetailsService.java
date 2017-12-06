//package com.maslen.services;
//
//import com.maslen.models.User;
//import com.maslen.services.interfaces.LogInService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class SpringDataUserDetailsService implements UserDetailsService {
//    private LogInService logInService;
//
//    @Autowired
//    public void setLogInService(LogInService logInService) {
//        this.logInService = logInService;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = logInService.logIn(username)
//                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
//
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
//
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//                authorities);
//    }
//}
