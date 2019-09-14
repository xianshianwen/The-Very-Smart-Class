package com.example.theverysmartclass.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.theverysmartclass.R;
import com.example.theverysmartclass.bean.HomeworkBean;

import java.util.List;

public class HomeworkAdapter extends ArrayAdapter<HomeworkBean> {

    private int resourceId;

    public HomeworkAdapter(Context context, int textViewResourceId, List<HomeworkBean> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeworkBean homeworkBean = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.Image = (ImageView) view.findViewById(R.id.homework_image);
            viewHolder.Name = (TextView) view.findViewById(R.id.homework_name);
            view.setTag(viewHolder);//将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();//重新获取ViewHolder
        }

        viewHolder.Image.setImageResource(homeworkBean.getImageId());
        viewHolder.Name.setText(homeworkBean.getName());
        return view;
    }
}

class ViewHolder {
    ImageView Image;
    TextView Name;
}