package com.medpay.payment.ui.main.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medpay.payment.R;
import com.medpay.payment.databinding.FragmentInProgressBinding;
import com.medpay.payment.ui.main.MainViewModel;
import com.medpay.payment.ui.main.databinding.PaymentInputModel;

public class InProgressFragment extends Fragment {
    private static final long TOTAL_DELAY = 3000;
    CountDownTimer countDownTimer = new CountDownTimer(TOTAL_DELAY,
            500) {

        public void onTick(long millisUntilFinished) {
            int progress= (int) (TOTAL_DELAY-millisUntilFinished);
            binding.pbHorizontal.setProgress(progress);
        }

        public void onFinish() {
            binding.pbHorizontal.setProgress((int)TOTAL_DELAY);
            listener.remove();
            confirmScreen();
        }
    };
    private MainViewModel viewModel;
    private PaymentInputModel transaction;
    private OnBackPressedCallback listener=new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            listener.remove();
            showCancelPopUp();
        }
    };

    private FragmentInProgressBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=DataBindingUtil
                .inflate(LayoutInflater.from(getContext()),
                        R.layout.fragment_in_progress,
                        container, false);
        getActivity().getOnBackPressedDispatcher().addCallback(listener);
       viewModel= new ViewModelProvider(getActivity()).get(MainViewModel.class);
       transaction= InProgressFragmentArgs.fromBundle(getArguments()).getTransactionId();
        return binding.getRoot();
    }

    private void showCancelPopUp() {
        countDownTimer.cancel();
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Do you want to cancel transaction")
                .setTitle("Warning!")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, which) -> {
                    viewModel.canceledTransaction(transaction);
                    Navigation.findNavController(getView()).popBackStack();
                })
                .setNegativeButton("No", (dialog, which) -> {
                    countDownTimer.onFinish();
                });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        countDownTimer.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }

    private void confirmScreen() {
        viewModel.successTransaction(transaction);
        InProgressFragmentDirections.ActionInProgressFragmentToPaymentDetailsFragment action
                = InProgressFragmentDirections.actionInProgressFragmentToPaymentDetailsFragment()
                .setTransactionId(transaction.getTransactionId());
        Navigation.findNavController(getView()).navigate(action);
    }

}