package com.example.luckynumberapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class resultScreen extends AppCompatActivity {
    TextView txt1, resultNum;
    ImageButton btn;

    public int randomNumberGenerator(){
        Random random = new Random();
        int randomNum = random.nextInt(1000);
        return randomNum;
    }

    public void share(String username, int luckyNumber){
        Intent i = new Intent();
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, username+" got lucky today");
        i.putExtra(Intent.EXTRA_TEXT, "His lucky number is "+luckyNumber+" today!");
        startActivity(Intent.createChooser(i, "Choose a platform to share"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txt1 = findViewById(R.id.txt1);
        resultNum = findViewById(R.id.txt2);
        btn = findViewById(R.id.imageButton);

        //receive data from main activity
        Intent i = getIntent();
        String userName = i.getStringExtra("name");
        int luckyNum = randomNumberGenerator();
        resultNum.setText(luckyNum);
        btn.setOnClickListener(v -> {
            share(userName, luckyNum);
        });
    }
}