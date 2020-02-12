package com.example.day08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * 轻量级存储 只存在于App内
 */
public class MainActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);

    }
    //写数据
    public void write(View view) {
        //得到SP的对象
        //参数一 xml文件的名字 参数二 MODE_ORIVATE 指定该SP数据只能被本应用程序读写
        SharedPreferences msg = getSharedPreferences("msg", MODE_PRIVATE);
        //获取编辑对象
        SharedPreferences.Editor edit = msg.edit();
        edit.putString("msg","哈哈哈哈，你成功了");
        //提交数据
        edit.commit();
    }

    //读数据
    public void read(View view) {
        //得到SP对象
        SharedPreferences msg = getSharedPreferences("msg", MODE_PRIVATE);
        //直接读取
        //参数一 键  参数二 找不到的时候给默认值
        String string = msg.getString("msg", "没有该数据");
        tv.setText(string);
    }
}
