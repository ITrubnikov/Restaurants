package ru.mcsolutions.restaurants.shisha.activities.ChangeLocationActivityFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gigamole.infinitecycleviewpager.VerticalInfiniteCycleViewPager;
import ru.mcsolutions.restaurants.shisha.R;
import ru.mcsolutions.restaurants.shisha.adapters.VerticalPagerAdapter;

/**
 * Created by GIGAMOLE on 8/18/16.
 */
public class VerticalPagerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.change_location_activity_fragment_vertical, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final VerticalInfiniteCycleViewPager verticalInfiniteCycleViewPager =
                (VerticalInfiniteCycleViewPager) view.findViewById(R.id.vicvp);
        verticalInfiniteCycleViewPager.setAdapter(new VerticalPagerAdapter(getContext()));

        verticalInfiniteCycleViewPager.setScrollDuration(1000);
        verticalInfiniteCycleViewPager.startAutoScroll(true);
    }
}
