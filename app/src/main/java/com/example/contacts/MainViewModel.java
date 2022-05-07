package com.example.contacts;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private static Database database;
    private LiveData<List<Contact>> contacts;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = Database.getInstance(getApplication());
        contacts = database.contactsDao().getAllContacts();
    }


    public static Database getDatabase() {
        return database;
    }

    public LiveData<List<Contact>> getAllContacts() {
        return contacts;
    }
    public void insertContact(Contact contact) {
        new InsertContactTask().execute(contact);
    }

    public void deleteContact(Contact contact) {
        new DeleteContactTask().execute(contact);
    }

    public void deleteAllContacts() {
        new DeleteAllContactTask().execute();
    }

    private static class InsertContactTask extends AsyncTask<Contact, Void, Void> {
        @Override
        protected Void doInBackground(Contact... contacts) {
            if (contacts != null && contacts.length > 0) {
                database.contactsDao().addContact(contacts[0]);
            }
            return null;
        }
    }


    private static class DeleteContactTask extends AsyncTask<Contact, Void, Void> {
        @Override
        protected Void doInBackground(Contact... contacts) {
            if (contacts != null && contacts.length > 0) {
                database.contactsDao().deleteContact(contacts[0]);
            }
            return null;
        }
    }

    private static class DeleteAllContactTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... contacts) {
            database.contactsDao().deleteAllContacts();
            return null;
        }
    }
}
