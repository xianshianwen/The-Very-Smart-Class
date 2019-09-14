package com.example.theverysmartclass.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.theverysmartclass.R;
import com.example.theverysmartclass.bean.AssetBean;

import java.util.List;

public class AssetAdapter extends BaseQuickAdapter<AssetBean, BaseViewHolder> {
    downloadClickListener listener;
    public AssetAdapter(@Nullable List<AssetBean> data) {
        super(R.layout.assert_item, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, AssetBean item) {
        helper.setText(R.id.item_tv, item.getTitle());//设置文件名
//        helper.setText(R.id.item_btn, item.isDownload()? "已下载":"未下载");
        helper.getView(R.id.item_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!= null){
                    listener.downloadClick(helper.getAdapterPosition());
                }
            }
        });

    }

    /**
     * 定义监听接口
     */
    public interface downloadClickListener {
        void downloadClick(int position);

    }

    public void setDownloadClickListener(downloadClickListener listener){
        this.listener= listener;
    }


}
