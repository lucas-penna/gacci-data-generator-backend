package com.gacci.gacci_data_generator.repository;

import com.gacci.gacci_data_generator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
