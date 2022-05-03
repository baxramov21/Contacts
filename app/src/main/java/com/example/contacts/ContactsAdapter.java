package com.example.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactsAdapter extends ArrayAdapter<Contact> {

    public ContactsAdapter(@NonNull Context context, @NonNull List<Contact> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext().getApplicationContext()).inflate(R.layout.contact_item , parent,false);
        }

        Contact currentContact = getItem(position);

        TextView textViewContactName = convertView.findViewById(R.id.textViewContactName);
        textViewContactName.setText(currentContact.getContactName());

        return convertView;

    }
}
