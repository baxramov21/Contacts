package com.example.contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView contactsListView;

    Contact currentContact;
    ContactsAdapter adapter;

    private final ArrayList<Contact> contacts = new ArrayList<>();
    private ContactsDBHelper dbHelper;
    private  SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        contactsListView = findViewById(R.id.rv_contacts_list);
        dbHelper = new ContactsDBHelper(this);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        getData();
        adapter = new ContactsAdapter(this, contacts);
        contactsListView.setAdapter(adapter);
        contactsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                remove(i);
                return true;
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                remove(viewHolder.getAdapterPosition());
            }
        });

    }

    public void onClickCreateNewContact(View view) {
        Intent intent = new Intent(this, NewContactActivity.class);
        startActivity(intent);
    }

    private void remove(int position) {
        int id = contacts.get(position).getId();
        String where = ContactsContract.ContactsEntries._ID + " = ?";
        String[] whereArgs = new String[]{Integer.toString(id)};
        sqLiteDatabase.delete(ContactsContract.ContactsEntries.COLUMNS_CONTACT_TABLE_NAME , where , whereArgs);
        getData();
        adapter.notifyDataSetChanged();
    }

    private void getData() {
        contacts.clear();
        String selection = ContactsContract.ContactsEntries.COLUMNS_CONTACT_NAME + " = ?";
        String[] selectionArgs = new String[]{"Shixnazar"};
        Cursor cursor = sqLiteDatabase.query(ContactsContract.ContactsEntries.COLUMNS_CONTACT_TABLE_NAME, null, null, null, null, null, ContactsContract.ContactsEntries.COLUMNS_CONTACT_NAME);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(ContactsContract.ContactsEntries._ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.ContactsEntries.COLUMNS_CONTACT_NAME));
            String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.ContactsEntries.COLUMNS_CONTACT_NUMBER));
            Contact contact = new Contact(id,name, number);
            contacts.add(contact);
        }
        cursor.close();
    }


}