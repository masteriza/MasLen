package com.maslen.config;

import com.maslen.services.SpringDataUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        http
                .csrf()
                .disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.NEVER)
//                .enableSessionUrlRewriting(false)
//                .and()
                .authorizeRequests()
                .antMatchers("/static/**",
                        "/js/**",
                        "/css/**",
                        "/fonts/**",
                        "/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successForwardUrl("/loggedIn")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/")
                .permitAll();

        http.httpBasic();


//        CharacterEncodingFilter filter = new CharacterEncodingFilter();
//        filter.setEncoding("UTF-8");
//        filter.setForceEncoding(true);
//        http.addFilterBefore(filter, CsrfFilter.class);
//
//        http
//                .authorizeRequests()
//                .antMatchers(
//                        HttpMethod.GET,
//                        "/",
//                        "/*.html",
//                        "/userPanel.html",
//                        "/favicon.ico",
//                        "/**/*.map",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js"
//                ).permitAll()
//                .anyRequest().authenticated();
//
//        http
//                .formLogin()
//                .loginPage("/login")
////                .loginProcessingUrl("/auth")
//                .successForwardUrl("/loggedIn")
////                .successForwardUrl("/userPanel")
////                .usernameParameter("username")
////                .passwordParameter("password")
//                .failureHandler((request, response, e) -> {
//                    if (e instanceof BadCredentialsException) {
//                        response.sendRedirect("/login?error");
//                    } else {
//                        response.sendRedirect("/error");
//                    }
//                })
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/")
//
//                .and()
//                .rememberMe()
//                .tokenValiditySeconds(VALIDITY_SECONDS)
//
//                .and()
//                .exceptionHandling().accessDeniedPage("/403")
//
//                .and()
//                .csrf()
//                .disable()//отключить все
//        //.ignoringAntMatchers("путь где отключено")
//        ;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
        auth.userDetailsService(springDataUserDetailsService()).passwordEncoder(bCryptPasswordEncoder());
    }
}
