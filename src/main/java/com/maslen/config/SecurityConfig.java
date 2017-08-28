package com.maslen.config;

import com.maslen.services.SpringDataUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final int VALIDITY_SECONDS = 604800;


    @Bean
    public SpringDataUserDetailsService springDataUserDetailsService() {
        return new SpringDataUserDetailsService();
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

        http.authorizeRequests()
                .anyRequest().permitAll()

                .and()
                .formLogin()
                .loginPage("/logIn")
                .successForwardUrl("/driverMap")
                .failureHandler((request, response, e) -> {
                    if (e instanceof BadCredentialsException) {
                        response.sendRedirect("/logIn?error");
                    } else {
                        response.sendRedirect("/error");
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logOut")
                .logoutSuccessUrl("/")

                .and()
                .rememberMe()
                .tokenValiditySeconds(VALIDITY_SECONDS)

                .and()
                .exceptionHandling().accessDeniedPage("/403")

                .and()
                .csrf()
                .disable()//отключить все
        //.ignoringAntMatchers("путь где отключено")
        ;


//                .antMatchers("/").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().permitAll()
//                .and()
//                .logout().permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
        auth.userDetailsService(springDataUserDetailsService()).passwordEncoder(bCryptPasswordEncoder());
    }
}
