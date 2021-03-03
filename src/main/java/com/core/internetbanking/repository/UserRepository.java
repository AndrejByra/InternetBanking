package com.core.internetbanking.repository;

import com.core.internetbanking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
}