package com.example.myproject.HelperClass;

import android.graphics.drawable.GradientDrawable;

public class CategoriesHelperClass{
    GradientDrawable gradient;
    int image;
    String title,desc;

    public CategoriesHelperClass(GradientDrawable gradient, int image, String title, String desc) {
        this.gradient = gradient;
        this.image = image;
        this.title = title;
        this.desc = desc;
    }

    public GradientDrawable getGradient() {
        return gradient;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}
