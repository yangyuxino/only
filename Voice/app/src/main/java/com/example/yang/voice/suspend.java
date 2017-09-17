package com.example.yang.voice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class suspend extends AppCompatActivity {
private Button bt1;
    private TextView tv;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspend);
        bt1= (Button) findViewById(R.id.bt1);
        tv= (TextView) findViewById(R.id.tv);
        iv= (ImageView) findViewById(R.id.iv);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(suspend.this, Classification2.class);
                startActivity(intent);
            }
        });
    }
}
