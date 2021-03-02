package com.medpay.payment.data.db.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionAndUser {
    private String phoneNumber;
    private String name;
    private String transactionId;
    private Date transactionTime;
    double amount;
    boolean isSuccess;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return name == null ? "" : "Sent to: " + name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getAmountString() {
        return String.format("%.2f", amount);
    }

    public String getTransactionTimeString() {
        String pattern = "dd/mm/yyyy hh:mm:ss a";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(transactionTime);
    }
}
