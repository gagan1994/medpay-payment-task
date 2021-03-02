package com.medpay.payment.ui.main.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.medpay.payment.R;
import com.medpay.payment.databinding.FragmentPaymentInputBinding;
import com.medpay.payment.ui.main.MainViewModel;
import com.medpay.payment.ui.main.databinding.PaymentInputDataBinding;
import com.medpay.payment.ui.main.databinding.PaymentInputListener;
import com.medpay.payment.ui.main.databinding.PaymentInputModel;
import com.medpay.payment.utils.Validators;

public class PaymentInputFragment extends Fragment implements PaymentInputListener {

    private static final String TAG = "PaymentInputFragment";
    private FragmentPaymentInputBinding fragmentPaymentInputBinding;
    private PaymentInputDataBinding paymentInputDataBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentPaymentInputBinding = DataBindingUtil
                .inflate(LayoutInflater.from(getContext()),
                        R.layout.fragment_payment_input,
                        container, false);
        PaymentInputFragmentArgs paymentInputFragmentArgs = PaymentInputFragmentArgs.fromBundle(getArguments());
        paymentInputDataBinding = new PaymentInputDataBinding(this,paymentInputFragmentArgs);
        fragmentPaymentInputBinding.setPaymentInputModel(paymentInputDataBinding);
        return fragmentPaymentInputBinding.getRoot();
    }


    @Override
    public void onValidation(PaymentInputDataBinding model, Validators... validators) {
        String message = null;
        for (Validators validator : validators) {
            if (!validator.isValid()) {
                if (message == null) message = "";
                message = message + validator.getMessage() + "\n";

            }
        }
        if (message != null) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(message)
                    .setTitle("Please correct below errors")
                    .setCancelable(true)
                    .setNegativeButton("Dismiss", (dialog, which) -> {

            });
            final AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    @Override
    public void onSuccessValidation(PaymentInputDataBinding model) {
        PaymentInputFragmentDirections.ActionPaymentInputFragmentToInProgressFragment action
                = PaymentInputFragmentDirections.actionPaymentInputFragmentToInProgressFragment(model.getTransaction());
        Navigation.findNavController(getView()).navigate(action);

    }
}