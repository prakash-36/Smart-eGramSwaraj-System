package com.example.myproject.User;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.Common.Login;
import com.example.myproject.HelperClass.CategoriesAdapter;
import com.example.myproject.HelperClass.CategoriesHelperClass;
import com.example.myproject.HelperClass.FeaturedAdapter;
import com.example.myproject.HelperClass.FeaturedHelper;
import com.example.myproject.HelperClass.MostViewedAdapter;
import com.example.myproject.HelperClass.MostViewedHelperClass;
import com.example.myproject.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import java.io.File;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    MaterialAutoCompleteTextView financialYear;
    RecyclerView featuredRecycler, mostViewedRecycler, categoriesRecycler;
    RecyclerView.Adapter adapter;
    GradientDrawable gradient1, gradient2, gradient3, gradient4;

    //Variables
    static final float END_SCALE = 0.7f;

    ImageView menuIcon;
    LinearLayout contentView;
    TextView view_all;

    //Drawer menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        //spinner hooks
        financialYear = findViewById(R.id.financial_year);

        featuredRecycler = findViewById(R.id.featured_recycler);
        mostViewedRecycler = findViewById(R.id.ongoing_recycler);
        categoriesRecycler = findViewById(R.id.completed_recycler);

        //Hooks
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);

        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        //adapter class code..
        String[] years = {"2016-2017","2017-2018","2018-2019","2019-2020","2020-2021"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, years);
        financialYear.setText(adapter.getItem(4));
        financialYear.setAdapter(adapter);

        //others cards...
        naviagtionDrawer();
        featuredRecycler();
        mostViewedRecycler();
        categoriesRecycler();


        //view all
        view_all = findViewById(R.id.future_view_all);
        view_all.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),DevelopmentActivity.class)));
    }
    //Navigation Drawer Functions
    private void naviagtionDrawer() {
        //Naviagtion Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(view -> {
            if (drawerLayout.isDrawerVisible(GravityCompat.START))
                drawerLayout.closeDrawer(GravityCompat.START);
            else drawerLayout.openDrawer(GravityCompat.START);
        });

        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }


    private void categoriesRecycler() {

        //All Gradients
        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
        gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});


        ArrayList<CategoriesHelperClass> categoriesHelperClasses = new ArrayList<>();
        categoriesHelperClasses.add(new CategoriesHelperClass(gradient1, R.drawable.sanitation1, "Sanitation","Construction of Drainage"));
        categoriesHelperClasses.add(new CategoriesHelperClass(gradient2, R.drawable.drinking_water, "Drinking water","Providing pipeline, motor and flushing borewell"));
        categoriesHelperClasses.add(new CategoriesHelperClass(gradient3, R.drawable.libraray, "Library","Construction of Library"));
        categoriesHelperClasses.add(new CategoriesHelperClass(gradient4, R.drawable.road, "Road","Construction of cc roads"));

        categoriesRecycler.setHasFixedSize(true);
        adapter = new CategoriesAdapter(categoriesHelperClasses);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoriesRecycler.setAdapter(adapter);

    }

    private void mostViewedRecycler() {

        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<MostViewedHelperClass> mostViewedLocations = new ArrayList<>();
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.drinking_water, "Drinking Water","Providing pipeline, motor and flushing borewell"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.sanitation1, "Sanitation","Construction of Drainage"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.road, "Road","Construction of cc roads"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.libraray, "Education","Construction of Library"));

        adapter = new MostViewedAdapter(mostViewedLocations);
        mostViewedRecycler.setAdapter(adapter);

    }

    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelper> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelper(R.drawable.road, "Road", "Construction of cc roads"));
        featuredLocations.add(new FeaturedHelper(R.drawable.libraray, "Education", "Construction of Library"));
        featuredLocations.add(new FeaturedHelper(R.drawable.drinking_water, "Drinking Water", "Providing pipeline, motor and flushing borewell"));
        featuredLocations.add(new FeaturedHelper(R.drawable.sanitation1, "Sanitation", "Construction of Drainage"));

        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_search : startActivity(new Intent(getApplicationContext(),DevelopmentActivity.class)); break;
            case R.id.nav_logout : startActivity(new Intent(getApplicationContext(), Login.class)); break;
            case R.id.nav_share : shareApp(); break;
            case R.id.nav_rate_us : rateUS(); break;
        }
        return true;
    }

    private void rateUS() {
        CustomDialog cus = new CustomDialog(HomeActivity.this,"Rate us");
        cus.showDialog();
    }

    private void shareApp() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String body = "Download this app";
        String sub = "This is the best app";
        intent.putExtra(Intent.EXTRA_TEXT, body);
        intent.putExtra(Intent.EXTRA_TEXT, sub);
        startActivity(Intent.createChooser(intent,"ShareVia"));
    }
}