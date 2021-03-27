package com.example.myapplication.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.here.sdk.core.GeoCircle;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.LanguageCode;
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

import java.util.ArrayList;
import java.util.List;

public class MapFragment extends Fragment {
    private MapView mapView;
    private GeoCoordinates timisoaraCoordinates = new GeoCoordinates(45.760696, 21.226788);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_map, container, false);
        initializeViews(root);
        mapView.onCreate(savedInstanceState);

        mapView.setOnReadyListener(() -> {
        });
        loadMapScene();
        searchForCategories();
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
//        try {
//            consentEngine = new ConsentEngine();
//        } catch (InstantiationErrorException e) {
//            throw new RuntimeException("Initialization of ConsentEngine failed: " + e.getMessage());
//        }
//
//// Check if user consent has been handled.
//        if (consentEngine.getUserConsentState() == Consent.UserReply.NOT_HANDLED) {
//
//            // Show dialog.
//            consentEngine.requestUserConsent();
//        }

// The execution can continue while the dialog is being shown.

    }

    private void searchForCategories() {
        List<PlaceCategory> categoryList = new ArrayList<>();
        categoryList.add(new PlaceCategory(PlaceCategory.NATURAL_AND_GEOGRAPHICAL));
        GeoCircle geoCircle = new GeoCircle(timisoaraCoordinates, 10000);

        CategoryQuery categoryQuery = new CategoryQuery(categoryList, timisoaraCoordinates);
        SearchEngine searchEngine;
        try {
            searchEngine = new SearchEngine();
        } catch (InstantiationErrorException e) {
            throw new RuntimeException("Initialization of SearchEngine failed: " + e.error.name());
        }

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