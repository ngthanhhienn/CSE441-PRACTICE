package com.example.phonelist;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ TextView
        txt1 = findViewById(R.id.selection);

        // 1. Khởi tạo dữ liệu cho mảng arr1
        final String arr1[] = {
                "Iphone 7", "SamSung Galaxy S7", "Nokia Lumia 730",
                "Sony Xperia XZ", "HTC One E9"
        };

        // 2. Khai báo Adapter và gán Data source vào ArrayAdapter
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                arr1
        );

        // 3. Khai báo ListView và đưa Adapter vào ListView
        ListView lv1 = findViewById(R.id.lv1);
        lv1.setAdapter(adapter1);

        // 4. Viết sự kiện khi Click vào một dòng trên ListView
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Hiển thị vị trí và giá trị của phần tử được chọn trong TextView
                txt1.setText("Vị trí " + position + " : " + arr1[position]);
            }
        });
    }
}