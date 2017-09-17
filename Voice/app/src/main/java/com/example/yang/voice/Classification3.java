package com.example.yang.voice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Classification3 extends AppCompatActivity {
    private RecordButton mRecordButton = null;
    private Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classificaton3);
        TextView number = (TextView) findViewById(R.id.number3);
        mRecordButton = (RecordButton) findViewById(R.id.record_button3);
        button3= (Button) findViewById(R.id.button3);
        /*Intent intent = getIntent();
        String str = intent.getExtras().getString("key");*/
        number.setText(RegisterTask.phone);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        path += "/zzzz.wav";
        mRecordButton.setSavePath(path);
        mRecordButton.setOnFinishedRecordListener(new RecordButton.OnFinishedRecordListener() {
            @Override
            public void onFinishedRecord(String audioPath) {
                Log.i("RECORD", "finished! save to " + audioPath);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.button3) {
                    Intent intent = new Intent(Classification3.this,end.class);
                    startActivity(intent);
                }
            }

        });
    }
}

