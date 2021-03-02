package com.medpay.payment.ui.main.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.Navigator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.medpay.payment.R;
import com.medpay.payment.data.db.entities.Transactions;
import com.medpay.payment.databinding.FragmentMainBinding;
import com.medpay.payment.ui.main.MainViewModel;
import com.medpay.payment.ui.main.adapters.RvTransactionAdapter;
import com.medpay.payment.utils.UiUtils;

import java.util.List;

public class MainFragment extends Fragment {

    private FragmentMainBinding fragmentMainBinding;
    private MainViewModel viewModelProvider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMainBinding= DataBindingUtil
                .inflate(LayoutInflater.from(getContext()),
                        R.layout.fragment_main,
                        container, false);
      viewModelProvider= new ViewModelProvider(getActivity()).get(MainViewModel.class);
        return fragmentMainBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RvTransactionAdapter adapter=new RvTransactionAdapter();
        fragmentMainBinding.rvTransactions.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentMainBinding.rvTransactions.setAdapter(adapter);
        viewModelProvider.fetchTransaction();
        viewModelProvider.getTransactionLiveData().observe(getViewLifecycleOwner(), transactions -> {
            adapter.setData(transactions);
            updateUi();
        });
        UiUtils.showOnboardingScreen(getActivity(), fragmentMainBinding.fabSendMoney);
        fragmentMainBinding.fabSendMoney.setOnClickListener(v -> Navigation
                .findNavController(getView())
                .navigate(R.id.action_mainFragment_to_paymentInputFragment));
    }

    private void updateUi() {
        if(fragmentMainBinding.rvTransactions.getAdapter().getItemCount() == 0){
            showEmptySnackbar();
        }
    }
    private void showEmptySnackbar() {
        Snackbar snackbar = Snackbar
                .make(getView(),
                        getString(R.string.empty_transaction),
                        Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}