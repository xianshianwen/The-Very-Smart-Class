package com.example.theverysmartclass.function_menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.theverysmartclass.R;
import com.example.theverysmartclass.function_menu_UI.AssessActivity;
import com.example.theverysmartclass.function_menu_UI.AssetActivity;
import com.example.theverysmartclass.function_menu_UI.HomeworkActivity;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //获取按钮实例，并添加监听器
        Button sign_btn = findViewById(R.id.sign_btn);
        sign_btn.setOnClickListener(this);
        Button assess_btn = findViewById(R.id.assess_btn);
        assess_btn.setOnClickListener(this);
        Button asset_btn = findViewById(R.id.asset_btn);
        asset_btn.setOnClickListener(this);
        Button homework_btn = findViewById(R.id.homework_btn);
        homework_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_btn:
                //签到界面
                break;
            case R.id.assess_btn:
                //评教界面
                Intent intent1 = new Intent(MenuActivity.this, AssessActivity.class);
                startActivity(intent1);
                break;
            case R.id.asset_btn:
                //资料界面
                Intent intent2 = new Intent(MenuActivity.this, AssetActivity.class);
                startActivity(intent2);
                break;
            case R.id.homework_btn:
                //作业界面
                Intent intent3 = new Intent(MenuActivity.this, HomeworkActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
