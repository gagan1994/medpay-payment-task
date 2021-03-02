package com.medpay.payment.ui.main.databinding;

import android.view.View;

import com.medpay.payment.ui.main.fragments.PaymentInputFragmentArgs;
import com.medpay.payment.utils.Validators;

public class PaymentInputDataBinding {
    PaymentInputListener listener;
    PaymentInputModel model;

    public PaymentInputDataBinding(PaymentInputListener listener,
                                   PaymentInputFragmentArgs paymentInputFragmentArgs) {
        this.listener = listener;
        this.model = new PaymentInputModel();
        model.setName(paymentInputFragmentArgs.getName());
        model.setAmount(paymentInputFragmentArgs.getAmount());
        model.setPhoneNumber(paymentInputFragmentArgs.getPhoneNumber());
    }

    public String getName() {
        return model.getName();
    }

    public void setName(String name) {
        this.model.setName(name);
    }

    public String getPhoneNumber() {
        return model.getPhoneNumber();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.model.setPhoneNumber(phoneNumber);
    }

    public String getAmount() {
        return model.getAmount();
    }

    public void setAmount(String amount) {
        this.model.setAmount(amount);
    }

    public void validateAndSubmit(View v) {
        Validators phoneValidation = Validators.isValidPhoneNumber(model.getPhoneNumber());
        Validators costValidation = Validators.isValidCost(model.getPhoneNumber());
        listener.onValidation(this, costValidation, phoneValidation);
        if (phoneValidation.isValid() && costValidation.isValid()) {
            listener.onSuccessValidation(this);
        }
    }

    public PaymentInputModel getTransaction() {
        return model;
    }
}
