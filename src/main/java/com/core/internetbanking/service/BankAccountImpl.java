package com.core.internetbanking.service;

import com.core.internetbanking.dto.PaymentDto;
import com.core.internetbanking.model.BankAccount;
import com.core.internetbanking.repository.BankAccountRepository;
import com.core.internetbanking.repository.UserRepository;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Component
public class BankAccountImpl implements BankAccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public BankAccount createAccount(Integer accountId, Integer balance) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(balance);
        bankAccount.setIban(ibanGenerator());
        bankAccount.setUser(userRepository.getOne(accountId));
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public Integer getBalanceOfAccount(Integer id) {
        return bankAccountRepository.getOne(id).getBalance();
    }

    private String ibanGenerator() {
        Iban ibanGenerator = new Iban.Builder()
                .countryCode(CountryCode.SK)
                .bankCode("0900")
                .buildRandom();
        return ibanGenerator.toString();
    }

    @Override
    public void sendAmount(PaymentDto paymentDto) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(paymentDto.getAccountId1());
        Optional<BankAccount> bankAccount2 = bankAccountRepository.findById(paymentDto.getAccountId2());

        if (bankAccount.isPresent() && bankAccount2.isPresent()) {
            BankAccount bankAccount1 = bankAccount.get();
            BankAccount bankAccount3 = bankAccount2.get();
            if (!bankAccount1.isActive()) {
                throw new BackendException("Bank Account " + bankAccount1.getId() + " is blocked.", HttpStatus.OK);
            } else if (!bankAccount3.isActive()) {
                throw new BackendException("Bank Account " + bankAccount3.getId() + " is blocked", HttpStatus.OK);
            } else if (!((bankAccount1.getBalance() >= paymentDto.getTransferAmount()) || !bankAccount1.isDebit())) {
                throw new BackendException("Not enough balance in account " + bankAccount1.getId(), HttpStatus.OK);
            } else {
                bankAccount1.setBalance(bankAccount1.getBalance() - paymentDto.getTransferAmount());
                bankAccount3.setBalance(bankAccount3.getBalance() + paymentDto.getTransferAmount());
                bankAccountRepository.save(bankAccount1);
                bankAccountRepository.save(bankAccount3);
            }
        } else {
            throw new BackendException("Invalid Account Id", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void blockAccount(@RequestParam Integer accountId) {
        BankAccount bankAccount1 = bankAccountRepository.getOne(accountId);
        bankAccount1.setActive(false);
        bankAccountRepository.save(bankAccount1);
    }

    @Override
    public void unblockAccount(@RequestParam Integer accountId) {
        BankAccount bankAccount1 = bankAccountRepository.getOne(accountId);
        bankAccount1.setActive(true);
        bankAccountRepository.save(bankAccount1);
    }

    @Override
    public void setCreditCardStatus(@RequestParam Integer accountId) {
        BankAccount bankAccount1 = bankAccountRepository.getOne(accountId);
        bankAccount1.setDebit(false);
        bankAccountRepository.save(bankAccount1);
    }

    @Override
    public void setDebitCardStatus(@RequestParam Integer accountId) {
        BankAccount bankAccount1 = bankAccountRepository.getOne(accountId);
        bankAccount1.setDebit(true);
        bankAccountRepository.save(bankAccount1);
    }
}