package com.example.contacts;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;


@androidx.room.Database(entities = {Contact.class},version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public static Database database;
    public static final String DATABASE_NAME = "DATABASE_NAME.2";
    public static final Object LOCK = new Object();

    public static Database getInstance(Context context) {
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, Database.class, DATABASE_NAME).build();
            }
        }
        return database;
    }

    public abstract ContactsDao contactsDao();
}
