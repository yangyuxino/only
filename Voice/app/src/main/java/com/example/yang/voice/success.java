package com.example.yang.voice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class success extends AppCompatActivity {
private Button bt1;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        bt1= (Button) findViewById(R.id.bt1);
        tv= (TextView) findViewById(R.id.tv);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(success.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
