package com.example.contacts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContactInfo extends AppCompatActivity {

    private TextView tv_contact_name;
    private TextView tv_contact_number;

    private String contactNumber;
    private String contactName;
    private int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_info);

        Intent intentFromMA = getIntent();
        contactName = intentFromMA.getStringExtra("contact_name");
        contactNumber = intentFromMA.getStringExtra("contact_number");
        position = intentFromMA.getIntExtra("position",-1);

        tv_contact_name = findViewById(R.id.text_view_contact_name);
        tv_contact_name.setText(contactName);
        tv_contact_number = findViewById(R.id.text_view_contact_number);
        tv_contact_number.setText(contactNumber);

    }

    public void onClickCallToContact(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse(contactNumber));
        startActivity(intent);
    }

    public void onClickSendMSGToContact(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO,Uri.parse(contactNumber));
        startActivity(intent);
    }

    public void onClickChangeContact(View view) {
        Intent intent = new Intent(this, NewContactActivity.class);
        intent.putExtra("contact_name",contactName);
        intent.putExtra("contact_number", contactNumber);
        intent.putExtra("position",position);
        startActivity(intent);
    }
}
