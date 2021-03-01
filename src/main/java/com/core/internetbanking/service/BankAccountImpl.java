package com.core.internetbanking.service;

import com.core.internetbanking.dto.PaymentDto;
import com.core.internetbanking.model.BankAccount;
import com.core.internetbanking.repository.BankAccountRepository;
import com.core.internetbanking.repository.UserRepository;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;

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
    public ResponseEntity<String> sendAmount(PaymentDto paymentDto) {
        BankAccount bankAccount;
        BankAccount bankAccount2;

        try {
            try {
                bankAccount = bankAccountRepository.getOne(paymentDto.getAccountId1());
            } catch (Exception exception) {
                System.out.println("Invalid Account Id." + paymentDto.getAccountId1());
                return new ResponseEntity<>("Invalid Account Id " + paymentDto.getAccountId2(), HttpStatus.BAD_REQUEST);
            }

            try {
                bankAccount2 = bankAccountRepository.getOne(paymentDto.getAccountId2());
            } catch (EntityNotFoundException exception) {
                System.out.println("Invalid Account Id." + paymentDto.getAccountId2());
                return new ResponseEntity<>("Invalid Account Id " + paymentDto.getAccountId2(), HttpStatus.BAD_REQUEST);
            }

            if (!bankAccount.isActive()) {
                System.out.println("Bank Account " + bankAccount.getId() + " is blocked.");
                return new ResponseEntity<>("Bank Account " + bankAccount.getId() + " is blocked.", HttpStatus.OK);
            } else if (!bankAccount2.isActive()) {
                System.out.println("Bank Account " + bankAccount2.getId() + " is blocked");
                return new ResponseEntity<>("Bank Account " + bankAccount2.getId() + " is blocked", HttpStatus.OK);
            } else if (!((bankAccount.getBalance() >= paymentDto.getTransferAmount()) || !bankAccount.isDebit())) {
                System.out.println("Not enough balance in account " + bankAccount.getId());
                return new ResponseEntity<>("Not enough balance in account " + bankAccount.getId(), HttpStatus.OK);
            } else {
                bankAccount.setBalance(bankAccount.getBalance() - paymentDto.getTransferAmount());
                bankAccount2.setBalance(bankAccount2.getBalance() + paymentDto.getTransferAmount());
                bankAccountRepository.save(bankAccount);
                bankAccountRepository.save(bankAccount2);
                return new ResponseEntity<>("Success", HttpStatus.OK);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>("Internal server error.", HttpStatus.valueOf(500));
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