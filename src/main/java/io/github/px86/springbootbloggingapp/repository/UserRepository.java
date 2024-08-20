package io.github.px86.springbootbloggingapp.repository;

import io.github.px86.springbootbloggingapp.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findAll();

  Optional<User> findById(Long id);

  Optional<User> findByUsername(String username);

  Optional<User> findByEmail(String email);

  List<User> findByLastName(String lastName);

  List<User> findByFirstName(String firstName);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);

  void delete(User user);

  void deleteById(Long id);

  void deleteByUsername(String username);

  void deleteByEmail(String email);

  User save(User user);
}
