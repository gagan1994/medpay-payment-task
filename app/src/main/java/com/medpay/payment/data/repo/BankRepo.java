package com.medpay.payment.data.repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.medpay.payment.data.db.AppDatabase;
import com.medpay.payment.data.db.entities.Transactions;
import com.medpay.payment.data.db.entities.UserData;
import com.medpay.payment.data.db.models.TransactionAndUser;
import com.medpay.payment.ui.main.databinding.PaymentInputModel;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankRepo {
    private final AppDatabase appDatabase;

    public BankRepo(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    public void cancelTransaction(PaymentInputModel transaction) {
      updateTransaction(transaction,false);
    }

    private void updateTransaction(PaymentInputModel transaction, boolean isSuccess) {
        ExecutorService exe = Executors.newSingleThreadExecutor();
        exe.execute(() -> {
            int userId=insertOrUpdateUser(transaction.getPhoneNumber(),transaction.getName());
            Transactions trans=new Transactions(transaction,userId,isSuccess);
            appDatabase.getTransactionDao().insert(trans);

        });
    }

    private int insertOrUpdateUser(String phoneNumber, String name) {
        UserData user = appDatabase.getUserDao().findUserByPhone(phoneNumber);
        if(user==null){
            user=new UserData();
            user.setName(name);
            user.setPhoneNumber(phoneNumber);
            appDatabase.getUserDao().insertUser(user);
        }
        return appDatabase.getUserDao().findUserByPhone(phoneNumber).getUserId();

    }

    public void successTransaction(PaymentInputModel transaction) {
        updateTransaction(transaction, true);
    }

    public LiveData<List<TransactionAndUser>> fetchTransaction() {
        return appDatabase.getTransactionAndUserDao().fetchTransactionLive();
    }

    public LiveData<TransactionAndUser> fetchTransaction(String transactionId) {
        return appDatabase.getTransactionAndUserDao().fetchTransaction(transactionId);
    }

    public LiveData<List<TransactionAndUser>> fetchAllUserData() {
        return  appDatabase.getTransactionAndUserDao().fetchTransactionWRTUser();
    }
}
