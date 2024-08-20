package io.github.px86.springbootbloggingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class IUserDetailsService implements UserDetailsService {

  @Autowired private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return this.userService
        .findByUsername(username)
        .orElseThrow(
            () -> new UsernameNotFoundException("can not find user with username=" + username));
  }
}
