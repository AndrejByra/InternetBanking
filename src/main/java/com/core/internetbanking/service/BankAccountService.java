package com.core.internetbanking.service;

import com.core.internetbanking.dto.PaymentDto;
import com.core.internetbanking.model.BankAccount;
import org.springframework.http.ResponseEntity;

public interface BankAccountService {

    BankAccount createAccount(Integer accountId, Integer balance);

    Integer getBalanceOfAccount(Integer id);

    ResponseEntity<String> sendAmount(PaymentDto paymentDto);

    void blockAccount(Integer accountId);

    void unblockAccount(Integer accountId);

    void setCreditCardStatus(Integer accountId);

    void setDebitCardStatus(Integer accountId);
}
