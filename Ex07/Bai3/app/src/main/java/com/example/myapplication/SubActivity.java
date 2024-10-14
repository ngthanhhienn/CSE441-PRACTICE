package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubActivity extends AppCompatActivity {

    EditText edtAA, edtBB;
    Button btnsendtong, btnsendhieu;
    Intent myintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        edtAA = findViewById(R.id.edtAA);
        edtBB = findViewById(R.id.edtBB);
        btnsendtong = findViewById(R.id.btnsendtong);
        btnsendhieu = findViewById(R.id.btnsendhieu);

        // Nhận Intent
        myintent = getIntent();
        // lấy dữ liệu khỏi Intent
        int a = myintent.getIntExtra("soa", 0);
        int b = myintent.getIntExtra("sob", 0);
        edtAA.setText(a + "");
        edtBB.setText(b + "");

        btnsendtong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xử lý kết quả
                int sum = a + b;
                // Đẩy kết quả trở lại Intent
                myintent.putExtra("kq", sum);
                setResult(33, myintent); // Trả intent trở về
                finish(); // thoát Activity này để quay về
            }
        });

        btnsendhieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xử lý kết quả
                int sub = a - b;
                myintent.putExtra("kq", sub);
                setResult(34, myintent);
                finish(); // thoát Activity này để quay về
            }
        });
    }
}