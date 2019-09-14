package com.example.theverysmartclass.bean;

import java.io.Serializable;

public class AssetBean implements Serializable {
    private String title;
    private boolean isDownload;

    @Override
    public String toString() {
        return "AssertBean{" +
                "title='" + title + '\'' +
                ", isDownload=" + isDownload +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDownload() {
        return isDownload;
    }

    public void setDownload(boolean download) {
        isDownload = download;
    }

    public AssetBean(String title, boolean isDownload) {
        this.title = title;
        this.isDownload = isDownload;
    }

}
