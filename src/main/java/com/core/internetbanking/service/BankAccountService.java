package com.core.internetbanking.service;

import com.core.internetbanking.dto.PaymentDto;
import com.core.internetbanking.model.BankAccount;

public interface BankAccountService {
    /**
     * method to create bank account
     * return bank account
     */
    BankAccount createAccount(Integer accountId, Integer balance);
    /**
     * method to get balance of account
     * return amount that is on account
     */
    Integer getBalanceOfAccount(Integer id);
    /**
     * method to send amount
     */
    void sendAmount(PaymentDto paymentDto) throws BackendException;
    /**
     * method to block bank account
     */
    void blockAccount(Integer accountId);
    /**
     * method to unblock bank account
     */
    void unblockAccount(Integer accountId);
    /**
     * method to set status of account to credit status
     */
    void setCreditCardStatus(Integer accountId);
    /**
     * method to set status of account to debit status
     */
    void setDebitCardStatus(Integer accountId);
}