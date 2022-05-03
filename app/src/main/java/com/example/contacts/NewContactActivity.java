/*
 * get elements from layout
 * check if they are not empty
 * create new object and add it to the list
 */

package com.example.contacts;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewContactActivity extends AppCompatActivity {

    private EditText et_contactName;
    private EditText et_contactNumber;
    private EditText et_email;
    private Button btn_contactImage;

    private ContactsDBHelper dbHelper;
    private SQLiteDatabase database;
    private boolean isEditing;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        isEditing = false;
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        dbHelper = new ContactsDBHelper(this);
        database = dbHelper.getWritableDatabase();

        et_contactName = findViewById(R.id.editTextName);
        et_contactNumber = findViewById(R.id.editTextPhone);
        et_email = findViewById(R.id.editTextTextEmailAddress);
        btn_contactImage = findViewById(R.id.buttonContactImage);

        try {
            Intent intentFromContactInfo = getIntent();
            String contactName = intentFromContactInfo.getStringExtra("contact_name");
            String contactNumber = intentFromContactInfo.getStringExtra("contact_number");
            position = intentFromContactInfo.getIntExtra("position", -1);
            et_contactName.setText(contactName);
            et_contactNumber.setText(contactNumber);
            isEditing = true;
        } catch (Exception exception) {
            Log.e("Main", exception.toString());
        }
    }

    public void onClickSaveContact(View view) {
        ContentValues contentValues = new ContentValues();
        String contactEmail = et_email.getText().toString();
        String contactName = et_contactName.getText().toString();
        String contactNumber = et_contactNumber.getText().toString();
        Intent intentToMainActivity = new Intent(this, MainActivity.class);
        if (isEditing) {
            contentValues.put(ContactsContract.ContactsEntries.COLUMNS_CONTACT_NAME, contactName);
            contentValues.put(ContactsContract.ContactsEntries.COLUMNS_CONTACT_NUMBER, contactNumber);
            String where = ContactsContract.ContactsEntries._ID + " = ?";
            String[] whereArgs = new String[]{Integer.toString(position)};
            database.update(ContactsContract.ContactsEntries.COLUMNS_CONTACT_TABLE_NAME, contentValues, where, whereArgs);
            startActivity(intentToMainActivity);
        } else {
            if (isFilled(contactName, contactNumber)) {
                contentValues.put(ContactsContract.ContactsEntries.COLUMNS_CONTACT_NAME, contactName);
                contentValues.put(ContactsContract.ContactsEntries.COLUMNS_CONTACT_NUMBER, contactNumber);
                database.insert(ContactsContract.ContactsEntries.COLUMNS_CONTACT_TABLE_NAME, null, contentValues);
                startActivity(intentToMainActivity);
            } else {
                Toast.makeText(this, R.string.fill_fields, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isFilled(String contactName, String contactNumber) {
        return !contactName.isEmpty() && !contactNumber.isEmpty();
    }
}
