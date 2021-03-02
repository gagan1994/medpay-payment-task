package com.medpay.payment.ui.main.databinding;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.UUID;

public class PaymentInputModel implements Parcelable {

    private final String transactionId;
    private String phoneNumber;
    String amount;
    String name;

    public PaymentInputModel() {
        transactionId = UUID.randomUUID().toString();
    }

    public PaymentInputModel(String phoneNumber) {
        transactionId = UUID.randomUUID().toString();
        this.phoneNumber = phoneNumber;
    }

    public PaymentInputModel(Parcel in) {
        this.transactionId = in.readString();
        this.phoneNumber = in.readString();
        this.amount = in.readString();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public PaymentInputModel createFromParcel(Parcel in) {
            return new PaymentInputModel(in);
        }

        public PaymentInputModel[] newArray(int size) {
            return new PaymentInputModel[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.transactionId);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.amount);
        dest.writeString(this.name);

    }
    @Override
    public String toString() {
        return "PaymentInputModel{" +
                "transactionId='" + transactionId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", amount='" + amount + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public double getAmountInDouble() {
        return Double.parseDouble(amount);
    }
}
