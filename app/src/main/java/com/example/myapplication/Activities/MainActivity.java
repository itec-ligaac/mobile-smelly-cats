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
import com.example.myapplication.fragments.ProfileFragment;
import com.example.myapplication.fragments.TreasureHuntsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, TreasureHuntsFragment.HuntStarted, MapFragment.FoundNewMarker {
    private MapFragment mapFragment;
    private ProfileFragment profileFragment;
    private TreasureHuntsFragment treasureHuntsFragment;
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
                fragmentManager.beginTransaction().hide(activeFragment).show(profileFragment).commit();
                activeFragment = profileFragment;
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
                fragmentManager.beginTransaction().hide(activeFragment).show(treasureHuntsFragment).commit();
                activeFragment = treasureHuntsFragment;
                return true;
        }
        return false;
    }

    private void LoadFragment() {
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, treasureHuntsFragment, "4").hide(treasureHuntsFragment).commit();
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, mapFragment, "3").hide(mapFragment).commit();
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, huntInformationFragment, "2").hide(huntInformationFragment).commit();
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, profileFragment, "1").commit();
    }

    private void initializeViews() {
        navView = findViewById(R.id.nav_view);

        treasureHuntsFragment = new TreasureHuntsFragment();
        huntInformationFragment = new HuntInformationFragment();
        mapFragment = new MapFragment();
        profileFragment = new ProfileFragment();

        activeFragment = profileFragment;
    }

    @Override
    public void onHuntStarted(int type) {
        mapFragment.searchForCategories(type);
        huntInformationFragment.addHuntInfo(0);
    }

    @Override
    public void onMarkerFound(int position) {
        huntInformationFragment.addHuntInfo(position);
    }
}