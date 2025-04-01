package com.mystore.app.security;


import com.mystore.app.config.JWTAuthenticationFilter;
import com.mystore.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class StoreSecurityConfig {


    @Autowired
    JWTAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{

            httpSecurity
                    .csrf(csrf->csrf.disable())
                        .authorizeHttpRequests(auth->auth
                                                                            .requestMatchers("/users","/auth/login")
                                //users is used to regiter the user and auth/login is used to login the user
                                                                            .permitAll()
//                                                                            .requestMatchers(HttpMethod.POST,"/users","/auth/login")
//                                                                            .permitAll()
                                                                            .anyRequest()
                                                                            .authenticated())
                                                                            .sessionManagement(session-> session
                                                                                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                                                                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).httpBasic();

return httpSecurity.build();


    }


    @Autowired
    UserService userService;


    @Autowired
    PasswordEncoder passwordEncoder;


    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }

}
