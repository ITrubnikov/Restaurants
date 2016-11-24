package ru.mcsolutions.restaurants.shisha.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ru.mcsolutions.restaurants.shisha.activities.ChangeLocationActivityFragments.HorizontalPagerFragment;
import ru.mcsolutions.restaurants.shisha.activities.ChangeLocationActivityFragments.TwoWayPagerFragment;

/**
 * Created by GIGAMOLE on 8/18/16.
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private final static int COUNT = 3;

    private final static int HORIZONTAL = 0;
    private final static int TWO_WAY = 1;

    public MainPagerAdapter(final FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(final int position) {
        switch (position) {
            case TWO_WAY:
                return new TwoWayPagerFragment();
            case HORIZONTAL:
            default:
                return new HorizontalPagerFragment();
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
