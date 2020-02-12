package com.example.day08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件存储
 */
public class Main2Activity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv = (TextView) findViewById(R.id.tv);
    }

    public void write(View view) throws IOException {
        FileOutputStream fileOutputStream = openFileOutput("msg.txt", MODE_PRIVATE);
        fileOutputStream.write("这就是文件储存".getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    public void read(View view) throws IOException {
        FileInputStream fileInputStream = openFileInput("msg.txt");
        StringBuffer stringBuffer = new StringBuffer();
        byte[] bys=new byte[1024];
        int len=0;
        while ((len=fileInputStream.read(bys))!=-1) {
            stringBuffer.append(new String(bys,0,len));
        }
        tv.setText(stringBuffer);
    }
}
