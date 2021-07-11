package com.example.myproject.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myproject.HelperClass.DevelopmentAdapter;
import com.example.myproject.HelperClass.DevelopmentHelper;
import com.example.myproject.HelperClass.MostViewedAdapter;
import com.example.myproject.HelperClass.MostViewedHelperClass;
import com.example.myproject.R;

import java.util.ArrayList;

public class DevelopmentActivity extends AppCompatActivity {

    RecyclerView developmentRecycler;
    RecyclerView.Adapter adapter;
    ImageView backup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_development);

        developmentRecycler = findViewById(R.id.development_activities);
        backup = findViewById(R.id.backup);
        backup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        developmentRecycler();
    }

    private void developmentRecycler() {
        developmentRecycler.setHasFixedSize(true);
        developmentRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<DevelopmentHelper> developmentHelpers = new ArrayList<>();
        developmentHelpers.add(new DevelopmentHelper(R.drawable.drinking_water,"1","653246", "Providing pipeline","Drinking Water","Rs.500000"));
        developmentHelpers.add(new DevelopmentHelper(R.drawable.sanitation1,"2","653246", "Construction of Drainage","Sanitation","Rs.600000"));
        developmentHelpers.add(new DevelopmentHelper(R.drawable.road,"3","653246", "Construction of cc roads","Road","Rs.700000"));
        developmentHelpers.add(new DevelopmentHelper(R.drawable.libraray,"4","653246", "Construction of Library","Education","Rs.800000"));

        adapter = new DevelopmentAdapter(developmentHelpers);
        developmentRecycler.setAdapter(adapter);
    }
}