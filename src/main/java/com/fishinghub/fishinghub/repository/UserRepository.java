package com.fishinghub.fishinghub.repository;
import com.fishinghub.fishinghub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<Object> findByUsername(String username);
}
