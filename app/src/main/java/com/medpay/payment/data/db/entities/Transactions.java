package com.medpay.payment.data.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.medpay.payment.ui.main.databinding.PaymentInputModel;

import java.util.Calendar;
import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = UserData.class,
        parentColumns = "userId",
        childColumns = "fUserId",
        onDelete = CASCADE))
public class Transactions {

    @PrimaryKey
    @NonNull
    private String transactionId;
    private Integer fUserId;
    private Date transactionTime;
    double amount;
    boolean isSuccess;

    public Transactions(PaymentInputModel transaction, int userId, boolean isSuccess) {
        transactionId = transaction.getTransactionId();
        this.fUserId = userId;
        this.isSuccess = isSuccess;
        transactionTime = Calendar.getInstance().getTime();
        amount = transaction.getAmountInDouble();
    }

    public Transactions() {

    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(@NonNull String id) {
        this.transactionId = id;
    }

    public Integer getFUserId() {
        return fUserId;
    }

    public void setFUserId(Integer userId) {
        this.fUserId = userId;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public String getTransactionTimeString() {
        return transactionTime.toString();
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public double getAmount() {
        return amount;
    }

    public String getAmountString() {
        return amount + "";
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
}