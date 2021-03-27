package com.example.myapplication.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.helpers.DataStorageHelper;
import com.example.myapplication.models.TreasureHuntItemModel;
import com.example.myapplication.models.TreasureHuntType;
import com.example.myapplication.services.PlatformPositioningProvider;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.LanguageCode;
import com.here.sdk.core.Point2D;
import com.here.sdk.core.errors.InstantiationErrorException;
import com.here.sdk.gestures.TapListener;
import com.here.sdk.mapview.MapImage;
import com.here.sdk.mapview.MapImageFactory;
import com.here.sdk.mapview.MapMarker;
import com.here.sdk.mapview.MapScheme;
import com.here.sdk.mapview.MapView;
import com.here.sdk.mapview.MapViewBase;
import com.here.sdk.mapview.PickMapItemsResult;
import com.here.sdk.search.CategoryQuery;
import com.here.sdk.search.Place;
import com.here.sdk.search.PlaceCategory;
import com.here.sdk.search.SearchEngine;
import com.here.sdk.search.SearchOptions;

import java.util.ArrayList;
import java.util.List;

public class MapFragment extends Fragment {
    private final int FINE_LOCATION_ACCESS_CODE = 9234;
    private MapView mapView;
    private GeoCoordinates timisoaraCoordinates = new GeoCoordinates(45.760696, 21.226788);
    private PlatformPositioningProvider platformPositioningProvider;
    private boolean locationServiceStarted;
    private int currentItemPosition;
    private boolean canStartLocation;
    private MapImage mapImage;
    private FoundNewMarker mCallback;

    public interface FoundNewMarker {
        void onMarkerFound(int position);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_map, container, false);
        initializeViews(root);
        mapView.onCreate(savedInstanceState);
        mCallback = (FoundNewMarker)getActivity();

        mapView.setOnReadyListener(() -> {
        });
        loadMapScene();
        askForLocationPermission();
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
        mapImage = MapImageFactory.fromResource(getContext().getResources(), R.drawable.ic_location);
        setTapGestureHandler();
    }

    private void askForLocationPermission(){
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions( //Method of Fragment
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    FINE_LOCATION_ACCESS_CODE
            );
        } else {
            if (canStartLocation){
                startLocationService();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (!locationServiceStarted && canStartLocation){
            startLocationService();
        }
    }

    private void setTapGestureHandler() {
        mapView.getGestures().setTapListener(new TapListener() {
            @Override
            public void onTap(Point2D touchPoint) {
                pickMapMarker(touchPoint);
            }
        });
    }

    private void pickMapMarker(final Point2D touchPoint) {
        float radiusInPixel = 2;
        mapView.pickMapItems(touchPoint, radiusInPixel, new MapViewBase.PickMapItemsCallback() {
            @Override
            public void onPickMapItems(@NonNull PickMapItemsResult pickMapItemsResult) {
                List<MapMarker> mapMarkerList = pickMapItemsResult.getMarkers();
                if (mapMarkerList.size() == 0) {
                    return;
                }
                MapMarker topmostMapMarker = mapMarkerList.get(0);
            }
        });
    }

    private void startLocationService(){
        platformPositioningProvider = new PlatformPositioningProvider(getContext());
        locationServiceStarted = true;
        platformPositioningProvider.startLocating(new PlatformPositioningProvider.PlatformLocationListener() {
            @Override
            public void onLocationUpdated(Location location) {
                checkIfMarkerCanBeAdded(location);
            }
        });
    }

    private void checkIfMarkerCanBeAdded(Location location){
        TreasureHuntItemModel itemModel = DataStorageHelper.getInstance().outdoorHuntList().get(currentItemPosition);
        if (checkCoordinatesForProximity(location.getLatitude(), itemModel.getCoordinates().latitude) && checkCoordinatesForProximity(location.getLongitude(), itemModel.getCoordinates().longitude)){
            addNewMarker();
        }
    }

    private void addNewMarker(){
        MapMarker mapMarker = new MapMarker(DataStorageHelper.getInstance().outdoorHuntList().get(currentItemPosition).getCoordinates(), mapImage);
        mapView.getMapScene().addMapMarker(mapMarker);
        mCallback.onMarkerFound(currentItemPosition);
        if (currentItemPosition == 7){
            Toast.makeText(getContext(), "You've reached the end, congratulations!", Toast.LENGTH_SHORT).show();
            platformPositioningProvider.stopLocating();
            return;
        }
        currentItemPosition++;
    }

    private boolean checkCoordinatesForProximity(double currentCoordinates, double locationCoordinates){
        return Math.abs(currentCoordinates - locationCoordinates) < 0.005;
    }

    @Override
    public void onDestroy() {
        platformPositioningProvider.stopLocating();
        super.onDestroy();
    }

    public void searchForCategories(int treasureHuntType) {
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
        MapMarker mapMarker = new MapMarker(DataStorageHelper.getInstance().outdoorHuntList().get(0).getCoordinates(), mapImage);
        mapView.getMapScene().addMapMarker(mapMarker);
        canStartLocation = true;
        currentItemPosition++;
        startLocationService();
    }

    private void initializeViews(View root) {
        mapView = root.findViewById(R.id.map_view);
    }
}