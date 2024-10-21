package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<String> arraywork;
    ArrayAdapter<String> arrAdapter;
    EditText edtwork, edth, edtm;
    TextView txtdate;
    Button btnwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các thành phần giao diện
        edth = findViewById(R.id.edthour);
        edtm = findViewById(R.id.edtmi);
        edtwork = findViewById(R.id.edtwork);
        btnwork = findViewById(R.id.btnadd);
        lv = findViewById(R.id.listView1);
        txtdate = findViewById(R.id.txtdate);

        // Hiển thị ngày hiện tại
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txtdate.setText("Hôm nay: " + simpleDateFormat.format(currentDate));

        // Khởi tạo danh sách công việc
        arraywork = new ArrayList<>();
        arrAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arraywork);
        lv.setAdapter(arrAdapter);

        // Lấy dữ liệu từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("NoteList", MODE_PRIVATE);
        Set<String> savedWorks = sharedPreferences.getStringSet("works", null);
        if (savedWorks != null) {
            arraywork.addAll(savedWorks);
            arrAdapter.notifyDataSetChanged();
        }

        // Xử lý sự kiện khi nhấn nút Add Work
        btnwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtwork.getText().toString().equals("") || edth.getText().toString().equals("") || edtm.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Info missing");
                    builder.setMessage("Please enter all information of the work");
                    builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.show();
                } else {
                    String str = edtwork.getText().toString() + " - " + edth.getText().toString() + ":" + edtm.getText().toString();
                    arraywork.add(str);
                    arrAdapter.notifyDataSetChanged();

                    // Lưu vào SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Set<String> worksSet = new HashSet<>(arraywork);
                    editor.putStringSet("works", worksSet);
                    editor.apply();

                    // Xóa nội dung nhập sau khi thêm
                    edth.setText("");
                    edtm.setText("");
                    edtwork.setText("");
                }
            }
        });

        // Xử lý sự kiện khi nhấn vào một công việc trong ListView để xóa
        lv.setOnItemClickListener((parent, view, position, id) -> {
            arraywork.remove(position);
            arrAdapter.notifyDataSetChanged();

            // Cập nhật lại SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Set<String> worksSet = new HashSet<>(arraywork);
            editor.putStringSet("works", worksSet);
            editor.apply();
        });
    }
}
