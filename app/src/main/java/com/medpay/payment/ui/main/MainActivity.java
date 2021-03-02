package com.medpay.payment.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.medpay.payment.R;
import com.medpay.payment.data.db.AppDatabase;
import com.medpay.payment.data.repo.BankRepo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BankRepo repoBank = new BankRepo(AppDatabase.getInstance(this));
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.setRepo(repoBank);
    }
}