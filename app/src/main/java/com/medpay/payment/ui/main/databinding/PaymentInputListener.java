package com.medpay.payment.ui.main.databinding;

import com.medpay.payment.utils.Validators;

public interface PaymentInputListener {
    public void onValidation(PaymentInputDataBinding model,Validators... validators);

    void onSuccessValidation(PaymentInputDataBinding model);
}
