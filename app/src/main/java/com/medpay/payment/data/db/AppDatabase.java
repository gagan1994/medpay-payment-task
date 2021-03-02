package com.medpay.payment.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.medpay.payment.data.db.converters.Converter;
import com.medpay.payment.data.db.dao.TransactionAndUserDao;
import com.medpay.payment.data.db.dao.TransactionDao;
import com.medpay.payment.data.db.dao.UserDao;
import com.medpay.payment.data.db.entities.Transactions;
import com.medpay.payment.data.db.entities.UserData;
import com.medpay.payment.data.db.models.TransactionAndUser;


@Database(entities = {UserData.class, Transactions.class},
        version = 1)
@TypeConverters(Converter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();
    public abstract TransactionDao getTransactionDao();
    public abstract TransactionAndUserDao getTransactionAndUserDao();

    private static AppDatabase _instance = null;

    public synchronized static AppDatabase getInstance(Context context) {
        if (_instance == null) {
            _instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,
                    "quiz.db")
                    .build();
        }
        return _instance;
    }

}
