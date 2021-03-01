package com.core.internetbanking.service;

import com.core.internetbanking.controller.BankAccountController;
import com.core.internetbanking.model.BankAccount;
import com.core.internetbanking.model.User;
import com.core.internetbanking.repository.BankAccountRepository;
import com.core.internetbanking.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BankAccountImplTest {

    @InjectMocks
    private BankAccountService bankAccountService = new BankAccountImpl();

    @InjectMocks
    private BankAccountController bankAccountController = new BankAccountController();

    @Mock
    UserRepository userRepository;

    @Mock
    BankAccountRepository bankAccountRepository;


    @Test
    void testCreateAccount() {
        User user = new User();
        user.setAccountId(1);

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(12);
        bankAccount.setUser(user);

        Mockito.when(bankAccountRepository.save(Mockito.any()))
                .thenReturn(bankAccount);

        Mockito.when(userRepository.getOne(1))
                .thenReturn(user);

        BankAccount bankAccount1 = bankAccountService.createAccount(1, 12);
        assertEquals(1, bankAccount1.getUser().getAccountId());
        assertEquals(12, bankAccount1.getBalance());
    }

    @Test
    void testGetBalance() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(12);

        Mockito.when(bankAccountRepository.getOne(1))
                .thenReturn(bankAccount);

        Integer bankAccount1 = bankAccountService.getBalanceOfAccount(1);
        assertEquals(12, bankAccount1);
    }

    @Test
    void testBlockAccount() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(1);

        Mockito.when(bankAccountRepository.save(Mockito.any()))
                .thenReturn(bankAccount);

        Mockito.when(bankAccountRepository.getOne(1))
                .thenReturn(bankAccount);

        bankAccountService.blockAccount(bankAccount);

        Mockito.verify(bankAccountRepository, Mockito.times(1)).
                save(Mockito.any());
    }


    @Test
    void testUnblockAccount() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(1);

        Mockito.when(bankAccountRepository.save(Mockito.any()))
                .thenReturn(bankAccount);

        Mockito.when(bankAccountRepository.getOne(1))
                .thenReturn(bankAccount);

        bankAccountService.unblockAccount(bankAccount);

        Mockito.verify(bankAccountRepository, Mockito.times(1)).
                save(Mockito.any());
    }


    @Test
    void testCreditCardStatus() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(1);

        Mockito.when(bankAccountRepository.save(Mockito.any()))
                .thenReturn(bankAccount);

        Mockito.when(bankAccountRepository.getOne(1))
                .thenReturn(bankAccount);

        bankAccountService.setCreditCardStatus(bankAccount);

        Mockito.verify(bankAccountRepository, Mockito.times(1)).
                save(Mockito.any());
    }

    @Test
    void testDebitCardStatus() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(1);

        Mockito.when(bankAccountRepository.save(Mockito.any()))
                .thenReturn(bankAccount);

        Mockito.when(bankAccountRepository.getOne(1))
                .thenReturn(bankAccount);

        bankAccountService.setDebitCardStatus(bankAccount);

        Mockito.verify(bankAccountRepository, Mockito.times(1)).
                save(Mockito.any());
    }

    @Test
    void testSendAmount() {

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(22);
        BankAccount bankAccount2 =  new BankAccount();
        bankAccount2.setBalance(11);

        Mockito.when(bankAccountRepository.getOne(1))
                .thenReturn(bankAccount);

        Mockito.when(bankAccountRepository.getOne(2))
                .thenReturn(bankAccount2);

        Mockito.when(bankAccountRepository.save(Mockito.any()))
                .thenReturn(bankAccount);

        Mockito.when(bankAccountRepository.save(Mockito.any()))
                .thenReturn(bankAccount2);

        bankAccountRepository.save(bankAccount);
        bankAccountRepository.save(bankAccount2);

        Mockito.verify(bankAccountRepository, Mockito.times(2)).
                save(Mockito.any());

    }
}
