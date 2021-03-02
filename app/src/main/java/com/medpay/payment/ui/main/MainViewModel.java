package com.medpay.payment.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.medpay.payment.data.db.entities.Transactions;
import com.medpay.payment.data.db.models.TransactionAndUser;
import com.medpay.payment.data.repo.BankRepo;
import com.medpay.payment.ui.main.databinding.PaymentInputModel;

import java.util.List;

public class MainViewModel extends ViewModel {
    private LiveData<List<TransactionAndUser>> transactionLiveData;
    private BankRepo repo;

    public LiveData<List<TransactionAndUser>> getTransactionLiveData() {
        return transactionLiveData;
    }

    public void canceledTransaction(PaymentInputModel transaction) {
        repo.cancelTransaction(transaction);
    }

    public void successTransaction(PaymentInputModel transaction) {
        repo.successTransaction(transaction);
    }

    public void setRepo(BankRepo repoBank) {
        this.repo=repoBank;
    }

    public void fetchTransaction() {
        if(transactionLiveData==null){
            transactionLiveData=repo.fetchTransaction();
        }
    }

    public LiveData<TransactionAndUser> getTransaction(String transactionId) {
        return repo.fetchTransaction(transactionId);
    }
}
