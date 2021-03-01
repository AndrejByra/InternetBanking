package com.core.internetbanking.dto;

public class PaymentDto {

    private Integer accountId1;

    private Integer accountId2;

    private Integer transferAmount;


    public Integer getAccountId1() {
        return accountId1;
    }

    public void setAccountId1(Integer accountId1) {
        this.accountId1 = accountId1;
    }

    public Integer getAccountId2() {
        return accountId2;
    }

    public void setAccountId2(Integer accountId2) {
        this.accountId2 = accountId2;
    }

    public Integer getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Integer transferAmount) {
        this.transferAmount = transferAmount;
    }

}
