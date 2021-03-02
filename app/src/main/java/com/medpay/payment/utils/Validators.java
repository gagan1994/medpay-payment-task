package com.medpay.payment.utils;

public class Validators {
    boolean isValid;
    String message;

    public Validators(boolean isValid, String message) {
        this.isValid = isValid;
        this.message = message;
    }

    public static Validators isValidPhoneNumber(String phoneNumber) {
        return isValidPhoneNumber(phoneNumber, true);
    }

    public static Validators isValidPhoneNumber(String phoneNumber, boolean isCheckNull) {
        if (isCheckNull) {
            if (checkNull(phoneNumber)) {
                return Validators.emptyError("Phone Number");
            }
        }
        String regex = "(0/91)?[7-9][0-9]{9}";
        if (phoneNumber.matches(regex)) {
            return Validators.successMessage();
        }
        return new Validators(false, "Incorrect Phone number");
    }

    private static boolean checkNull(String text) {
        return text == null || text.trim().length() == 0;
    }

    private static Validators successMessage() {
        return new Validators(true, "Valid");
    }

    private static Validators emptyError(String fieldtype) {
        return new Validators(false, fieldtype + " cannot be empty");
    }

    public static Validators isValidCost(String amount) {
        return isValidCost(amount, true);
    }

    public static Validators isValidCost(String amount, boolean isCheckNull) {
        if (isCheckNull) {
            if (checkNull(amount)) {
                return Validators.emptyError("Amount");
            }
        }
        try {
            Double.parseDouble(amount);
            return successMessage();
        } catch (Exception e) {

        }
        return new Validators(false, "Incorrect Phone number");
    }

    public boolean isValid() {
        return isValid;
    }

    public String getMessage() {
        return message;
    }
}
