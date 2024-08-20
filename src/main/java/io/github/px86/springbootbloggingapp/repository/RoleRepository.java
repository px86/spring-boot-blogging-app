package io.github.px86.springbootbloggingapp.repository;

import io.github.px86.springbootbloggingapp.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByName(String name);

  Optional<Role> findById(Long id);
}
