package com.maslen.config;

import com.maslen.security.JwtAuthenticationEntryPoint;
import com.maslen.security.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }

//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                // we don't need CSRF because our token is invulnerable
//                .csrf().disable()
//
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//
//                // don't create session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//
//        httpSecurity.
//                authorizeRequests()
//                // allow anonymous resource requests
//                .antMatchers("/",
//                        "/login",
//                        "/auth",
//                        "/admin/driverMap/*.html",
//                        "/driverMap/*.html",
//                        "/**/driverMap.html",
//                        "/registration",
//                        "/favicon.ico",
//                        "/**/*.css",
//                        "/**/*.js").permitAll()
//                .antMatchers("/login").permitAll()
////                .antMatchers(HttpMethod.POST, "/user/update").permitAll()
////                .antMatchers(HttpMethod.POST, "/user/reset_password").permitAll()
////                .antMatchers("/user/forgot").permitAll()
////                .antMatchers("/user/registration").permitAll()
////                .antMatchers("/user/confirmation").permitAll()
////                .antMatchers(HttpMethod.GET, "/user/confirmation_email_response").permitAll()
////                .antMatchers("/user/test").permitAll()
//                .antMatchers("/admian/**").hasAuthority("ADMIN").anyRequest()
//                .authenticated().and()
//
//                // we don't need CSRF because our token is invulnerable
////                .csrf().disable()
//
////                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//
//                // don't create session
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//
//                .formLogin()
//                .loginPage("/").failureUrl("/login?error=true")
//                .loginProcessingUrl("/driverMap")
//                .defaultSuccessUrl("/driverMap")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .and().logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/").and().exceptionHandling()
//                .accessDeniedPage("/access-denied");
//
//
//        // Custom JWT based security filter
//        httpSecurity
//                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
//
//        // disable page caching
//        httpSecurity.headers().cacheControl();
//    }
//}


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()

                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//                .authorizeRequests()
        //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()


        httpSecurity
                .authorizeRequests()
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // allow anonymous resource requests
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/login",
//                        "/*.html",
//                        "/driverMap.html",
                        "/favicon.ico",
//                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()

                .antMatchers("/auth/**").permitAll()
                //todo: тут надо разобраться как видеть страницу но не пускать на нее тех что не подходит по правам
                .antMatchers("/userPanel.html").hasAuthority("ADMIN")
                .anyRequest().authenticated();

        httpSecurity
                .formLogin()
                .loginPage("/").failureUrl("/login?error=true")
                .loginProcessingUrl("/userPanel")
                .defaultSuccessUrl("/userPanel")
                .usernameParameter("email")
                .passwordParameter("password").and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and()
                .exceptionHandling().accessDeniedPage("/accessDenied");

        // Custom JWT based security filter
        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        httpSecurity.headers().cacheControl();
    }
}



