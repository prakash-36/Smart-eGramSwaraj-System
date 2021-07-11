package com.example.myproject.User;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.R;

public class CustomDialog implements View.OnClickListener {
    Dialog dialog;
    Activity activity;
    RatingBar rb;
    String msg;

    public CustomDialog(Activity activity, String msg) {
        this.activity = activity;
        this.msg = msg;
    }

    public void showDialog(){
        dialog = new Dialog(activity);
        dialog.setContentView(R.layout.rating_dialog);
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations =R.style.animation;

        Button sub = dialog.findViewById(R.id.dialog_submit);
        TextView title = dialog.findViewById(R.id.dialog_text);
        title.setText(msg);
        rb = dialog.findViewById(R.id.rating_bar);
        sub.setOnClickListener(this);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_submit :
                Toast.makeText(activity,"You are rated "+rb.getRating()+" stars..",Toast.LENGTH_SHORT).show();
                break;
        }
        dialog.dismiss();
    }
}
