package com.example.myapplication;
 
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageButton btnplay, btnstop;
    Boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnplay = findViewById(R.id.btnplay);
        btnstop = findViewById(R.id.btnstop);

        // Xử lý sự kiện Play
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, MyService.class);
                startService(intent1);
                if (flag) {
                    btnplay.setImageResource(R.drawable.stop); // Đổi icon thành pause
                    flag = false;
                } else {
                    btnplay.setImageResource(R.drawable.play); // Đổi icon thành play
                    flag = true;
                }
            }
        });

        // Xử lý sự kiện Stop
        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, MyService.class);
                stopService(intent2);
                btnplay.setImageResource(R.drawable.play); // Reset icon về play
                flag = true;
            }
        });
    }
}
