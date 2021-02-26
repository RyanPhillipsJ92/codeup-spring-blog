package com.spring.springblog;

import com.spring.springblog.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsLoader userDetailsLoader;
    public SecurityConfiguration(UserDetailsLoader userDetailsLoader){
        this.userDetailsLoader = userDetailsLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsLoader).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http)throws Exception{
//        DEFINE HOW TO LOG IN
        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/posts")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")

//        DEFINE PAGES WHERE YOU DONT HAVE TO BE LOGGED IN
                .and()
                .authorizeRequests()
                .antMatchers("/","sign-up", "/posts")
                .permitAll()

//                DEFINE PAGES THAT REQUIRES USER TO BE LOGGED IN
                .and()
                .authorizeRequests()
                .antMatchers("/posts/*", "/posts/edit")
                .authenticated();
    }

//



}
