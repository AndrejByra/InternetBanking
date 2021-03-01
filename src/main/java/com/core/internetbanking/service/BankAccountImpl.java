package com.core.internetbanking.service;

import com.core.internetbanking.dto.PaymentDto;
import com.core.internetbanking.model.BankAccount;
import com.core.internetbanking.repository.BankAccountRepository;
import com.core.internetbanking.repository.UserRepository;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

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
        BankAccount bankAccount = bankAccountRepository.getOne(paymentDto.getAccountId1());
        BankAccount bankAccount2 = bankAccountRepository.getOne(paymentDto.getAccountId2());

        if (bankAccount.isActive() && bankAccount2.isActive()) {
            bankAccount.setBalance(bankAccount.getBalance() - paymentDto.getTransferAmount());
            bankAccount2.setBalance(bankAccount2.getBalance() + paymentDto.getTransferAmount());

            bankAccountRepository.save(bankAccount);
            bankAccountRepository.save(bankAccount2);
        } else
            System.out.println("Bank Account is blocked.");
    }

    @Override
    public void blockAccount(BankAccount bankAccount) {
        BankAccount bankAccount1 = bankAccountRepository.getOne(bankAccount.getId());
        bankAccount1.setActive(false);
        bankAccountRepository.save(bankAccount1);
    }

    @Override
    public void unblockAccount(BankAccount bankAccount) {
        BankAccount bankAccount1 = bankAccountRepository.getOne(bankAccount.getId());
        bankAccount1.setActive(true);
        bankAccountRepository.save(bankAccount1);
    }

    @Override
    public void setCreditCardStatus(@RequestParam BankAccount bankAccount) {
        BankAccount bankAccount1 = bankAccountRepository.getOne(bankAccount.getId());
        bankAccount1.setDebit(false);
        bankAccountRepository.save(bankAccount1);
    }

    @Override
    public void setDebitCardStatus(BankAccount bankAccount) {
        BankAccount bankAccount1 = bankAccountRepository.getOne(bankAccount.getId());
        bankAccount1.setDebit(true);
        bankAccountRepository.save(bankAccount1);
    }

}
