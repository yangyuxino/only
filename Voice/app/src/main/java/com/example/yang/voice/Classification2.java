package com.example.yang.voice;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Classification2 extends Activity {
    private RecordButton mRecordButton = null;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classification2);
        TextView number = (TextView) findViewById(R.id.number2);
        mRecordButton = (RecordButton) findViewById(R.id.record_button2);
        button2= (Button) findViewById(R.id.button2);
        /*Intent intent = getIntent();
        String str = intent.getExtras().getString("key2");*/
        number.setText(RegisterTask.phone1);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        path += "/xxxx.wav";
        mRecordButton.setSavePath(path);
        mRecordButton.setOnFinishedRecordListener(new RecordButton.OnFinishedRecordListener() {
            @Override
            public void onFinishedRecord(String audioPath) {
                Log.i("RECORD", "finished! save to " + audioPath);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.button2) {
                    Intent intent = new Intent(Classification2.this, success.class);
                    startActivity(intent);
                }
            }

        });
    }
}
