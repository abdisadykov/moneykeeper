package kz.diploma.basqaru.domain.repository;

import kz.diploma.basqaru.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByEmail(String email);
    void deleteUserByEmail(String email);
    boolean existsByEmail(String email);
}
