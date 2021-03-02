package com.medpay.payment.data.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.medpay.payment.data.db.entities.UserData;

@Dao
public interface UserDao {
    @Query("SELECT * from UserData WHERE phoneNumber = :phoneNumber")
    UserData findUserByPhone(String phoneNumber);

    @Insert
    void insertUser(UserData user);
}
