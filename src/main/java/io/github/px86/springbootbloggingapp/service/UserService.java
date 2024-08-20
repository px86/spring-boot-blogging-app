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

  public List<User> findAll() {
    return this.userRepository.findAll();
  }

  public Optional<User> findById(Long id) {
    return this.userRepository.findById(id);
  }

  public Optional<User> findByUsername(String username) {
    return this.userRepository.findByUsername(username);
  }

  public Optional<User> findByEmail(String email) {
    return this.userRepository.findByEmail(email);
  }

  public List<User> findByLastName(String lastName) {
    return this.userRepository.findByLastName(lastName);
  }

  public List<User> findByFirstName(String firstName) {
    return this.userRepository.findByFirstName(firstName);
  }

  public boolean existsByUsername(String username) {
    return this.userRepository.existsByUsername(username);
  }

  public boolean existsByEmail(String email) {
    return this.userRepository.existsByEmail(email);
  }

  public void delete(User user) {
    this.userRepository.delete(user);
  }

  public void deleteById(Long id) {
    this.userRepository.deleteById(id);
  }

  public void deleteByUsername(String username) {
    this.userRepository.deleteByUsername(username);
  }

  public void deleteByEmail(String email) {
    this.userRepository.deleteByEmail(email);
  }

  public User save(User user) {
    return this.userRepository.save(user);
  }
}
