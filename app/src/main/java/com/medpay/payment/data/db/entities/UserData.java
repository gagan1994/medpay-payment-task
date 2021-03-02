package com.medpay.payment.data.db.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class UserData {
    @PrimaryKey(autoGenerate = true)
    private int userId;
    private String phoneNumber;
    private String name;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
