package com.example.myproject.HelperClass;

public class DevelopmentHelper {
    int image;
    String col1_value,col2_value,col3_value,col4_value,col5_value;

    public DevelopmentHelper(int image, String col1_value, String col2_value, String col3_value, String col4_value, String col5_value) {
        this.image = image;
        this.col1_value = col1_value;
        this.col2_value = col2_value;
        this.col3_value = col3_value;
        this.col4_value = col4_value;
        this.col5_value = col5_value;
    }

    public int getImage() {
        return image;
    }

    public String getCol1_value() {
        return col1_value;
    }

    public String getCol2_value() {
        return col2_value;
    }

    public String getCol3_value() {
        return col3_value;
    }

    public String getCol4_value() {
        return col4_value;
    }

    public String getCol5_value() {
        return col5_value;
    }
}
