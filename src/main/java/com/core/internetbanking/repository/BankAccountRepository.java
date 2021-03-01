package com.core.internetbanking.repository;

import com.core.internetbanking.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,Integer > {
}
