package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText edtten, editCMND, editBosung;
    CheckBox chkdocbao, chkdocsach, chkdoccode;
    Button btnsend;
    RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các thành phần giao diện
        edtten = findViewById(R.id.edtten);
        editCMND = findViewById(R.id.edtcmnd);
        editBosung = findViewById(R.id.edtbosung);
        chkdocbao = findViewById(R.id.chkdocbao);
        chkdocsach = findViewById(R.id.chkdocsach);
        chkdoccode = findViewById(R.id.chkcode);
        btnsend = findViewById(R.id.btnsend);
        group = findViewById(R.id.idgruop);

        // Xử lý sự kiện khi bấm nút "Gửi thông tin"
        btnsend.setOnClickListener(view -> doShowInformation());
    }

    public void doShowInformation() {
        // Kiểm tra tên hợp lệ
        String ten = edtten.getText().toString().trim();
        if (ten.length() < 3) {
            edtten.requestFocus();
            edtten.selectAll();
            Toast.makeText(this, "Tên phải >= 3 ký tự", Toast.LENGTH_LONG).show();
            return;
        }

        // Kiểm tra CMND hợp lệ
        String cmnd = editCMND.getText().toString().trim();
        if (cmnd.length() != 9 || !cmnd.matches("\\d+")) {
            editCMND.requestFocus();
            editCMND.selectAll();
            Toast.makeText(this, "CMND phải đúng 9 chữ số", Toast.LENGTH_LONG).show();
            return;
        }

        // Kiểm tra bằng cấp
        String bang = "";
        int id = group.getCheckedRadioButtonId();
        if (id == -1) {
            Toast.makeText(this, "Phải chọn bằng cấp", Toast.LENGTH_LONG).show();
            return;
        }
        RadioButton rad = findViewById(id);
        bang = rad.getText().toString();

        // Kiểm tra sở thích
        StringBuilder sothich = new StringBuilder();
        if (chkdocbao.isChecked()) sothich.append(chkdocbao.getText()).append("\n");
        if (chkdocsach.isChecked()) sothich.append(chkdocsach.getText()).append("\n");
        if (chkdoccode.isChecked()) sothich.append(chkdoccode.getText()).append("\n");

        if (sothich.toString().isEmpty()) {
            Toast.makeText(this, "Phải chọn ít nhất 1 sở thích", Toast.LENGTH_LONG).show();
            return;
        }

        // Lấy thông tin bổ sung
        String bosung = editBosung.getText().toString();

        // Tạo Dialog để hiển thị thông tin
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin cá nhân");
        builder.setPositiveButton("Đóng", (dialog, which) -> dialog.cancel());

        // Tạo nội dung hiển thị
        String msg = "Tên: " + ten + "\n" +
                "CMND: " + cmnd + "\n" +
                "Bằng cấp: " + bang + "\n" +
                "Sở thích: " + sothich.toString() +
                "—————————–\n" +
                "Thông tin bổ sung:\n" + bosung + "\n" +
                "—————————–";

        builder.setMessage(msg);
        builder.create().show();
    }

    @Override
    public void onBackPressed() {
        // Hiển thị hộp thoại xác nhận thoát
        super.onBackPressed();
        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
        b.setTitle("Question");
        b.setMessage("Are you sure you want to exit?");
        b.setIcon(R.drawable.ic_launcher_background);
        b.setPositiveButton("Yes", (dialog, which) -> finish());
        b.setNegativeButton("No", (dialog, which) -> dialog.cancel());
        b.create().show();
    }
}
