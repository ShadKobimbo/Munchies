package com.example.munchies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private DrawerLayout drawer;

    private ActionBar actionBar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initToolbar();

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Munchies");
    }

    private void initView() {

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

    }

        @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Intent intent = new Intent();

        switch (item.getItemId()) {
            case R.id.navigation_login:
                intent.setClass(this, LoginActivity.class);
                startActivity(intent);
                break;

//            case R.id.navigation_orders:
//                intent.setClass(this, ScrollingActivity.class);
//                startActivity(intent);
//                break;
//
//            case R.id.navigation_restaurants:
//                intent.setClass(this, FullscreenActivity.class);
//                startActivity(intent);
//                break;
//
//            case R.id.navigation_for_you:
//                intent.setClass(this, BottomNavigationActivity.class);
//                startActivity(intent);
//                break;
//
            case R.id.navigation_profile:
                intent.setClass(this, ProfileActivity.class);
                startActivity(intent);
                break;

            case R.id.navigation_settings:
                intent.setClass(this, SettingsActivity.class);
                startActivity(intent);
                break;

            case R.id.navigation_logout:
                finish();
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}