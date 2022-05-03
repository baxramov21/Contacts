package com.example.contacts;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ContactsDBHelper extends SQLiteOpenHelper {

    public static final String CONTACTS_DB = "CONTACTS.DB";
    public static final int VERSION = 1;

    public ContactsDBHelper(@Nullable Context context) {
        super(context, CONTACTS_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ContactsContract.ContactsEntries.CREATING_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(ContactsContract.ContactsEntries.DELETE_ALL);
        onCreate(sqLiteDatabase);
    }
}
