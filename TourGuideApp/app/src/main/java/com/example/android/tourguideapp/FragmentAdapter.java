package com.example.android.tourguideapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class FragmentAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private MuseumFragment mMuseumFragment = new MuseumFragment();
    private RestaurantFragment mRestaurantFragment = new RestaurantFragment();
    private MovieTheater mMovieTheater = new MovieTheater();
    private ParkFragment mParkFragment = new ParkFragment();

    FragmentAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return mMuseumFragment;
            case 1:
                return mRestaurantFragment;
            case 2:
                return mMovieTheater;
            case 3:
                return mParkFragment;
            default:
                return mRestaurantFragment;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.Museum);
            case 1:
                return mContext.getString(R.string.restaurant);
            case 2:
                return mContext.getString(R.string.MovieTheater);
            case 3:
                return mContext.getString(R.string.Park);
            default:
                return mContext.getString(R.string.restaurant);
        }
    }
}
