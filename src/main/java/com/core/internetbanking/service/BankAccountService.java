package com.core.internetbanking.service;

import com.core.internetbanking.dto.PaymentDto;
import com.core.internetbanking.model.BankAccount;

public interface BankAccountService {

    BankAccount createAccount(Integer accountId, Integer balance);

    Integer getBalanceOfAccount(Integer id);

    void sendAmount(PaymentDto paymentDto);

    void blockAccount(BankAccount bankAccount);

    void unblockAccount(BankAccount bankAccount);

    void setCreditCardStatus(BankAccount bankAccount);

    void setDebitCardStatus(BankAccount bankAccount);

}
