package com.healthcare.records.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.healthcare.records.database.dao.PatientRecordDao;
import com.healthcare.records.database.dao.UserDao;
import com.healthcare.records.database.entity.PatientRecord;
import com.healthcare.records.database.entity.User;

/**
 * Main database class for the application using Room.
 * Contains tables for users and patient records.
 */
@Database(entities = {User.class, PatientRecord.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "healthcare_records_db";
    private static AppDatabase instance;

    public abstract UserDao userDao();
    public abstract PatientRecordDao patientRecordDao();

    /**
     * Gets singleton instance of the database.
     * Creates it if it doesn't exist.
     */
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
