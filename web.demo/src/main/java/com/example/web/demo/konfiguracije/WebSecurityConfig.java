package com.example.web.demo.konfiguracije;

import com.example.web.demo.dao.TecajDao;
import com.example.web.demo.manager.TecajManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/register","/confirmEmail","/activateEmail","/pdf","/vendor","/css","/js/**","/registration","/activateEmail","/messages","/login","/loginLog").permitAll() // login, register
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").defaultSuccessUrl("/pdf", true)
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/pdf").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .rememberMe().key("uniqueAndSecret");

        http.csrf().disable();///ovo jer nisam mogao druge reqeuset metode osim get

    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        System.out.println("sad sam u userdtails metoda u webconfigu");
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        TecajManagerImpl tec = new TecajManagerImpl();
        ArrayList<com.example.web.demo.modeli.User> us = new ArrayList<>();
        try {
            us = tec.getUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Boolean l = null;
        for (com.example.web.demo.modeli.User u: us) {
            switch(u.getLocked()) {
                case 0:
                    l = false;
                    break;
                case 1:
                    l = true;
                    break;
                default:
                    // code block
            }

            manager.createUser(users.username(u.getUserName()).password(u.getPassword()).roles(u.getRole()).accountLocked(l).build());}


        return manager;


    }

}
