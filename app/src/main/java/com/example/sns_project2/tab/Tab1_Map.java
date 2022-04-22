package com.example.sns_project2.tab;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sns_project2.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import noman.googleplaces.NRPlaces;
import noman.googleplaces.Place;
import noman.googleplaces.PlaceType;
import noman.googleplaces.PlacesException;
import noman.googleplaces.PlacesListener;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class Tab1_Map extends Fragment implements OnMapReadyCallback {

    GoogleMap gMap;
    MapView mapView = null;

    MarkerOptions myLocationPin;

    //Places API - PlacesListener
    List<Marker> previous_marker = null;


    Button searchbtn;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_tab1__map, container, false);


        /*Fragment내에서는 mapView로 지도를 실행*/
        mapView = (MapView) rootView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this); // 비동기적 방식으로 구글 맵 실행


        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        previous_marker = new ArrayList<Marker>();


        searchbtn = view.findViewById(R.id.search);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initLocationManager();
            }
        });




    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        try {
            MapsInitializer.initialize(this.getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }        gMap = googleMap;



        LatLng SEOUL = new LatLng(37.56, 126.97);

        //구글맵 실행시 시작 위치로 카메라 이동과 줌 사이즈
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 15));

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");
        gMap.addMarker(markerOptions);


    }

    public void initLocationManager() {
        LocationManager manager = (LocationManager)getContext().getSystemService(Context.LOCATION_SERVICE);

        try {
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                Log.d("GPS",  "startLocationService  Latitude : "+ latitude + "+ Longitude:"+ longitude);
            }

            GPSListener gpsListener = new GPSListener();
            long minTime = 10000;
            float minDistance = 0;

            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime, minDistance, gpsListener);


        } catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    class GPSListener implements LocationListener {


        public void onLocationChanged(Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            Log.d("GPS",  "onLocationChanged  Latitude : "+ latitude + "+ Longitude:"+ longitude);

            LatLng curPoint = new LatLng(latitude, longitude);
            gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));

            if (myLocationPin == null) {
                myLocationPin = new MarkerOptions();
                myLocationPin.position(curPoint);
                myLocationPin.title("내 위치");
                myLocationPin.snippet("hi hi");
                myLocationPin.icon(BitmapDescriptorFactory.fromResource(R.drawable.location_pin));
                gMap.addMarker(myLocationPin);

                initPlaceLocation(curPoint);

            } else {
                myLocationPin.position(curPoint);
            }
        }

        public void onProviderDisabled(String provider) { }

        public void onProviderEnabled(String provider) { }

        public void onStatusChanged(String provider, int status, Bundle extras) { }
    }

    //Places API - PlacesListener

    public void initPlaceLocation(LatLng location)
    {
        PlacesAPIListener placesAPIListener = new PlacesAPIListener();


        gMap.clear();//지도 클리어

        if (previous_marker != null)
            previous_marker.clear();//지역정보 마커 클리어

        new NRPlaces.Builder()
                .listener(placesAPIListener)
                .key("AIzaSyCzmawqBqx01jL2nzmdCH4urzpW5ygclmM") // API 키
                .latlng(location.latitude, location.longitude)//현재 위치
                .radius(500) //미터 내에서 검색 (500미터)
                .type(PlaceType.GYM) //장소 타입 ( 헬스장 )
                .build()
                .execute();
    }

    class PlacesAPIListener implements PlacesListener{
        @Override
        public void onPlacesFailure(PlacesException e) {

        }

        @Override
        public void onPlacesStart() {

        }

        @Override
        public void onPlacesSuccess(List<Place> places) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    for (noman.googleplaces.Place place : places) {

                        LatLng latLng
                                = new LatLng(place.getLatitude()
                                , place.getLongitude());

                        String markerSnippet = getCurrentAddress(latLng);

                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(place.getName());
                        markerOptions.snippet(markerSnippet);
                        Marker item = gMap.addMarker(markerOptions);

                        previous_marker.add(item);

                    }

                    //중복 마커 제거
                    HashSet<Marker> hashSet = new HashSet<Marker>();
                    hashSet.addAll(previous_marker);
                    previous_marker.clear();
                    previous_marker.addAll(hashSet);

                }
            });
        }

        @Override
        public void onPlacesFinished() {

        }

        public String getCurrentAddress(LatLng latlng) {

            //GPS를 주소로 변환
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());

            List<Address> addresses;

            try {
                addresses = geocoder.getFromLocation(
                        latlng.latitude,
                        latlng.longitude,
                        1);
            } catch (IOException ioException) {

                return "네트워크 문제";
            } catch (IllegalArgumentException illegalArgumentException) {
                return "GPS 좌표 없음";
            }


            if (addresses == null || addresses.size() == 0) {

                return "주소 없음";

            } else {
                Address address = addresses.get(0);
                return address.getAddressLine(0).toString();
            }

        }
    }
}