package com.example.android.tourguideapp.model;

import com.example.android.tourguideapp.R;

import java.util.ArrayList;
import java.util.Arrays;

public class PlaceData {

    public static ArrayList<Place> getRestaurants() {
        return new ArrayList<>(Arrays.asList(
                new Place("Restaurant 1", "Restaurant 1 description", -1),
                new Place("Restaurant 2", "Restaurant 2 description", -1),
                new Place("Restaurant 3", "Restaurant 3 description", -1),
                new Place("Restaurant 4", "Restaurant 4 description", -1),
                new Place("Restaurant 5", "Restaurant 5 description", -1)
        ));
    }

    public static ArrayList<Place> getParks() {
        return new ArrayList<>(Arrays.asList(
                new Place("Park 1", "Park 1 description", -1),
                new Place("Park 2", "Park 2 description", -1)
        ));
    }

    public static ArrayList<Place> getMuseums() {
        return new ArrayList<>(Arrays.asList(
                new Place("Museum 1", "Museum 1 description", R.drawable.empty_image),
                new Place("Museum 2", "Museum 2 description", R.drawable.empty_image)
        ));
    }

    public static ArrayList<Place> getMovieTheaters() {
        return new ArrayList<>(Arrays.asList(
                new Place("Movie Theater 1", "Movie Theater 1 description", -1),
                new Place("Movie Theater 2", "Movie Theater 2 description", -1)
        ));
    }
}
