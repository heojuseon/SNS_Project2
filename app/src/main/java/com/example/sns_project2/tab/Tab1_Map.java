package com.example.sns_project2.tab;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sns_project2.R;

import com.example.sns_project2.mapData.FetchData;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class Tab1_Map extends Fragment implements OnMapReadyCallback {
    private GoogleMap gMap;
    MapView mapView = null;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 1;
    private double lat, lng;

    Button gym, cafe, convenience_store;
    ImageButton refresh;

    Fragment frg = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_tab1__map, container, false);

//        /*Fragment내에서는 mapView로 지도를 실행*/
//        mapView = (MapView) rootView.findViewById(R.id.mapView);
//        mapView.onCreate(savedInstanceState);
//        mapView.onResume();
//        mapView.getMapAsync(this); // 비동기적 방식으로 구글 맵 실행

        gym = rootView.findViewById(R.id.gym_search);
        cafe = rootView.findViewById(R.id.cafe_search);
        convenience_store = rootView.findViewById(R.id.convenience_store_search);
        refresh = rootView.findViewById(R.id.refresh_btn);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.getContext());
        mapView = (MapView) rootView.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);


        gym.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                StringBuilder stringBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                stringBuilder.append("location=" + lat + "," + lng);
                stringBuilder.append("&radius=1000");
                stringBuilder.append("&type=gym");
                stringBuilder.append("&sensor=true");
                stringBuilder.append("&key=AIzaSyCFWHshQcfZkyPmOscoBEIQMmiN5aphjZ4");

                String url = stringBuilder.toString();
                Object dataFetch[] = new Object[2];
                dataFetch[0] = gMap;
                dataFetch[1] = url;

                FetchData fetchData = new FetchData();
                fetchData.execute(dataFetch);


            }
        });


        cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringBuilder stringBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                stringBuilder.append("location=" + lat + "," + lng);
                stringBuilder.append("&radius=1000");
                stringBuilder.append("&type=cafe");
                stringBuilder.append("&sensor=true");
                stringBuilder.append("&key=AIzaSyCFWHshQcfZkyPmOscoBEIQMmiN5aphjZ4");

                String url = stringBuilder.toString();
                Object dataFetch[] = new Object[2];
                dataFetch[0] = gMap;
                dataFetch[1] = url;

                FetchData fetchData = new FetchData();
                fetchData.execute(dataFetch);

            }
        });

        convenience_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder stringBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                stringBuilder.append("location=" + lat + "," + lng);
                stringBuilder.append("&radius=1000");
                stringBuilder.append("&type=convenience_store");
                stringBuilder.append("&sensor=true");
                stringBuilder.append("&key=AIzaSyCFWHshQcfZkyPmOscoBEIQMmiN5aphjZ4");

                String url = stringBuilder.toString();
                Object dataFetch[] = new Object[2];
                dataFetch[0] = gMap;
                dataFetch[1] = url;

                FetchData fetchData = new FetchData();
                fetchData.execute(dataFetch);
            }
        });


        //새로고침 버튼
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                fragmentManager.beginTransaction().remove(Tab1_Map.this).commit();
//                fragmentManager.beginTransaction().detach(Tab1_Map.this).attach(Tab1_Map.this);
//                fragmentManager.popBackStack();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.framelayout, Tab1_Map.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("null") // name can be null
                        .commit();

            }
        });



//        getCurrentLocation();

        return rootView;
    }



    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;

        getCurrentLocation();

    }


    private void getCurrentLocation() {


        if (ActivityCompat.checkSelfPermission(
                getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);


            return;
        }

        //오른쪽 상단 gps버튼
        gMap.setMyLocationEnabled(true);



        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(60000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(5000);

        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                Log.d("Location result", "location result is = " + locationResult);

                if(locationRequest == null){
                    Log.d("Current location", "Current location is null");

                    return;
                }
                for (Location location:locationResult.getLocations()){
                    if (location != null){
                        Log.d("Current location", "Current location is = " + location.getLatitude() + location.getLongitude());
                    }
                }

//                //프래그먼트에서 Toast를 사용하려면 onCreateView(즉 프래그먼트 끝나기 전 시점)에서 사용해야한다.
//                Toast.makeText(getContext(), "location result is = " + locationResult, Toast.LENGTH_LONG).show();
//
//                if (locationRequest == null){
//                    Toast.makeText(getContext(), "Current location is null", Toast.LENGTH_LONG).show();
//
//                    return;
//                }
//                for (Location location:locationResult.getLocations()){
//                    if (location != null){
//                        Toast.makeText(getContext(), "Current location is = "+ location.getLongitude() , Toast.LENGTH_LONG).show();
//                    }
//                }
            }
        };
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {

                    lat = location.getLatitude();
                    lng = location.getLongitude();

                    LatLng latLng = new LatLng(lat, lng);
                    gMap.addMarker(new MarkerOptions().position(latLng).title("current location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                    gMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

                }
            }
        });

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (REQUEST_CODE){
            case REQUEST_CODE:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    getCurrentLocation();


                }
        }
    }
}