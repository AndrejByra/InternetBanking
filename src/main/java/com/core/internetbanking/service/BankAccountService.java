package com.core.internetbanking.service;

import com.core.internetbanking.dto.PaymentDto;
import com.core.internetbanking.model.BankAccount;

public interface BankAccountService {
    BankAccount createAccount(Integer accountId, Integer balance);

    Integer getBalanceOfAccount(Integer id);

    void sendAmount(PaymentDto paymentDto) throws BackendException;

    void blockAccount(Integer accountId);

    void unblockAccount(Integer accountId);

    void setCreditCardStatus(Integer accountId);

    void setDebitCardStatus(Integer accountId);
}