package com.example.painoindeksilaskuri;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        String kayttajanNimi = getIntent().getStringExtra("USER_NAME");
        Button button = (Button) findViewById(R.id.button);
        button.setText (kayttajanNimi);
        if (kayttajanNimi != null){
            button.setText(kayttajanNimi);
        }

    }
}
