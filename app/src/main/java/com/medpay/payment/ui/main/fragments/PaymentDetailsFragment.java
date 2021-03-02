package com.medpay.payment.ui.main.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medpay.payment.R;
import com.medpay.payment.data.db.models.TransactionAndUser;
import com.medpay.payment.databinding.FragmentPaymentDetailsBinding;
import com.medpay.payment.ui.main.MainViewModel;

public class PaymentDetailsFragment extends Fragment {

    private FragmentPaymentDetailsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=DataBindingUtil
                .inflate(LayoutInflater.from(getContext()),
                        R.layout.fragment_payment_details,
                        container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainViewModel viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        viewModel.getTransaction(PaymentDetailsFragmentArgs.fromBundle(getArguments())
                .getTransactionId()).observe(getViewLifecycleOwner(),
                transactionAndUser -> {
                    binding.setTransactionModel(transactionAndUser);
                    updateUi(transactionAndUser);
                });
    }

    private void updateUi(TransactionAndUser transactionAndUser) {
        binding.ivStatus.setImageDrawable(getActivity().getDrawable(transactionAndUser.isSuccess()
                ?R.drawable.ic_baseline_check_circle:R.drawable.ic_baseline_cancel));
        binding.tvStatus.setText(transactionAndUser.isSuccess()?"Success":"Canceled");
        binding.tvStatus.setTextColor(getContext().getColor(transactionAndUser.isSuccess()?R.color.success:R.color.design_default_color_error));
    }
}