package com.example.android.tourguideapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.tourguideapp.model.Place;

import java.util.ArrayList;

import static com.example.android.tourguideapp.model.PlaceData.getMovieTheaters;

public class MovieTheater extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.places_list, container, false);
        ArrayList<Place> movieTheaters = getMovieTheaters();
        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(new PlaceAdapter(getActivity(), R.layout.places_list, movieTheaters));
        return rootView;
    }
}
