package com.demo.finbank.repositories;

import com.demo.finbank.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    boolean existsByEmail (String email);

    Users findByAccountNumber(String accountNumber);

    boolean existsByAccountNumber(String accountNumber);
   // Optional<User> findByAccountBalance(String accountBalance);
}
