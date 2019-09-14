package com.example.theverysmartclass.function_menu_UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.theverysmartclass.R;

public class AssessActivity extends AppCompatActivity {
    TextView grade_tv;
    RatingBar star_bar;
    Button post_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assess);
        grade_tv = (TextView)findViewById(R.id.textView1);
        star_bar = (RatingBar)findViewById(R.id.ratingBar1);
        post_btn = (Button)findViewById(R.id.button1);
        post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final float r;
                r = star_bar.getRating();
                grade_tv.setText("课堂评分"+ r +"星");
            }
        });

    }
}
