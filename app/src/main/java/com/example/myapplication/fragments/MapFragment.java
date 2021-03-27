package com.example.myapplication.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Activities.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.models.TreasureHuntType;
import com.example.myapplication.services.PlatformPositioningProvider;
import com.here.sdk.core.GeoBox;
import com.here.sdk.core.GeoCircle;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.LanguageCode;
import com.here.sdk.core.LocationListener;
import com.here.sdk.core.errors.InstantiationErrorException;
import com.here.sdk.mapview.MapImage;
import com.here.sdk.mapview.MapImageFactory;
import com.here.sdk.mapview.MapMarker;
import com.here.sdk.mapview.MapScheme;
import com.here.sdk.mapview.MapView;
import com.here.sdk.search.CategoryQuery;
import com.here.sdk.search.Place;
import com.here.sdk.search.PlaceCategory;
import com.here.sdk.search.SearchEngine;
import com.here.sdk.search.SearchOptions;
import com.here.sdk.search.TextQuery;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

public class MapFragment extends Fragment {
    private final int FINE_LOCATION_ACCESS_CODE = 9234;
    private MapView mapView;
    private GeoCoordinates timisoaraCoordinates = new GeoCoordinates(45.760696, 21.226788);
    private GeoCoordinates timisoaraNECoordinates = new GeoCoordinates(45.7789, 21.1910);
    private GeoCoordinates timisoaraSWCoordinates = new GeoCoordinates(45.7142, 21.2686);
    private PlatformPositioningProvider platformPositioningProvider;
    private boolean locationServiceStarted;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_map, container, false);
        initializeViews(root);
        mapView.onCreate(savedInstanceState);

        mapView.setOnReadyListener(() -> {
        });
        loadMapScene();

        askForLocationPermission();
        //searchForCategories();
        return root;
    }

    private void loadMapScene() {
        // Load a scene from the HERE SDK to render the map with a map scheme.
        mapView.getMapScene().loadScene(MapScheme.NORMAL_DAY, mapError -> {
            if (mapError == null) {
                double distanceInMeters = 1000 * 10;
                mapView.getCamera().lookAt(timisoaraCoordinates, distanceInMeters);
            } else {
                Log.d("tag", "Loading map failed: mapError: " + mapError.name());
            }
        });
    }

    private void askForLocationPermission(){
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions( //Method of Fragment
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    FINE_LOCATION_ACCESS_CODE
            );
        } else {
            startLocationService();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (!locationServiceStarted){
            startLocationService();
        }
    }

    private void startLocationService(){
        platformPositioningProvider = new PlatformPositioningProvider(getContext());
        locationServiceStarted = true;
        platformPositioningProvider.startLocating(new PlatformPositioningProvider.PlatformLocationListener() {
            @Override
            public void onLocationUpdated(Location location) {
                Toast.makeText(getContext(), "merge", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchForCategories(int treasureHuntType) {
        List<PlaceCategory> categoryList = new ArrayList<>();
        switch (treasureHuntType){
            case TreasureHuntType.PARKS:
                categoryList.add(new PlaceCategory(PlaceCategory.NATURAL_AND_GEOGRAPHICAL));
                break;
            case TreasureHuntType.CULTURE:
                categoryList.add(new PlaceCategory(PlaceCategory.GOING_OUT_THEATRE_MUSIC_CULTURE));
                break;
                case TreasureHuntType.SHOPPING:
                categoryList.add(new PlaceCategory(PlaceCategory.SHOPPING));
                break;
            default:
                break;
        }
        CategoryQuery categoryQuery = new CategoryQuery(categoryList, timisoaraCoordinates);
        SearchEngine searchEngine;
        try {
            searchEngine = new SearchEngine();
        } catch (InstantiationErrorException e) {
            throw new RuntimeException("Initialization of SearchEngine failed: " + e.error.name());
        }

//        GeoBox viewportGeoBox = new GeoBox(timisoaraNECoordinates, timisoaraSWCoordinates);
//        TextQuery query = new TextQuery("pizza", viewportGeoBox);

        int maxItems = 50;
        SearchOptions searchOptions = new SearchOptions(LanguageCode.EN_US, maxItems);
        searchEngine.search(categoryQuery, searchOptions, (searchError, list) -> {
            if (searchError != null) {
                return;
            }
            MapImage mapImage = MapImageFactory.fromResource(getContext().getResources(), R.drawable.ic_pin);

            // If error is null, list is guaranteed to be not empty.
            for (Place searchResult : list) {
                String addressText = searchResult.getAddress().addressText;
                MapMarker mapMarker = new MapMarker(searchResult.getGeoCoordinates(), mapImage);
                mapView.getMapScene().addMapMarker(mapMarker);
                Log.d("tag 2", addressText);
            }
        });
    }

    private void initializeViews(View root) {
        mapView = root.findViewById(R.id.map_view);
    }
}