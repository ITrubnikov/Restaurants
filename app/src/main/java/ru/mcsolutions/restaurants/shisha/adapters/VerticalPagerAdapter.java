package ru.mcsolutions.restaurants.shisha.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.mcsolutions.restaurants.shisha.R;
import ru.mcsolutions.restaurants.shisha.tools.UtilsScrolling;


import static ru.mcsolutions.restaurants.shisha.tools.UtilsScrolling.setupItem;

/**
 * Created by GIGAMOLE on 7/27/16.
 */
public class VerticalPagerAdapter extends PagerAdapter {

    private final UtilsScrolling.LibraryObject[] TWO_WAY_LIBRARIES = new UtilsScrolling.LibraryObject[]{
            new UtilsScrolling.LibraryObject(
                    R.drawable.ic_fintech,
                    "Fintech"
            ),
            new UtilsScrolling.LibraryObject(
                    R.drawable.ic_delivery,
                    "Delivery"
            ),
            new UtilsScrolling.LibraryObject(
                    R.drawable.ic_social,
                    "Social network"
            ),
            new UtilsScrolling.LibraryObject(
                    R.drawable.ic_ecommerce,
                    "E-commerce"
            ),
            new UtilsScrolling.LibraryObject(
                    R.drawable.ic_wearable,
                    "Wearable"
            ),
            new UtilsScrolling.LibraryObject(
                    R.drawable.ic_internet,
                    "Internet of things"
            )
    };

    private LayoutInflater mLayoutInflater;

    public VerticalPagerAdapter(final Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return TWO_WAY_LIBRARIES.length;
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view = mLayoutInflater.inflate(R.layout.change_location_activity_item, container, false);

        setupItem(view, TWO_WAY_LIBRARIES[position]);

        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }
}
