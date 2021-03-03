package com.medpay.payment.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.medpay.payment.data.db.models.TransactionAndUser;

import java.util.List;

@Dao
public interface TransactionAndUserDao {
    @Query("SELECT userId,phoneNumber,name,transactionTime,isSuccess,transactionId,amount FROM Transactions INNER JOIN UserData ON Transactions.fUserId = UserData.userId")
    LiveData<List<TransactionAndUser>> fetchTransactionLive();

    @Query("SELECT userId,phoneNumber,name,transactionTime,isSuccess,transactionId,amount FROM Transactions " +
            "INNER JOIN UserData ON Transactions.fUserId = UserData.userId AND transactionId = :transactionId")
    LiveData<TransactionAndUser> fetchTransaction(String transactionId);

    @Query("SELECT userId,phoneNumber,name,transactionTime,isSuccess,transactionId,amount FROM Transactions INNER JOIN UserData ON Transactions.fUserId = UserData.userId")
    List<TransactionAndUser> fetchTransaction();

    @Query("SELECT userId,phoneNumber,name,MAX(transactionTime) as transactionTime,isSuccess,transactionId,SUM(amount) as amount" +
            " FROM Transactions INNER JOIN UserData ON Transactions.fUserId = UserData.userId GROUP BY userId")
    LiveData<List<TransactionAndUser>> fetchTransactionWRTUser();

}
