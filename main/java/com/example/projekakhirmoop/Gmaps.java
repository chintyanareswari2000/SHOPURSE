package com.example.projekakhirmoop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Gmaps extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap maps;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_google_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        maps = googleMap;

        LatLng Bdg = new LatLng(-6.901043173058489, 107.63833992311658);
        maps.addMarker(new MarkerOptions().position(Bdg).title("Bandung"));
        maps.moveCamera(CameraUpdateFactory.newLatLng(Bdg));
    }
}
