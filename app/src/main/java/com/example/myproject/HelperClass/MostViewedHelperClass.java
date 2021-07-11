package com.example.myproject.HelperClass;

import android.widget.ImageView;

public class MostViewedHelperClass {
    int imageView;
    String title,desc;

    public MostViewedHelperClass(int imageView, String title, String desc) {
        this.imageView = imageView;
        this.title = title;
        this.desc = desc;
    }

    public int getImageView() {
        return imageView;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc(){
        return desc;
    }
}
