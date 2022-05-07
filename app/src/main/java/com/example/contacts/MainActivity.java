package com.example.contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView contactsListView;
    private ContactsAdapter adapter;
    private MainViewModel viewModel;

    public static final String TAG = MainActivity.class.getName();

    public static ArrayList<Contact> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        contactsListView = findViewById(R.id.rv_contacts_list);
        getData();
        adapter = new ContactsAdapter(this, contacts);
        contactsListView.setAdapter(adapter);
        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentToContactInfo = new Intent(MainActivity.this, ContactInfo.class);
                Contact clickedContact = contacts.get(i);
                intentToContactInfo.putExtra("contact_name", clickedContact.getContactName());
                intentToContactInfo.putExtra("contact_number", clickedContact.getContactNumber());
                intentToContactInfo.putExtra("position", clickedContact.getId());
                startActivity(intentToContactInfo);
            }
        });
        contactsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                remove(i);
                return true;
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
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
        Contact contact = contacts.get(position);
        viewModel.deleteContact(contact);
    }

    private void getData() {
        LiveData<List<Contact>> contactsFromDB = viewModel.getAllContacts();
        contactsFromDB.observe((LifecycleOwner) this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contactsFromLiveData) {
                contacts.clear();
                contacts.addAll(contactsFromLiveData);
                adapter.notifyDataSetChanged();
            }
        });
    }
}