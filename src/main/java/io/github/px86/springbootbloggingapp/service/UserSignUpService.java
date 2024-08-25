package io.github.px86.springbootbloggingapp.service;

import io.github.px86.springbootbloggingapp.model.Role;
import io.github.px86.springbootbloggingapp.model.User;
import io.github.px86.springbootbloggingapp.model.UserCreationDTO;
import io.github.px86.springbootbloggingapp.model.UserStatus;
import io.github.px86.springbootbloggingapp.service.exception.EmailAlreadyRegisteredException;
import io.github.px86.springbootbloggingapp.service.exception.UsernameNotAvailableException;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSignUpService {

  @Autowired private UserService userService;
  @Autowired private PasswordEncoder passwordEncoder;
  @Autowired private EmailService emailService;

  public void registerNewUser(UserCreationDTO userCreationDTO)
      throws UsernameNotAvailableException, EmailAlreadyRegisteredException {

    if (this.userService.existsByUsername(userCreationDTO.getUsername())) {

      throw new UsernameNotAvailableException("Username is already taken!");

    } else if (this.userService.existsByEmail(userCreationDTO.getEmail())) {

      throw new EmailAlreadyRegisteredException("Email is already registered!");
    }

    User user = new User();
    user.setUsername(userCreationDTO.getUsername());
    user.setPassword(this.passwordEncoder.encode(userCreationDTO.getPassword()));
    user.setEmail(userCreationDTO.getEmail());
    user.setFirstName(userCreationDTO.getFirstName());
    user.setLastName(userCreationDTO.getLastName());
    user.setRole(Role.STANDARD);
    user.setStatus(UserStatus.UNVERIFIED);
    user.setCreatedAt(OffsetDateTime.now());

    this.userService.save(user);

    String emailBody = "TO BE IMPLEMENTED!";

    this.emailService.sendEmailAsync(user.getEmail(), "Email verification", emailBody);
  }
}
