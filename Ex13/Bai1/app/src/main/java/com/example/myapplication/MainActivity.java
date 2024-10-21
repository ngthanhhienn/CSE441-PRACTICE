package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Danh sách các tỉnh thành
        String[] provinces = {"Hà Nội", "Hải Phòng", "Huế", "Hà Giang", "Hà Nam", "Hà Tĩnh", "Hòa Bình"};

        // AutoCompleteTextView
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.editauto);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, provinces);
        autoCompleteTextView.setAdapter(adapter);

        // MultiAutoCompleteTextView
        MultiAutoCompleteTextView multiAutoCompleteTextView = findViewById(R.id.multiAutoCompleteTextView1);
        multiAutoCompleteTextView.setAdapter(adapter);
        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}
