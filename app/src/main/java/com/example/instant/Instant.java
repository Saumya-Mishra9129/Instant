package com.example.instant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Instant extends AppCompatActivity {
    Button panic,w_help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instant);
        panic=findViewById(R.id.panic);
        w_help=findViewById(R.id.w_help);
        w_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Instant.this,WantHelp.class));

            }
        });

    }
}
