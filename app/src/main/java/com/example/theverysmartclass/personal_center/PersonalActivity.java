package com.example.theverysmartclass.personal_center;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.theverysmartclass.R;
import com.example.theverysmartclass.function_menu.MenuActivity;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class PersonalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        ImageView blurImageView=findViewById(R.id.h_back);
        ImageView avatarImageView= findViewById(R.id.h_head);
        //设置背景磨砂效果
        Glide.with(this).load(R.drawable.head)
                .bitmapTransform(new BlurTransformation(this, 25), new CenterCrop(this))
                .into(blurImageView);
        //设置圆角头像效果
        Glide.with(this).load(R.drawable.head)
                .bitmapTransform(new CropCircleTransformation(this))
                .into(avatarImageView);
        Button menubtn=(Button) findViewById(R.id.menu_btn);
        menubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PersonalActivity.this,MenuActivity.class);
                startActivity(intent);
            }
        });

    }
}
