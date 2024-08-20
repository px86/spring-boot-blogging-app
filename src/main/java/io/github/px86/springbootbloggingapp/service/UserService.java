package io.github.px86.springbootbloggingapp.service;

import io.github.px86.springbootbloggingapp.model.User;
import io.github.px86.springbootbloggingapp.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired private UserRepository userRepository;

  public Optional<User> findById(Long id) {
    return this.userRepository.findById(id);
  }

  public Optional<User> findByUsername(String username) {
    return this.userRepository.findByUsername(username);
  }

  public Optional<User> findByEmail(String email) {
    return this.userRepository.findByEmail(email);
  }

  public List<User> findByFirstName(String firstName) {
    return this.userRepository.findByFirstName(firstName);
  }

  public List<User> findByLastName(String lastName) {
    return this.userRepository.findByLastName(lastName);
  }
}
