package com.example.theverysmartclass.function_menu_UI;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.theverysmartclass.MainActivity;
import com.example.theverysmartclass.R;
import com.example.theverysmartclass.adapter.AssetAdapter;
import com.example.theverysmartclass.bean.AssetBean;
import com.example.theverysmartclass.utils.FileUtil;

import java.util.ArrayList;

public class AssetActivity extends AppCompatActivity {
    ArrayList<AssetBean> datalist = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset);
        initData();
        initView();
        initAdapter();

    }

    /**
     * 初始化数据
     */
    private void initData() {
        datalist = new ArrayList<AssetBean>();
        AssetBean bean = new AssetBean("课件1", false);
        datalist.add(bean);
        AssetBean bean1 = new AssetBean("课件2", false);
        datalist.add(bean1);
        AssetBean bean2 = new AssetBean("课件3", false);
        datalist.add(bean2);
        AssetBean bean3 = new AssetBean("课件4", false);
        datalist.add(bean3);
        AssetBean bean4 = new AssetBean("课件5", false);
        datalist.add(bean4);
        AssetBean bean5 = new AssetBean("课件6", false);
        datalist.add(bean5);
        AssetBean bean6 = new AssetBean("课件7", false);
        datalist.add(bean6);
        AssetBean bean7 = new AssetBean("课件8", false);
        datalist.add(bean7);
        AssetBean bean8 = new AssetBean("课件9", false);
        datalist.add(bean8);
        AssetBean bean9 = new AssetBean("课件10", false);
        datalist.add(bean9);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        recyclerView = findViewById(R.id.myRecyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        AssetAdapter adapter = new AssetAdapter(datalist);
        recyclerView.setAdapter(adapter);
        //接口回调完成item上button下载按钮的点击事件
        adapter.setDownloadClickListener(new AssetAdapter.downloadClickListener() {
                                             @Override
                                             public void downloadClick(int position) {
                                                 //文件下载路径
                                                 String url = "http://192.168.43.193:8080/TestService/Asset/RF-Capture.zip";
                                                 //文件在手机内存存储的路径
                                                 String saveurl = getExternalFilesDir(null) + "/resourse_file/";
                                                 Log.i("URL", saveurl);
                                                 //配置progressDialog
                                                 final ProgressDialog dialog = new ProgressDialog(AssetActivity.this);
                                                 dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                                                 dialog.setCanceledOnTouchOutside(false);
                                                 dialog.setCancelable(true);
                                                 dialog.setTitle("正在下载中");
                                                 dialog.setMessage("请稍后...");
                                                 dialog.setProgress(0);
                                                 dialog.setMax(100);
                                                 dialog.show();
                                                 //启动下载方法
                                                 FileUtil.get().download(AssetActivity.this, url, saveurl, new FileUtil.OnDownloadListener() {
                                                     //下载成功
                                                     @Override
                                                     public void onDownloadSuccess() {
                                                         runOnUiThread(new Runnable() {
                                                             @Override
                                                             public void run() {
                                                                 Toast.makeText(AssetActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                                                                 dialog.dismiss();
                                                             }
                                                         });

                                                     }

                                                     //下载中
                                                     @Override
                                                     public void onDownloading(int progress) {
                                                         dialog.setProgress(progress);
                                                     }

                                                     //下载失败
                                                     @Override
                                                     public void onDownloadFailed() {
                                                         runOnUiThread(new Runnable() {
                                                             @Override
                                                             public void run() {
                                                                 Toast.makeText(AssetActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
                                                                 dialog.dismiss();
                                                             }
                                                         });

                                                     }
                                                 });
                                             }
                                         }
        );
    }


}
