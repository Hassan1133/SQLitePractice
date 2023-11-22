package com.example.sqlitepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText name, phone;
    Button addBtn;

    DatabaseHelper databaseHelper;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
//        delete();
//        deleteSpec();
//        update();
//        fetch();
    }

    private void init() {
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);

        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });

        databaseHelper = new DatabaseHelper(this);
    }

    void insert() {
        long res = databaseHelper.insertData(name.getText().toString().trim(), phone.getText().toString().trim());
        Toast.makeText(this, "" + res, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("Range")
    void fetch() {
        Cursor cursor = databaseHelper.fetchData();
        while (cursor.moveToNext()) {
            Log.d(TAG, "id is : " + cursor.getInt(cursor.getColumnIndex("id")));
            Log.d(TAG, "name is : " + cursor.getString(cursor.getColumnIndex("name")));
            Log.d(TAG, "phone is : " + cursor.getString(cursor.getColumnIndex("phone")));
        }
    }

    void delete() {
        int res = databaseHelper.deleteData();
        Toast.makeText(this, "" + res, Toast.LENGTH_SHORT).show();
    }

    void deleteSpec() {
        int res = databaseHelper.deleteSpecData(1);
        Toast.makeText(this, "" + res, Toast.LENGTH_SHORT).show();
    }

    void update() {
        int res = databaseHelper.update("jawad", 4);
        Toast.makeText(this, "" + res, Toast.LENGTH_SHORT).show();
    }
}