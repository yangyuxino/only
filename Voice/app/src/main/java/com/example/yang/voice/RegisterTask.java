package com.example.yang.voice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterTask extends Activity {
    private EditText username;
    private static EditText phonenumber;
    public static String phone;
    public static String phone1;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registertask);

        username = (EditText) findViewById(R.id.username);
        phonenumber= (EditText) findViewById(R.id.phonenumber);
        //phone =""+ phonenumber.getText();
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone =""+ phonenumber.getText();
                phone1 =""+ phonenumber.getText();
                if(!isCheck()){
                    return;
                }
                Intent i = new Intent(RegisterTask.this,Classification.class);
                startActivity(i);
                /*Intent intent = new Intent();
                Intent intent2 = new Intent();
                Bundle bundle = new Bundle();
                Bundle bundle2 = new Bundle();
                bundle.putString("key", phonenumber.getText().toString());
                bundle2.putString("key2",phonenumber.getText().toString());
                intent.setClass(RegisterTask.this, Classification.class);
                intent2.setClass(RegisterTask.this, Classification2.class);
                intent.putExtras(bundle);
                intent2.putExtras(bundle2);
                startActivity(intent, bundle);
                startActivity(intent2,bundle2);*/
            }
        });
    }
    public boolean isCheck(){
        if(username.getText().toString().trim().equals("")){
            Toast.makeText(RegisterTask.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
        }else if(phonenumber.getText().toString().trim().equals("")){
            Toast.makeText(RegisterTask.this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
        }else if(phonenumber.getText().length()!=11){
            Toast.makeText(RegisterTask.this, "请输入正确的11位手机号码", Toast.LENGTH_SHORT).show();
        }else{
            return true;
        }
        return false;
    }
}
