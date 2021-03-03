package com.core.internetbanking.controller;

import com.core.internetbanking.dto.PaymentDto;
import com.core.internetbanking.model.BankAccount;
import com.core.internetbanking.service.BackendException;
import com.core.internetbanking.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bankAccount")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping
    public BankAccount createBankAccount(@RequestParam Integer accountId, @RequestParam Integer balance) {
        return bankAccountService.createAccount(accountId, balance);
    }

    @GetMapping(path = "/getBalance")
    public Integer getBalanceByAccountID(@RequestParam Integer id) {
        return bankAccountService.getBalanceOfAccount(id);
    }

    @PutMapping(path = "/send")
    public ResponseEntity<String> sendMoney(@RequestBody PaymentDto paymentDto) {
        try {
            bankAccountService.sendAmount(paymentDto);
        } catch (BackendException backendException) {
            return new ResponseEntity<>(backendException.getMessage(), backendException.getHttpStatus());
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PutMapping(path = "/blockBankAccount")
    public void blockBankAccount(@RequestParam Integer accountId) {
        bankAccountService.blockAccount(accountId);
    }

    @PutMapping(path = "/unlockBankAccount")
    public void unlockBankAccount(@RequestParam Integer accountId) {
        bankAccountService.unblockAccount(accountId);
    }

    @PutMapping(path = "/setStatusToCreditCard")
    public void setStatusToCreditCard(@RequestParam Integer accountId) {
        bankAccountService.setCreditCardStatus(accountId);
    }

    @PutMapping(path = "/setStatusToDebitCard")
    public void setStatusToDebitCard(@RequestParam Integer accountId) {
        bankAccountService.setDebitCardStatus(accountId);
    }
}