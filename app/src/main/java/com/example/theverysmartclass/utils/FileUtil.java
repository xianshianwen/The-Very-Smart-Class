package com.example.theverysmartclass.utils;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FileUtil {
    private static FileUtil fileUtil;
    private final OkHttpClient okHttpClient;
    private Context context;
    private String TAG = "下载页面";

    public static FileUtil get() {
        if (fileUtil == null) {
            fileUtil = new FileUtil();
        }
        return fileUtil;
    }

    private FileUtil() {
        okHttpClient = new OkHttpClient();
    }


    /**
     * @param url      下载链接
     * @param saveDir  存放下载文件的SD卡路径
     * @param listener 下载监听
     */
    public void download(Context context, final String url, final String saveDir, final OnDownloadListener listener) {
        this.context = context;
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败
                listener.onDownloadFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
                String savePath = isExistDir(saveDir);
                Log.w(TAG, "存储下载目录：" + savePath);
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    File file = new File(savePath, getNameFromUrl(url));
                    Log.w(TAG, "最终路径：" + file);
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中
                        listener.onDownloading(progress);
                    }
                    fos.flush();
                    // 下载完成
                    listener.onDownloadSuccess();
                } catch (Exception e) {
                    listener.onDownloadFailed();
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }


    /**
     * @param saveDir
     * @return
     * @throws IOException 判断下载目录是否存在
     */
    private String isExistDir(String saveDir) throws IOException {
        // 下载位置
        File downloadFile = new File(context.getExternalFilesDir(null), saveDir);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }
        String savePath = downloadFile.getAbsolutePath();
        Log.i(TAG, "判断文件是否存在——>" + savePath);
        return savePath;
    }

    /**
     * @param url
     * @return 返回文件名
     */
    @NonNull
    public String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }


    /**
     * 下载监听回调接口
     */
    public interface OnDownloadListener {
        /**
         * 下载成功
         */
        void onDownloadSuccess();

        /**
         * @param progress 下载进度
         */
        void onDownloading(int progress);

        /**
         * 下载失败
         */
        void onDownloadFailed();
    }

}


