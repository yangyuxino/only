package com.example.yang.voice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Classification extends Activity {
    private RecordButton mRecordButton = null;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classification);
        TextView number = (TextView) findViewById(R.id.number);
        mRecordButton = (RecordButton) findViewById(R.id.record_button);
        button= (Button) findViewById(R.id.button);
        /*Intent intent = getIntent();
        String str = intent.getExtras().getString("key");*/
        number.setText(RegisterTask.phone);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        path += "/yyyy.wav";
        mRecordButton.setSavePath(path);
        mRecordButton.setOnFinishedRecordListener(new RecordButton.OnFinishedRecordListener() {
            @Override
            public void onFinishedRecord(String audioPath) {
                Log.i("RECORD", "finished! save to " + audioPath);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.button) {
                    Intent intent = new Intent(Classification.this, suspend.class);
                    startActivity(intent);
                }
            }

        });
    }
}

