package com.example.theverysmartclass.function_menu_UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.theverysmartclass.MainActivity;
import com.example.theverysmartclass.R;
import com.example.theverysmartclass.adapter.HomeworkAdapter;
import com.example.theverysmartclass.bean.HomeworkBean;

import java.util.ArrayList;
import java.util.List;

public class HomeworkActivity extends AppCompatActivity {

    private List<HomeworkBean> DateList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);
        //初始化数组队列
        initDates();
        //创建适配器
        HomeworkAdapter adapter = new HomeworkAdapter(HomeworkActivity.this, R.layout.homework_item, DateList);
        //获取ListView对象
        ListView listView = (ListView) findViewById(R.id.list_view);
        //设置适配器
        listView.setAdapter(adapter);
        //添加监听方法
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HomeworkBean homeworkBean = DateList.get(position);
                Toast.makeText(HomeworkActivity.this, "查看"+homeworkBean.getName()+"课程作业内容", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void initDates() {
        for (int i = 0; i < 2; i++) {
            HomeworkBean work1 = new HomeworkBean("数学", R.drawable.work1);
            DateList.add(work1);
            HomeworkBean work2 = new HomeworkBean("英语", R.drawable.work2);
            DateList.add(work2);
            HomeworkBean work3 = new HomeworkBean("政治", R.drawable.work3);
            DateList.add(work3);
            HomeworkBean work4 = new HomeworkBean("物理", R.drawable.work4);
            DateList.add(work4);
            HomeworkBean work5 = new HomeworkBean("计算机", R.drawable.work5);
            DateList.add(work5);
            HomeworkBean work6 = new HomeworkBean("电子", R.drawable.work6);
            DateList.add(work6);
        }
    }
}
