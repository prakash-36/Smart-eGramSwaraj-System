package com.example.myproject.HelperClass;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.R;
import com.example.myproject.User.CustomDialog;
import com.example.myproject.User.DevelopmentActivity;
import com.example.myproject.User.HomeActivity;

import java.util.ArrayList;

public class DevelopmentAdapter extends RecyclerView.Adapter<DevelopmentAdapter.DevelopmentViewHolder> {
    ArrayList<DevelopmentHelper> developmentActivities;

    public DevelopmentAdapter(ArrayList<DevelopmentHelper> developmentActivities) {
        this.developmentActivities = developmentActivities;
    }

    @NonNull
    @Override
    public DevelopmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.development_card_design, parent, false);
        DevelopmentViewHolder developmentViewHolder = new DevelopmentViewHolder(view);
        return developmentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DevelopmentViewHolder holder, int position) {
        DevelopmentHelper developmentHelper = developmentActivities.get(position);

        holder.image.setImageResource(developmentHelper.getImage());
        holder.col1Value.setText(developmentHelper.getCol1_value());
        holder.col2Value.setText(developmentHelper.getCol2_value());
        holder.col3Value.setText(developmentHelper.getCol3_value());
        holder.col4Value.setText(developmentHelper.getCol4_value());
        holder.col5Value.setText(developmentHelper.getCol5_value());
    }

    @Override
    public int getItemCount() {
        return developmentActivities.size();
    }


    public static class DevelopmentViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView col1Value,col2Value,col3Value,col4Value,col5Value;

        public DevelopmentViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.development_image);
            col1Value = itemView.findViewById(R.id.development_col1_value);
            col2Value = itemView.findViewById(R.id.development_col2_value);
            col3Value = itemView.findViewById(R.id.development_col3_value);
            col4Value = itemView.findViewById(R.id.development_col4_value);
            col5Value= itemView.findViewById(R.id.development_col5_value);


            //onclick listener
            image.setOnClickListener(this::showPopupMenu);
        }

        private void showPopupMenu(View view){
            PopupMenu popupMenu = new PopupMenu(view.getContext(),view);
            popupMenu.inflate(R.menu.dotmenu);
            popupMenu.show();

            //onclick
            popupMenu.setOnMenuItemClickListener(item -> {
                item.getItemId();
                return true;
            });
        }
    }
}
