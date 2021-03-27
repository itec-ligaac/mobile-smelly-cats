package com.example.myapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.R;
import com.example.myapplication.fragments.DashboardFragment;
import com.example.myapplication.fragments.HomeFragment;
import com.example.myapplication.fragments.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private DashboardFragment dashboardFragment;
    private HomeFragment homeFragment;
    private NotificationsFragment notificationsFragment;
    private BottomNavigationView navView;
    private Fragment activeFragment;
    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        LoadFragment();
        navView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit();
                activeFragment = homeFragment;
                return true;

            case R.id.navigation_dashboard:
                fragmentManager.beginTransaction().hide(activeFragment).show(dashboardFragment).commit();
                activeFragment = dashboardFragment;
                return true;

            case R.id.navigation_notifications:
                fragmentManager.beginTransaction().hide(activeFragment).show(notificationsFragment).commit();
                activeFragment = notificationsFragment;
                return true;
        }
        return false;
    }

    private void LoadFragment() {
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, notificationsFragment, "3").hide(notificationsFragment).commit();
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, dashboardFragment, "2").commit();
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, homeFragment, "1").commit();
    }

    public void initializeViews() {
        navView = findViewById(R.id.nav_view);

        notificationsFragment = new NotificationsFragment();
        dashboardFragment = new DashboardFragment();
        homeFragment = new HomeFragment();

        activeFragment = dashboardFragment;
    }


}