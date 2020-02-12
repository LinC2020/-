package com.example.day08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * SD卡存储
 */
public class Main3Activity extends AppCompatActivity {
    private Button but;
    private TextView tv;

    private static final String TAG = "Main3Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        but = (Button) findViewById(R.id.but);
        tv = (TextView) findViewById(R.id.tv);

        String externalStorageState = Environment.getExternalStorageState(); //判断SD卡是否存在
        Log.i(TAG, "onCreate: "+externalStorageState);
        File externalStorageDirectory = Environment.getExternalStorageDirectory(); //获取SD卡的根目录
        Log.i(TAG, "onCreate: " + externalStorageDirectory.toString());

        // 获取SD卡公开目录pictures文件夹
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        Log.i(TAG, "onCreate: " + externalStoragePublicDirectory);


        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            }
        });
    }

    //授权的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        第1个参数，请求码，对应上述方法的第3个参数；
//        第2个参数，请求权限数组；
//        第3个参数，请求权限的结果数组。
        if (requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "同意读取", Toast.LENGTH_SHORT).show();
            //判断SD卡是否存在
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

                //把文件操作写到同意授权的回调方法里
                File file = Environment.getExternalStorageDirectory();
                //写SD卡
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(file, "sd.txt"));
                    fileOutputStream.write("恭喜你 SD卡数据存储成功".getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //读取sd卡
                try {
                    FileInputStream fileInputStream = new FileInputStream(new File(file, "sd.txt"));
                    StringBuffer stringBuffer = new StringBuffer();
                    byte[] bys=new byte[1024];
                    int len=0;
                    while ((len = fileInputStream.read(bys)) != -1) {
                        stringBuffer.append(new String(bys,0,len));
                    }
                    tv.setText(stringBuffer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(this, "没有同意权限", Toast.LENGTH_SHORT).show();
        }
    }
}
