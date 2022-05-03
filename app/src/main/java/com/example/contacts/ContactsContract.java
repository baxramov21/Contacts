package com.example.contacts;

// Creating variables for using to create SQLite database

import android.provider.BaseColumns;

public class ContactsContract {

    public static final class ContactsEntries implements BaseColumns {

        public static final String COLUMNS_CONTACT_TABLE_NAME = "CONTACTS_TABLE";
        public static final String COLUMNS_CONTACT_NAME = "CONTACT_NAME";
        public static final String COLUMNS_CONTACT_NUMBER = "CONTACT_NUMBER";

        public static final String CREATING_COMMAND = "CREATE TABLE IF NOT EXISTS " + COLUMNS_CONTACT_TABLE_NAME
                + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMNS_CONTACT_NAME + " TEXT, "
                + COLUMNS_CONTACT_NUMBER + " TEXT)";

        public static final String DELETE_ALL = "DROP TABLE IF EXISTS " + COLUMNS_CONTACT_TABLE_NAME;

    }
}
