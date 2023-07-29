package com.demo.finbank.repositories;

import com.demo.finbank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail (String email);

    User findByAccountNumber(String accountNumber);

    boolean existsByAccountNumber(String accountNumber);
   // Optional<User> findByAccountBalance(String accountBalance);
}
