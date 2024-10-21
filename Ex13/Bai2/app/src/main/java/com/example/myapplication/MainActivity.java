package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Dữ liệu danh sách các phần tử
    String arr[] = {
            "Ipad", "Iphone", "New Ipad", "SamSung", "Nokia", "Sony Ericson",
            "LG", "Q-Mobile", "HTC", "Blackberry", "G Phone", "FPT - Phone", "HK Phone"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TextView để hiển thị phần tử được chọn
        final TextView selection = findViewById(R.id.selection);

        // GridView
        final GridView gv = findViewById(R.id.gridView1);

        // Gán dữ liệu vào ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                arr
        );

        // Gán adapter vào GridView
        gv.setAdapter(adapter);

        // Xử lý sự kiện khi người dùng nhấn vào phần tử trong GridView
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Hiển thị tên phần tử được chọn trong TextView
                selection.setText(arr[position]);
            }
        });
    }
}
