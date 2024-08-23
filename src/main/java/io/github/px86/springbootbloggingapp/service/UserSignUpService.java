package io.github.px86.springbootbloggingapp.service;

import io.github.px86.springbootbloggingapp.model.Role;
import io.github.px86.springbootbloggingapp.model.User;
import io.github.px86.springbootbloggingapp.model.UserStatus;
import io.github.px86.springbootbloggingapp.service.exception.EmailAlreadyRegisteredException;
import io.github.px86.springbootbloggingapp.service.exception.UsernameNotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSignUpService {

  @Autowired private UserService userService;
  @Autowired private PasswordEncoder passwordEncoder;
  @Autowired private EmailService emailService;

  public void registerNewUser(User user)
      throws UsernameNotAvailableException, EmailAlreadyRegisteredException {

    if (this.userService.existsByUsername(user.getUsername())) {

      throw new UsernameNotAvailableException("Username is already taken!");

    } else if (this.userService.existsByEmail(user.getEmail())) {

      throw new EmailAlreadyRegisteredException("Email is already registered!");
    }

    user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    user.setRole(Role.STANDARD);
    user.setStatus(UserStatus.UNVERIFIED);

    this.userService.save(user);

    String emailBody = "TO BE IMPLEMENTED!";

    this.emailService.sendEmailAsync(user.getEmail(), "Email verification", emailBody);
  }
}
