package io.github.px86.springbootbloggingapp.config;

import io.github.px86.springbootbloggingapp.service.IUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
            (requests) ->
                requests
                    .requestMatchers("/", "/home", "/signup")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .formLogin((form) -> form.loginPage("/login").permitAll())
        .logout((logout) -> logout.permitAll());

    return http.build();
  }

  @Bean
  public UserDetailsService getUserDetailService() {
    return new IUserDetailsService();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
