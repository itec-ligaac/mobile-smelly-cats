package com.example.myapplication.Activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.R;
import com.example.myapplication.fragments.HuntInformationFragment;
import com.example.myapplication.fragments.MapFragment;
import com.example.myapplication.fragments.HomeFragment;
import com.example.myapplication.fragments.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private MapFragment mapFragment;
    private HomeFragment homeFragment;
    private NotificationsFragment notificationsFragment;
    private HuntInformationFragment huntInformationFragment;
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
                fragmentManager.beginTransaction().hide(activeFragment).show(mapFragment).commit();
                activeFragment = mapFragment;
                return true;

            case R.id.navigation_information:
                fragmentManager.beginTransaction().hide(activeFragment).show(huntInformationFragment).commit();
                activeFragment = huntInformationFragment;
                return true;

            case R.id.navigation_notifications:
                fragmentManager.beginTransaction().hide(activeFragment).show(notificationsFragment).commit();
                activeFragment = notificationsFragment;
                return true;
        }
        return false;
    }

    private void LoadFragment() {
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, notificationsFragment, "4").hide(notificationsFragment).commit();
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, mapFragment, "3").hide(mapFragment).commit();
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, huntInformationFragment, "2").hide(huntInformationFragment).commit();
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, homeFragment, "1").commit();
    }

    public void initializeViews() {
        navView = findViewById(R.id.nav_view);

        notificationsFragment = new NotificationsFragment();
        huntInformationFragment = new HuntInformationFragment();
        mapFragment = new MapFragment();
        homeFragment = new HomeFragment();

        activeFragment = homeFragment;
    }
}