package com.core.internetbanking.dto;

public class PaymentDto {

    private Integer transferFrom;

    private Integer transferTo;

    private Integer amount;

    public Integer getTransferFrom() {
        return transferFrom;
    }

    public void setTransferFrom(Integer transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Integer getTransferTo() {
        return transferTo;
    }

    public void setTransferTo(Integer transferTo) {
        this.transferTo = transferTo;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}